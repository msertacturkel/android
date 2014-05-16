package tr.com.agmlab.tasks;

import android.os.AsyncTask;

public abstract class BaseTask<T> extends AsyncTask<Void, Integer, T> {
	
	private Object result;
	
	public BaseTask(){
		
	}
	
	public BaseTask(Object result){
		setResult(result);
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	@Override
	protected T doInBackground(Void... params) {
		// TODO Auto-generated method stub
		return null;
	}
}
