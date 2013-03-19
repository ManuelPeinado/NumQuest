package com.manuelpeinado.numquest.views;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextView;

public class TimerView extends TextView implements Runnable {
	private static final int PERIOD = 1000;
	private static final int TOTAL_TIME = 60000;
	private long startTime;
	private Handler handler;
	private Listener listener;
	private int extraTime;
	private int remaining;
	private int ellapsed;

	public interface Listener {
		void onTimerExpired();
	}

	public TimerView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void resume() {
		startTime = System.currentTimeMillis();
		handler = new Handler();
		run();
	}

	@Override
	public void run() {
		if (handler == null) {
			return;
		}
		ellapsed = (int)(System.currentTimeMillis() - startTime);
		recomputeRemaining();
		if (remaining < 0) {
			remaining = 0;
			if (listener != null) {
				listener.onTimerExpired();
			}
		}
		if (handler == null) {
			return;
		}
		updateValue();
		handler.postDelayed(this, PERIOD);
	}

	private void recomputeRemaining() {
		remaining = TOTAL_TIME - ellapsed + extraTime;
	}

	public void setListener(Listener listener) {
		this.listener = listener;
	}

	public void stop() {
		handler.removeCallbacks(this);
		handler = null;
	}

	public void extendTime(int millis) {
		extraTime += millis;
		recomputeRemaining();
		updateValue();
	}

	private void updateValue() {
		setText(Integer.toString(remaining / 1000));
	}
}
