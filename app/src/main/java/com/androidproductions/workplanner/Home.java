package com.androidproductions.workplanner;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.androidproductions.workplanner.fragments.BaseFragment;
import com.androidproductions.workplanner.fragments.ToDoFragment;

import java.util.Calendar;


public class Home extends Activity implements View.OnClickListener {

    private DatePickerDialog dialog;
    private Calendar cal;
    private EditText datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        cal = Calendar.getInstance();
        datePicker = (EditText) findViewById(R.id.datePicker);
        datePicker.setOnClickListener(this);
        updateDate();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        dialog = new DatePickerDialog(this, datePickerListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    private void setDate(int selectedYear,
                         int selectedMonth, int selectedDay)
    {
        cal.set(Calendar.DAY_OF_MONTH, selectedDay);
        cal.set(Calendar.MONTH, selectedMonth);
        cal.set(Calendar.YEAR, selectedYear);
        updateDate();
    }

    private void updateDate()
    {
        datePicker.setText(cal.get(Calendar.DAY_OF_MONTH) + " / " + (cal.get(Calendar.MONTH) + 1) + " / "
                + cal.get(Calendar.YEAR));
        getFragmentManager().beginTransaction()
                .add(R.id.container, BaseFragment.newInstance(ToDoFragment.class, cal.getTime()))
                .commit();
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            setDate(selectedYear,selectedMonth,selectedDay);
            dialog.dismiss();
            dialog = null;
        }
    };

}
