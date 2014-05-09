package tr.com.agmlab.connection;



import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;

import tr.com.agmlab.enums.ErrorCode;
import tr.com.agmlab.enums.LsService;
import tr.com.agmlab.utils.JsonUtil;
import tr.com.agmlab.utils.LsFoException;
import tr.com.agmlab.utils.ServiceUtil;
import android.util.Log;

/**
 * LsHttpConnection,
 * Makes HttpRequests, and holds its response
 * 
 * @author Muhammet Dinc
 *
 */
public class LsHttpConnection {
	private HttpClient client;
	private HttpContext context;
	/**
	 * Constructor
	 * 
	 * @param baseClient	BaseHttpClient, generally DefaultHttpClient
	 * @param context		HttpContext, to manage cookie etc
	 */
	public LsHttpConnection(HttpClient baseClient, HttpContext context) {
		this.client = baseClient;
		this.context = context;
	}
	
	/**
	 * prepare HttpGetRequest with url, params and headers
	 * 
	 * @param url
	 * @param headers
	 * @param params
	 * @return
	 */
	public static HttpGet prepareHttpGetRequest(String url, HashMap<String, String> headers, String param) {
		String connStr = "";
		
		if(param != null && !param.isEmpty()) {
			if(url.charAt(url.length()-1) != '/') {
				connStr = "/";
			}
			
			connStr += param;
		}
		
		HttpGet getReq = new HttpGet(url+connStr + "?accessToken=" + ServiceUtil.token);
		
		if(headers != null) {
			for(String headerKey:headers.keySet()) {
				getReq.setHeader(headerKey, headers.get(headerKey));
			}
		}
		
		return getReq;
	}
	
	/**
	 * Prepare HttpPostRequest with url, params and headers
	 * 
	 * @param url
	 * @param headers
	 * @param params
	 * @return
	 * @throws NimbusException
	 * @throws UnsupportedEncodingException 
	 */
	public static HttpPost prepareHttpPostRequest(String url, HashMap<String, String> headers, Object param) throws LsFoException{
		HttpPost postReq = null;
		if(url.equals(LsService.SIGN_IN.getUrl())){
			postReq = new HttpPost(url);
		}else{
			postReq = new HttpPost(url + "?accessToken=" + ServiceUtil.token);
		}
		
		// set headers
		if(headers != null) {
			for(String headerKey:headers.keySet()) {
				postReq.setHeader(headerKey, headers.get(headerKey));
			}
		}
		
		// set param
		StringEntity entity = null;
		try {
			entity = new StringEntity(JsonUtil.getJson(param), HTTP.UTF_8);
		} catch (UnsupportedEncodingException e) {
			Log.e("ls_connection", "Unsupported encoding exception, e:"+e);
		}
		
		entity.setContentType("application/json");
		postReq.setEntity(entity);
		
		return postReq;
	}
	
	public static HttpPut prepareHttpPutRequest(String url, HashMap<String, String> headers, Object param) throws LsFoException{
		HttpPut putReq = null;
		
		putReq = new HttpPut(url + "?accessToken=" + ServiceUtil.token);
		
		// set headers
		if(headers != null) {
			for(String headerKey:headers.keySet()) {
				putReq.setHeader(headerKey, headers.get(headerKey));
			}
		}
		
		// set param
		StringEntity entity = null;
		try {
			entity = new StringEntity(JsonUtil.getJson(param), HTTP.UTF_8);
		} catch (UnsupportedEncodingException e) {
			Log.e("ls_connection", "Unsupported encoding exception, e:"+e);
		}
		
		entity.setContentType("application/json");
		putReq.setEntity(entity);
		
		return putReq;
	}
	
