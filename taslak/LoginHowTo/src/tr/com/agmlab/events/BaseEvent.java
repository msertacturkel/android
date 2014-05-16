package tr.com.agmlab.events;

import com.agmlab.localsearch.core.domain.base.ResultCode;

public class BaseEvent {
	private ResultCode resultCode;
	private Long duration;
	private boolean success;

	public BaseEvent() {
	}

	public BaseEvent(ResultCode resultCode, Long duration, boolean success) {
		this.success = success;
		this.resultCode = resultCode;
		this.duration = duration;
	}

	public ResultCode getResultCode() {
		return resultCode;
	}

	public void setResultCode(ResultCode resultCode) {
		this.resultCode = resultCode;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
