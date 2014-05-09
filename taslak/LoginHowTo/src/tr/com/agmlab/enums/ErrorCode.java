package tr.com.agmlab.enums;



/**
 * Error codes
 * 
 * @author Muhammet Dinç
 * 
 */
public enum ErrorCode {
	// --- Success ---
	SUCCESS(100, "Success", "success"),

	// --- Invalid Input JSON ---
	INVALID_JSON(200, "Invalid JSON Error", "json.invalid.error"),

	// --- Service Errors ----
	SERVICE_CALL_EXCEPTION(300, "Service call exception",
			"service.call.exception"), BAD_SERVICE_RETURN_VALUE(301,
			"Bad service return value", "service.return.bad.value"), BAD_SERVICE_PARAM(
			302, "Bad service params", "service.param.bad.value"),

	UNKNOWN_ERROR(900, "Unknown Error", "error.unknown");

	private int errorCode;
	private String message;
	private String userMessage;

	private ErrorCode(int errorCode, String message, String userMessage) {
		this.errorCode = errorCode;
		this.message = message;
		this.userMessage = userMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getMessage() {
		return message;
	}

	public String getUserMessage() {
		return userMessage;
	}

	@Override
	public String toString() {
		return "{ErrorCode:" + errorCode + ",Message:" + message + "}";
	}
}
