package tr.com.agmlab.enums;

import com.agmlab.localsearch.core.domain.user.dto.UserAuthResponse;
import com.agmlab.localsearch.core.domain.user.dto.UserSocialSigninRequest;




public enum LsService {
//	CHECK_FOR_UPDATE("/user/signin", HttpMethod.GET, ForceUpdate.class),
	SIGN_IN("/user/signin", HttpMethod.POST, UserAuthResponse.class),
	FACEBOOK_LOGIN("/user/signin/facebook", HttpMethod.POST, UserAuthResponse.class);
	
	private String url;
	private HttpMethod serviceType;
	private Class<? extends Object> returnType;
	
	private LsService (String url, HttpMethod serviceType, Class <? extends Object> returnType){
		setUrl(url);
		setServiceType(serviceType);
		setReturnType(returnType);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public HttpMethod getServiceType() {
		return serviceType;
	}

	public void setServiceType(HttpMethod serviceType) {
		this.serviceType = serviceType;
	}

	public Class<? extends Object> getReturnType() {
		return returnType;
	}

	public void setReturnType(Class<? extends Object> returnType) {
		this.returnType = returnType;
	}
	
}
