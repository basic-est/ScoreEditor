package com.nxsmatsumoto.scoreeditor_lyricsguitarcode_;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

public class UnderDevelopmentDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog_title_UnderDev);
        builder.setMessage(R.string.dialog_msg_UnderDev);
        builder.setPositiveButton(R.string.dialog_btn__UnderDev, new DialogButtonClickListner());
        AlertDialog dialog = builder.create();
        return dialog;
    }

    private class DialogButtonClickListner implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            getActivity().finish();
        }
    }
}
