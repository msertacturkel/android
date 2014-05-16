package tr.com.agmlab.tasks;



import tr.com.agmlab.enums.Config;
import tr.com.agmlab.enums.LsService;
import tr.com.agmlab.events.LoginEvent;
import tr.com.agmlab.utils.LsFoException;
import tr.com.agmlab.utils.ServiceUtil;
import android.util.Log;

import com.agmlab.localsearch.core.domain.user.dto.UserSigninRequest;
import com.agmlab.localsearch.web.util.Response;

import de.greenrobot.event.EventBus;


public class LoginTask extends BaseTask<Response>{
	private String email;
	private String password;

	public LoginTask(String email, String password){
		setEmail(email);
		setPassword(password);
	}
	
	@Override
	protected void onPreExecute() {
	}
	
	@Override
	protected Response doInBackground(Void... params) {
		try {
			if(email==null || email.isEmpty() || password==null || password.isEmpty()) {
				return null;
			}
				
			UserSigninRequest requestDto = new UserSigninRequest();
			requestDto.setEmail(getEmail());
			requestDto.setPassword(getPassword());
			requestDto.setApiKey(Config.API_KEY.getValue());
			
			Response response = ServiceUtil.call(LsService.SIGN_IN, requestDto);

			return response;
		} catch(LsFoException e) {
			e.printStackTrace();
			Log.e("login_task", "Login exception, e:"+e);
			return null;
		}
	}
	
	@Override
	protected void onPostExecute(Response loginResponse) {
		try {
			EventBus.getDefault().post(LoginEvent.getLoginEventFromResponse(loginResponse));
		} catch (Exception e) {
			Log.e("login_task","Login task exception e:"+e);
			EventBus.getDefault().post(LoginEvent.getLoginEventFromResponse(null));
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
