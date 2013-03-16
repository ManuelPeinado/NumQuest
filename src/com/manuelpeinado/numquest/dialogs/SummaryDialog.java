package com.manuelpeinado.numquest.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import com.manuelpeinado.numquest.R;

public class SummaryDialog extends DialogFragment {
	private Listener listener;

	public interface Listener {
		void onReplayButtonClicked();
		void onExitButtonClicked();
	}

	public static SummaryDialog newInstance(int score) {
		SummaryDialog dlg = new SummaryDialog();
		Bundle args = new Bundle();
		args.putInt("score", score);
		dlg.setArguments(args);
		return dlg;
	}

	public void setListener(Listener listener) {
		this.listener = listener;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
		b.setPositiveButton(R.string.replay, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (listener != null) {
					listener.onReplayButtonClicked();
				}
			}
		});
		b.setNegativeButton(R.string.exit, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (listener != null) {
					listener.onExitButtonClicked();
				}
			}
		});
		b.setTitle(R.string.game_finished);
		Resources resources = getActivity().getResources();
		int score = getArguments().getInt("score");
		String scoreString = resources.getQuantityString(R.plurals.you_have_achieved_an_score_of, score, score);
		b.setMessage(scoreString);
		return b.create();
	}
}
