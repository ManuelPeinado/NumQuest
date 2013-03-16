package com.manuelpeinado.numquest.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class ScoreView extends TextView {
	private int value;

	public ScoreView(Context context, AttributeSet attrs) {
		super(context, attrs);
		reset();
	}

	public void reset() {
		setValue(0);
	}

	public void incrementValue() {
		setValue(value + 1);
	}

	public int getValue() {
		return value;
	}

	private void setValue(int value) {
		this.value = value;
		setText(Integer.toString(value));
	}
}
