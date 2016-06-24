package br.com.consultafacil.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Locale;

import br.com.consultafacil.R;

/**
 * Created by Isaias on 22/06/2016.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance(new Locale("pt", "BR"));
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        EditText dataNascimento = (EditText) getActivity().findViewById(R.id.dataNascimento);
        String data = String.valueOf(day) + "/" + String.valueOf(month + 1) + "/" + String.valueOf(year);
        dataNascimento.setText(data);
    }
}
