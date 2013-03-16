package com.manuelpeinado.numquest.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import com.manuelpeinado.numquest.R;

public class StartDialog extends DialogFragment {
	private Listener listener;

	public interface Listener {
		void onStartButtonClicked();
	}

	public static StartDialog newInstance() {
		return new StartDialog();
	}

	public void setListener(Listener listener) {
		this.listener = listener;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
		b.setPositiveButton(R.string.start, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (listener != null) {
					listener.onStartButtonClicked();
				}
			}
		});
		b.setTitle(R.string.new_game);
		b.setTitle(R.string.press_to_start_new_game);
		return b.create();
	}
}
