package tr.com.agmlab.tasks;



import tr.com.agmlab.enums.LsService;
import tr.com.agmlab.events.LoginEvent;
import tr.com.agmlab.utils.LsFoException;
import tr.com.agmlab.utils.ServiceUtil;
import android.content.Context;
import android.util.Log;

import com.agmlab.localsearch.core.domain.user.dto.UserSocialSigninRequest;
import com.agmlab.localsearch.web.util.Response;

import de.greenrobot.event.EventBus;


public class FacebookLoginTask extends BaseTask<Response>{
	private Context context;
	private String accessToken;
	
	public FacebookLoginTask(Context context, String accessToken) {
		this.context = context;
		this.accessToken = accessToken;
	}
	

	@Override
	protected void onPreExecute() {
	}
	
	@Override
	protected Response doInBackground(Void... params) {
		try {
			UserSocialSigninRequest requestDto = new UserSocialSigninRequest();
			
			requestDto.setAccessToken(accessToken);
			
			Response response = ServiceUtil.call(LsService.FACEBOOK_LOGIN, requestDto);

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
}
