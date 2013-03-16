package com.manuelpeinado.numquest;

import java.util.Random;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.manuelpeinado.numquest.dialogs.StartDialog;
import com.manuelpeinado.numquest.dialogs.SummaryDialog;
import com.manuelpeinado.numquest.views.ScoreView;
import com.manuelpeinado.numquest.views.TimerView;

public class MainActivity extends FragmentActivity implements StartDialog.Listener, TextWatcher, 
															  TimerView.Listener, SummaryDialog.Listener {
	private static final int MAX_VALUE = 1000;
	private Random random = new Random();
	private int currentValue;
	private int operand;
	private int expectedResult;
	private EditText resultView;
	private TextView operandView;
	private TextView currentValueView;
	private TextView operationView;
	private View root;
	private TimerView timerView;
	private ScoreView scoreView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		root = findViewById(R.id.root);
		timerView = (TimerView)findViewById(R.id.timer);
		timerView.setListener(this);
		scoreView = (ScoreView)findViewById(R.id.score);
		currentValueView = (TextView) findViewById(R.id.currentValue);
		operationView = (TextView) findViewById(R.id.operation);
		operandView = (TextView) findViewById(R.id.operand);
		resultView = (EditText) findViewById(R.id.result);
		resultView.addTextChangedListener(this);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		hideViews();
		showStartDialog();
	}

	private void showStartDialog() {
		StartDialog dlg = StartDialog.newInstance();
		dlg.setListener(this);
		dlg.show(getSupportFragmentManager(), "startDlg");
	}

	@Override
	public void onStartButtonClicked() {
		restart();
	}

	private void restart() {
		scoreView.reset();
		showViews();
		generateInitialValue();
		generateOperation();
		timerView.resume();
	}

	private void showViews() {
		root.setVisibility(View.VISIBLE);
		resultView.requestFocus();
	}

	private void hideViews() {
		root.setVisibility(View.INVISIBLE);
		resultView.requestFocus();
	}

	private void nextStep() {
		resultView.setText("");
		setCurrentValue(expectedResult);
		generateOperation();
	}

	private void generateInitialValue() {
		int value = random.nextInt(100);
		setCurrentValue(value);
	}

	private void generateOperation() {
		while (!generateOperationImpl()) {
		}
	}

	private boolean generateOperationImpl() {
		Operation op = Operation.getRandom(random);
		operand = op.getRandomOperand(random);
		int result = op.apply(currentValue, operand);
		if (!isValidValue(result)) {
			return false;
		}
		expectedResult = result;
		setOperation(op, operand);
		return true;
	}

	private void setOperation(Operation operation, int operand) {
		operationView.setText(operation.toString());
		operandView.setText(Integer.toString(operand));
	}

	private boolean isValidValue(int value) {
		return value > 0 && value < MAX_VALUE;
	}

	private void setCurrentValue(int value) {
		currentValue = value;
		currentValueView.setText(Integer.toString(value));
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		String text = resultView.getText().toString().trim();
		try {
			int value = Integer.parseInt(text);
			if (value == expectedResult) {
				scoreView.incrementValue();
				nextStep();
			}
		} catch (Exception e) {
			return;
		}
	}

	@Override
	public void onTimerExpired() {
		timerView.stop();
		hideViews();
		showSummaryDialog();
	}

	private void showSummaryDialog() {
		SummaryDialog dlg = SummaryDialog.newInstance(scoreView.getValue());
		dlg.setListener(this);
		dlg.show(getSupportFragmentManager(), "summaryDlg");
	}

	@Override
	public void onReplayButtonClicked() {
		restart();
	}

	@Override
	public void onExitButtonClicked() {
		finish();
	}

	@Override
	public void afterTextChanged(Editable arg0) {
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	}
}
