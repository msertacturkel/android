package tr.com.agmlab.utils;



import tr.com.agmlab.enums.ErrorCode;


public class LsFoException extends RuntimeException {
	private static final long serialVersionUID = -443547555719311485L;
	
	private ErrorCode errorCode;
    private Exception exception;
    private Object extraData;
    
    public LsFoException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public LsFoException(ErrorCode errorCode, Exception exception) {
        super(exception.getCause());

        this.errorCode = errorCode;
        this.exception = exception;
    }
    
    public LsFoException(ErrorCode errorCode, Object extraData) {
        this.errorCode = errorCode;
        this.extraData = extraData;
    }

	public ErrorCode getErrorCode() {
		return errorCode;
	}
	
	public Exception getException() {
		return exception;
	}

	public Object getExtraData() {
		return extraData;
	}

	public void setExtraData(Object extraData) {
		this.extraData = extraData;
	}
	
}
