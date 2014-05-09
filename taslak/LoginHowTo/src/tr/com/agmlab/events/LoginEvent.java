package tr.com.agmlab.events;



import com.agmlab.localsearch.core.domain.user.dto.UserAuthResponse;
import com.agmlab.localsearch.web.util.Response;


public class LoginEvent extends BaseEvent{
	private UserAuthResponse loginResponse;
	

	private LoginEvent() {
		loginResponse = null;
	}
	
	private LoginEvent(Response response) {
		super(response.getResultCode(), response.getDuration(), response.isSuccess());
		loginResponse = (UserAuthResponse)response.getData();
	}
	
	public static LoginEvent getLoginEventFromResponse(Response response) {
		if(response==null) {
			return new LoginEvent();
		} else {
			return new LoginEvent(response);
		}
	}

	public UserAuthResponse getLoginResponse() {
		return loginResponse;
	}

	public void setLoginResponse(UserAuthResponse loginResponse) {
		this.loginResponse = loginResponse;
	}

}
