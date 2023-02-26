package com.example.quakehistory;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.quakehistory.db.EarthQuakeDB;

import java.util.ArrayList;

public class DialogFilter extends DialogFragment {
    OnDialogListener mListener;
    private Spinner spnMag;
    private EditText etMag;
    private Spinner spnCtry;

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_filter, null);

        spnMag = v.findViewById(R.id.spnMag);
        etMag = v.findViewById(R.id.etMag);
        initSpnCtry(v);


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.MyAlertDialogStyle);
        builder.setView(v);

        builder.setTitle(R.string.eartquake_filter)
                .setPositiveButton("Apply", null)
                .setNegativeButton("Cancel", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                });

        AlertDialog ad = builder.create();
        ad.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.dialog_bg));
        ad.setCanceledOnTouchOutside(false);

        ad.setOnShowListener(dialogInterface -> {
            Button btnApply = ad.getButton(AlertDialog.BUTTON_POSITIVE);

            btnApply.setOnClickListener(view -> {

                    String mag = spnMag.getSelectedItem().toString().trim();
                    String magValueStr = etMag.getText().toString().trim();
                    double magValue = 0.0;
                    if (!mag.isEmpty() && magValueStr.isEmpty())
                    {
                        Toast.makeText(getContext(), "Please enter a magnitude value", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else if (!magValueStr.isEmpty() && mag.isEmpty())
                    {
                        Toast.makeText(getContext(), "Please select a magnitude operator", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else if (mag.isEmpty() && magValueStr.isEmpty())
                    {
                        mag = "Any";
                        magValue = 0.0;
                    }
                    else {
                        magValue = Double.parseDouble(magValueStr);
                        if (magValue > 10.0)
                        {
                            Toast.makeText(getContext(), "Magnitude value must be less than 10.0", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    String ctry = spnCtry.getSelectedItem().toString();
                    mListener.onDialogPositiveClick(mag, magValue, ctry);
                    ad.dismiss();
            });
        });
        return ad;
    }

    private void initSpnCtry(View v)
    {
        ArrayList<String> ctryList = new ArrayList<>();
        ctryList.add("All");
        ctryList.addAll(EarthQuakeDB.getDatabase(getContext()).countryDao().getAllCountries());
        spnCtry = v.findViewById(R.id.spnCtry);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.spn_ctry_selected_ite, ctryList);
        adapter.setDropDownViewResource(R.layout.spn_ctry);
        spnCtry.setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context + getString(R.string.implement_OnDialogList));
        }
    }

    @Override
    public void onDetach() {
        if (mListener != null) {
            mListener = null;
        }
        super.onDetach();
    }
}
