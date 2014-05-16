package tr.com.agmlab.utils;



import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.TimeZone;

import com.agmlab.localsearch.core.domain.base.ResultCode;
import com.agmlab.localsearch.web.util.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonUtil {
	
	public static Response getResponseObject(Class<?> resultRootClass, String resultJson) {
		JsonObject jsonResult = getJsonObject(resultJson);
		
		Object data = getObject(resultRootClass, jsonResult.get("data").toString());
		boolean success = jsonResult.get("success").getAsBoolean();
		ResultCode resultCode = (ResultCode)getObject(ResultCode.class, jsonResult.get("resultCode").toString());
		
		Response response = new Response(data);		
		response.setSuccess(success);
		response.setResultCode(resultCode);
		return response;
	}
	
	public static Object getObject(Class<?> dtoClass,String json) {
		
		Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.STATIC).
				registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
					@Override
					public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)  {
						return new Date(json.getAsJsonPrimitive().getAsLong() - TimeZone.getTimeZone("Turkey").getRawOffset()); 
					}
				}).
				create();
		

		return gson.fromJson(json, dtoClass);
	}
	
	public static String getJson(Object dto) {
		Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
		String str = "";
		str = gson.toJson(dto);
		return str;
	}
	
	public static JsonObject getJsonObject(String jsonStr) {
		JsonParser jParser = new JsonParser();
		return jParser.parse(jsonStr).getAsJsonObject();
	}
	
	public static boolean isResultSuccess(String resultStr) {
		JsonObject jsonResult = null;
		try {
			jsonResult = getJsonObject(resultStr);
			boolean success=jsonResult.get("success").getAsBoolean();
			return success;
		} catch(Exception exc) {
			return false;
		}
	}

}
