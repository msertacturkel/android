package tr.com.agmlab.utils;



import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

public class ApplicationUtil {
	private static Activity activity = null;
	
	public static Activity getActivity() {
		return activity;
	}
	
	public static void setActivity(Activity act) {
		activity = act;
	}
	
	/**
	 * 
	 * @param oldVersion
	 * @param newVersion
	 * @return 1: if oldVersion < newVersion, 0: if oldVersion = newVersion, -1: if newVersion > oldVersion
	 * 
	 */
	public static int compareBuildVersion(String oldVersion, String newVersion) {
		try {
			Integer intOldVersion = Integer.parseInt(oldVersion);
			Integer intNewVersion = Integer.parseInt(newVersion);
			
			return intNewVersion.compareTo(intOldVersion);
		}catch(Exception e) {
			return 1;
		}
	}
	
	public static String getApplicationVersionName(Activity activity){
		PackageInfo pInfo;
		String versionName = null;
		
		try {
			pInfo = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0);
			versionName = pInfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
		return versionName;
	}
}
