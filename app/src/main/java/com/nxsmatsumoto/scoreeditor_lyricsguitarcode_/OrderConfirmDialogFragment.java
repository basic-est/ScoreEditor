package com.nxsmatsumoto.scoreeditor_lyricsguitarcode_;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

public class OrderConfirmDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog_title);
        builder.setMessage(R.string.dialog_msg);
        builder.setPositiveButton(R.string.dialog_btn_ok, new DialogButtonClickListner());
        builder.setNegativeButton(R.string.dialog_btn_ng, new DialogButtonClickListner());
        AlertDialog dialog = builder.create();
        return dialog;
    }

    private class DialogButtonClickListner implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            String msg = "";
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    getActivity().finish();
                    msg = getString(R.string.dialog_ok_toast);
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    msg = getString(R.string.dialog_ng_toast);
                    break;
            }
            Toast.makeText(getActivity(), msg ,Toast.LENGTH_LONG).show();
        }
    }
}
