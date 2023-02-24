package com.example.quakehistory;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.DialogFragment;

public class DialogFilter extends DialogFragment {
    OnDialogListener mListener;
    private Spinner spnMag;
    private EditText etMag;
    private Spinner spnCtry;

    public Dialog OnCreateDialog(@Nullable Bundle savedInstanceState) {
        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_filter, null);

        spnMag = v.findViewById(R.id.spnMag);
        etMag = v.findViewById(R.id.etMag);
        spnCtry = v.findViewById(R.id.spnCtry);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v);

        builder.setTitle(R.string.eartquake_filter)
                .setPositiveButton("Apply", null)
                .setNegativeButton("Cancel", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                });

        AlertDialog ad = builder.create();
        ad.setCanceledOnTouchOutside(false);

        ad.setOnShowListener(dialogInterface -> {
            Button btnApply = ad.getButton(AlertDialog.BUTTON_POSITIVE);
            btnApply.setOnClickListener(view -> {
                try {
                    String mag = etMag.getText().toString();
                    String magValue = spnMag.getSelectedItem().toString();
                    String ctry = spnCtry.getSelectedItem().toString();
                    mListener.onDialogPositiveClick(mag, magValue, ctry);
                    ad.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                    // TODO: handle exceptions
                }
            });
        });
        return ad;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + getString(R.string.implement_OnDialogList));
        }
    }

    public void onDetach() {
        if (mListener != null) {
            mListener = null;
        }
        super.onDetach();
    }
}
