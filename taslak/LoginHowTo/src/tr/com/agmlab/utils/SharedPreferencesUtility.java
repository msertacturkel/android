package tr.com.agmlab.utils;



import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtility extends Activity{
	public static final String PREFS_NAME = "ls_preferences";
	private static String ACCOUNT_TYPE; // must be packagename
	
	public static void setAccountType(String accountType) {
		ACCOUNT_TYPE = accountType;
		
	}
	
	public static void writeString(Context context, String value, String key){
		SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString(key, value);
		editor.commit();
	}
	
	public static void removeString(Context context, String key){
		SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.remove(key);
		editor.commit();
	}
	
	public static String getString(Context context, String key){
		SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
		String returnValue = settings.getString(key, "");
		return returnValue;
	}
	
    public static Account findMyAccount(Context context){
    	Account myAccount = null;
    	AccountManager accMan = AccountManager.get(context);
    	Account[] accounts = accMan.getAccountsByType(ACCOUNT_TYPE);
    	for (int i = 0; i < accounts.length; i++){
    		if (accounts[i].name.equals(getString(context, "username"))){
    			myAccount = accounts[i];
    		}
    	}
    	if (myAccount == null){
    		return null;
    	}else{
    		return myAccount;
    	}
    }
    
    public static boolean updatePassword(Context context, String password){
    	AccountManager accMan = AccountManager.get(context);
    	Account myAccount = findMyAccount(context);
    	if (myAccount != null){
    		accMan.setPassword(myAccount, password);
    		return true;
    	}else{
    		return false;
    	}
    }
    
    public static String getUserName(Context context) {
    	return getString(context, "username");
    }
    
    public static void setUserName(Context context, String username) {
    	writeString(context, username, "username");
    }
    
    public static String getPassword(Context context){
    	AccountManager accMan = AccountManager.get(context);
    	Account myAccount = findMyAccount(context);
    	if (myAccount != null){
    		return accMan.getPassword(myAccount);
    	}else{
    		return null;
    	}
    }
    
    public static boolean addAccount(Context context, String username, String password) {
    	AccountManager accMan = AccountManager.get(context);
    	Account account = new Account(username, ACCOUNT_TYPE);
    	return accMan.addAccountExplicitly(account, password, null);
    }
    
    public static boolean addOrUpdateAccount(Context context, String username, String password) {
    	Account account = findMyAccount(context);
    	
    	if(account == null) {
    		return addAccount(context, username, password);
    	} else {
    		setUserName(context, username);
    		return updatePassword(context, password);
    	}
    }
}
