package tr.com.agmlab.utils;



import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.w3c.dom.ls.LSException;

import tr.com.agmlab.connection.LsHttpConnection;
import tr.com.agmlab.enums.Config;
import tr.com.agmlab.enums.ErrorCode;
import tr.com.agmlab.enums.LsService;
import tr.com.agmlab.manager.ConnectionManager;

import com.agmlab.localsearch.core.enums.Language;
import com.agmlab.localsearch.web.util.Response;


/**
 * 
 * @author Muhammet Dinc
 * Makes bulutt service call
 * 
 */
public class ServiceUtil {
	/**
	 * 
	 * @param service 	bulutt service
	 * @param params	service parameters
	 * @return			DTO (Data Transfer Object)
	 */
	public static String baseUrl = Config.APPLICATION_URL.getValue();
	public static String token = "";
	
	public static Language language = Language.TURKISH;
	
	public static Response call(LsService service, Object param) throws LSException {
		return call(service, null, param);
	}
	
	public static Response call(LsService service, HashMap<String, String> headers, Object param) throws LSException {
		
		ConnectionManager cm = ConnectionManager.getInstance();
		LsHttpConnection bc = null;
		Response response =null;
		HttpResponse httpResponse = null;
		try {
			bc = cm.getHttpConnection();
			
			switch (service.getServiceType()) {
			case POST:
				httpResponse = bc.makeHttpPostRequest(baseUrl+service.getUrl(), headers, param);
				break;

			case GET:
				httpResponse = bc.makeHttpGetRequest(baseUrl+service.getUrl(), headers, param.toString());
				break;
			
			case PUT:
				httpResponse = bc.makeHttpPutRequest(baseUrl+service.getUrl(), headers, param);
				break;
				
			case DELETE:
				break;
			}
			
			
			try {
				response = JsonUtil.getResponseObject(service.getReturnType(), bc.getResponseText(httpResponse));
			}catch(Exception e) {
				throw new LsFoException(ErrorCode.BAD_SERVICE_RETURN_VALUE, e);
			}
		} catch(LsFoException e) {
			throw e;
		} finally{
			cm.releaseHttpConnection(bc);
		}
		
		return response;
	}
		
}
