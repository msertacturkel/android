package tr.com.agmlab.manager;


import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpVersion;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;

import tr.com.agmlab.connection.LsHttpConnection;
import tr.com.agmlab.enums.Config;
import tr.com.agmlab.utils.ApplicationUtil;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * ConnectionManager : responsible for manage connections (generally
 * httpconnections) It is singleton
 * 
 * @author Muhammet Dinç
 * 
 */
public class ConnectionManager {
	private final int HTTP_MAX_CONNECTION;
	private final int HTTP_CONNECTION_TIMEOUT;
	private static ConnectionManager instance = null;
	private static SSLSocketFactory _factory;
	private static HttpContext httpContext;
	private static CookieStore cookieStore;
	private static HttpParams httpParams;
	private volatile BlockingQueue<Boolean> httpConnectionQueue;
	private volatile HashSet<LsHttpConnection> workingConnections;
	
	private static SchemeRegistry registry;
	
	private ConnectionManager() {
		_factory = gewtNewSslSocketFactory();
		
		this.HTTP_MAX_CONNECTION = Config.HTTP_MAX_CONNECTION.getIntValue();
		this.HTTP_CONNECTION_TIMEOUT = Config.HTTP_CONNECTION_TIMEOUT.getIntValue();

	
		httpContext = new BasicHttpContext();
		cookieStore = new BasicCookieStore();
		httpContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
		
		httpConnectionQueue = new ArrayBlockingQueue<Boolean>(HTTP_MAX_CONNECTION);
		workingConnections = new HashSet<LsHttpConnection>();

		registry = new SchemeRegistry();
		registry.register(new Scheme("https", _factory, 443));
		registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
		
		
		httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, this.HTTP_CONNECTION_TIMEOUT);

		for (int i = 0; i < HTTP_MAX_CONNECTION; i++) {
			httpConnectionQueue.add(true);
		}
	}

	public synchronized static ConnectionManager getInstance() {
		if (instance == null) {
			instance = new ConnectionManager();
		}

		return instance;
	}

	public boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) ApplicationUtil.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		return (ni != null && ni.isConnected());
	}

	public LsHttpConnection getHttpConnection() {
		try {
			if (httpConnectionQueue.take()) {
				DefaultHttpClient newClient;// = new DefaultHttpClient();
				SingleClientConnManager mgr = new SingleClientConnManager(null, registry);
				newClient = new DefaultHttpClient(mgr, null);
				
				Log.i("connection","getHttpConnection, context:"+httpContext);
				LsHttpConnection newConnection = new LsHttpConnection(newClient, httpContext);

				synchronized (workingConnections) {
					workingConnections.add(newConnection);
				}

				return newConnection;
			}
		} catch (InterruptedException e) {
			;
		}

		return null;
	}

	public boolean releaseHttpConnection(LsHttpConnection conn) {
		synchronized (workingConnections) {
			if (workingConnections.contains(conn)) {
				workingConnections.remove(conn);
				synchronized (httpConnectionQueue) {
					httpConnectionQueue.add(true);
				}

				return true;
			}
		}
		return false;
	}
	
	public static SSLSocketFactory gewtNewSslSocketFactory() {
		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
	        trustStore.load(null, null);
			LsSSLSocketFactory bsf = new LsSSLSocketFactory(trustStore);
			bsf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			
			return bsf;
		} catch (Exception e) {
			throw new AssertionError(e);
		}
	}
	
	public void printCookies() {
		Log.i("cookie","---------- cookies -----------");
		for(Cookie c: cookieStore.getCookies()) {
			Log.i("cookie", c.getName() + ":"+c.getValue());
		}
	}
	
	public void resetHttpContext() {
		instance = new ConnectionManager();
	}
	
	static class LsSSLSocketFactory extends SSLSocketFactory {
	    SSLContext sslContext = SSLContext.getInstance("TLS");

	    public LsSSLSocketFactory(KeyStore truststore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
	        super(truststore);

	        TrustManager tm = new X509TrustManager() {
	            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	            }

	            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	            }

	            public X509Certificate[] getAcceptedIssuers() {
	                return null;
	            }
	        };

	        sslContext.init(null, new TrustManager[] { tm }, null);
	    }

	    @Override
	    public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException, UnknownHostException {
	        return sslContext.getSocketFactory().createSocket(socket, host, port, autoClose);
	    }

	    @Override
	    public Socket createSocket() throws IOException {
	        return sslContext.getSocketFactory().createSocket();
	    }
	}
	
	public HttpClient getNewHttpClient() {
	    try {
	        KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
	        trustStore.load(null, null);

	        SSLSocketFactory sf = new LsSSLSocketFactory(trustStore);
	        sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

	        HttpParams params = new BasicHttpParams();
	        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
	        HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

	        SchemeRegistry registry = new SchemeRegistry();
	        registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
	        registry.register(new Scheme("https", sf, 443));

	        ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);

	        return new DefaultHttpClient(ccm, params);
	    } catch (Exception e) {
	        return new DefaultHttpClient();
	    }
	}
}