	/**
	 * Makes HttpGetRequets with url, headers and params
	 * 
	 * @param url		base requets url
	 * @param params	param=>value pairs
	 * 
	 * @throws NimbusException
	 */
	public HttpResponse makeHttpGetRequest(String url,HashMap<String, String> headers, String param) throws LsFoException {
		HttpGet getReq = prepareHttpGetRequest(url, headers, param);
		HttpResponse response = null;

		try {
			response = client.execute(getReq ,context);
			return response;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			throw new LsFoException(ErrorCode.SERVICE_CALL_EXCEPTION);
		} catch (IOException e) {
			e.printStackTrace();
			throw new LsFoException(ErrorCode.SERVICE_CALL_EXCEPTION);
		}
	}
	
	/**
	 * Makes httpGetRequest with url, headers and params
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws NimbusException
	 */
	public HttpResponse makeHttpGetRequest(String url, String param) throws LsFoException {
		HttpGet getReq = prepareHttpGetRequest(url, null, param);
		HttpResponse response = null;
		
		try {
			response = client.execute(getReq ,context);
			return response;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			throw new LsFoException(ErrorCode.SERVICE_CALL_EXCEPTION);
		} catch (IOException e) {
			e.printStackTrace();
			throw new LsFoException(ErrorCode.SERVICE_CALL_EXCEPTION);
		}
	}
	
	
	/**
	 * Makes httpPostRequest with url, headers and params
	 * @param url	base url
	 * @param params	param=>value pairs
	 *
	 * @throws NimbusException
	 */
	public HttpResponse makeHttpPostRequest(String url,HashMap<String, String> headers, Object param) throws LsFoException {
		HttpResponse response = null;
		HttpPost postReq = prepareHttpPostRequest(url, headers, param);
		
		try {
			response = client.execute(postReq, context);
		} catch(ClientProtocolException e) {
			e.printStackTrace();
			throw new LsFoException(ErrorCode.SERVICE_CALL_EXCEPTION);
		} catch(IOException e) {
			e.printStackTrace();
			throw new LsFoException(ErrorCode.SERVICE_CALL_EXCEPTION);
		}
		
		return response;
	}
	
	public HttpResponse makeHttpPutRequest(String url,HashMap<String, String> headers, Object param) throws LsFoException {
		HttpResponse response = null;
		HttpPut putReq = prepareHttpPutRequest(url, headers, param);
		
		try {
			response = client.execute(putReq, context);
		} catch(ClientProtocolException e) {
			e.printStackTrace();
			throw new LsFoException(ErrorCode.SERVICE_CALL_EXCEPTION);
		} catch(IOException e) {
			e.printStackTrace();
			throw new LsFoException(ErrorCode.SERVICE_CALL_EXCEPTION);
		}
		
		return response;
	}
	
	public String getResponseText(HttpResponse response) {
		// if status not success, return null
		if(response.getStatusLine().getStatusCode() != 200)
			return null;
		
		InputStream is = null;
		
		try {
			is = response.getEntity().getContent();
			
			if(isThereAHeaderVal(response, "Content-Encoding", "gzip")) {
				is = new GZIPInputStream(is);
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ByteArrayOutputStream baos = null;
		
		try {
			baos = new ByteArrayOutputStream();
			
			int a = -1;
			byte[] tmp = new byte[1024];
			
			while ((a = is.read(tmp)) != -1) {
				baos.write(tmp,0,a);
			}
			
			return new String(baos.toByteArray());
			
		} catch(IOException e){
			return null;
		} finally {
			try {
				is.close();
			} catch (IOException e) {}
			try {
				baos.close();
			} catch (IOException e) {}
		}
	}
	
	public InputStream getResponseStream(HttpResponse response) {
		if(response.getStatusLine().getStatusCode() != 200)
			return null;
		
		InputStream is = null;
		
		try {
			is = response.getEntity().getContent();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return is;
	}
	
	public static boolean isThereAHeaderVal(HttpResponse response, String header, String val) {
		Header[] headers = response.getHeaders(header);
		
		if(headers!=null && headers.length>0) {
			if(headers[0].getValue().contains(val))
				return true;
			else
				return false;
		}
		
		return false;
	}

}
