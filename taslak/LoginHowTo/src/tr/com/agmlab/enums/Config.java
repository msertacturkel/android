package tr.com.agmlab.enums;



/**
 * 
 * @author Muhammet Dinc
 *
 */
public enum Config {
	APPLICATION_NAME("application.name","LocalsearchFO"),
	API_KEY("api.key","APIKEY1"),
	APPLICATION_BUILD_VERSION("application.build.version", "9"),
	APPLICATION_LANG("application.lang","tr_TR"),
	APPLICATION_KEYSTORE("application.keystore","/keystore/"),
	APPLICATION_TEMP_DIR("application.temp.dir",".lsFloMobile"),
	APPLICATION_URL("application.url", "http://api.test.scm.beezard.net"),
//	APPLICATION_URL("application.url", "http://10.111.183.12:8080/localsearch-rest/rest"),
	
	FORCE_UPDATE_URL("application.update.service","http://test.scm.beezard.net/mobile-updates/foAndroidUpdate.json"),
	FORCE_UPDATE_APK("application.update.service","http://test.scm.beezard.net/mobile-updates/fo-android.apk"),
	
	CDN_IMAGE_UPLOAD("cdn.image.upload","http://cdn.scm.beezard.net/sme/image/add"),
	
	HTTP_MAX_CONNECTION("http.max.connection","10"),
	HTTP_CONNECTION_TIMEOUT("http.connection.timeout","15000"),
	MAX_TASK_THREAD("max.task.thread","20"),
	MAX_EVENT_THREAD("max.event.thread","10"),
	MAX_ACTION_THREAD("max.action.thread","10"),
	
	MAX_RUNNING_TASK_COUNT("max.running.task.count","10"),
	MAX_TASK_RUNNING_TIME("max.task.running.time","5000"),
	TASK_ERROR_RETRY_COUNT("task.error.retry.count","2"),
	
	ACCOUNT_USERNAME("account.username",""),

	SME_FILTER_PAGE_SIZE("sme.filter.page.size","15");
	
	/**
	 * cnfig key
	 */
	private String key;
	
	/**
	 * Default Value
	 */
	private String value;
	
	private Config(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}
	
	public Integer getIntValue() {
		return Integer.parseInt( value );
	}
	
	public void setValue(String value){
		this.value = value;
	}
}
