package com.manuelpeinado.numquest.views;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextView;

public class TimerView extends TextView implements Runnable {
	private static final int PERIOD = 1000;
	private static final int TOTAL_TIME = 60;
	private long startTime;
	private Handler handler;
	private Listener listener;
	
	public interface Listener {
		void onTimerExpired();
	}

	public TimerView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public void resume() {
		startTime = System.currentTimeMillis();
		setValue(TOTAL_TIME);
		handler = new Handler();
		handler.postDelayed(this, PERIOD);
	}
	
	@Override
	public void run() {
		if (handler == null) {
			return;
		}
		long ellapsed = (System.currentTimeMillis() - startTime) / 1000;
		int remaining = TOTAL_TIME - (int)ellapsed;
		if (remaining < 0) {
			remaining = 0;
			if (listener != null) {
				listener.onTimerExpired();
			}
		}
		if (handler == null) {
			return;
		}
		setValue(remaining);
		handler.postDelayed(this, PERIOD);
	}
	
	private void setValue(int value) {
		setText(Integer.toString(value));
	}

	public void setListener(Listener listener) {
		this.listener = listener;
	}

	public void stop() {
		handler.removeCallbacks(this);
		handler = null;
	}
}
