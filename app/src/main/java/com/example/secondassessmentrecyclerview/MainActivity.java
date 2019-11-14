package com.example.secondassessmentrecyclerview;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.secondassessmentrecyclerview.adapter.DesignerAdapter;
import com.example.secondassessmentrecyclerview.model.Designer;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {


    AutoCompleteTextView autoCompleteTextView;
    EditText editTxtPhone, editTxtName, editTextdob, editTextemail, editTextaddress;
    RadioGroup radioGroup;
    Spinner spinner;
    Button button, btnView;

    //CalendarView calendarView;
    String name, gender, dob, country, address, email, phone, image;
    String[] countries = {"Select an Option", "Nepal", "USA", "India", "Korea", "Maldives", "Myanmar", "Pakistan", "Afganistan"};
    List<Designer> designerlist = new ArrayList<>();
    String[] imagesuggestion = {"hoyoungchi", "lindsaydeelohan", "manishrai","yubithapa","manishmalhotra","prabalgurung","guopei"};
    Calendar calendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener mydatepicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String mydateformat = "dd-MM-y";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(mydateformat, Locale.getDefault());
            editTextdob.setText(simpleDateFormat.format(calendar.getTime()));
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTxtName = findViewById(R.id.tblname);
        editTextemail = findViewById(R.id.tblemail);
        editTextaddress = findViewById(R.id.tbladdress);
        editTextdob = findViewById(R.id.tbldob);
        radioGroup = findViewById(R.id.tblgender);
        editTxtPhone = findViewById(R.id.tblphone);
        spinner = findViewById(R.id.spinerdata);
        button = findViewById(R.id.btnsubmit);
        btnView = findViewById(R.id.btnview);

        //for autocomplete Image name.
        autoCompleteTextView = findViewById(R.id.userimage);
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.select_dialog_item, imagesuggestion
        );
        autoCompleteTextView.setAdapter(stringArrayAdapter);
        autoCompleteTextView.setThreshold(1);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.countries, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        button.setOnClickListener(this);
        btnView.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);
        editTextdob.setOnClickListener(this);
        newSpinnerValue();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.tblmale) {
            gender = "Male";
        }

        if (checkedId == R.id.tblfemale) {
            gender = "Female";
        }

        if (checkedId == R.id.tblother) {
            gender = "Other";
        }

    }


    private void newSpinnerValue() {

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                country = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        name = editTxtName.getText().toString();
        dob = editTextdob.getText().toString();
        address = editTextaddress.getText().toString();
        email = editTextemail.getText().toString();
        phone = editTxtPhone.getText().toString();
        image = autoCompleteTextView.getText().toString();

        if (v.getId() == R.id.btnsubmit) {

            if (validate()) {
                String uri = "@drawable/" + image;
                int resID = getResources().getIdentifier(uri, null, getPackageName());
                String imageID = String.valueOf(resID);

                designerlist.add(new Designer(name, dob, gender, country, email, phone, address, imageID));
                Toast.makeText(this, "Designer details added successfully", Toast.LENGTH_SHORT).show();
            }
        }

        if (v.getId() == R.id.btnview) {
            Intent intent = new Intent(this, DesignerActivity.class);
            intent.putExtra("designerlist", (Serializable) designerlist);
            startActivity(intent);
        }

        if (v.getId() == R.id.tbldob) {
            new DatePickerDialog(this, mydatepicker,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show();
        }
    }

    //validation
    private boolean validate() {

        if (spinner.getSelectedItem().toString().trim().equals("Select an Option")) {
            Toast.makeText(this, "Select a Country to Proceed", Toast.LENGTH_SHORT).show();
            spinner.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(name)) {
            editTxtName.setError("Please enter Name");
            editTxtName.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(dob)) {
            editTextdob.setError("Please enter DoB");
            editTextdob.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(address)) {
            editTextaddress.setError("Please enter Address");
            editTextaddress.requestFocus();
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextemail.setError("Please enter Email");
            editTextaddress.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(gender)) {
            Toast.makeText(this, "Select Gender", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(country)) {
            Toast.makeText(this, "Select Country", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(phone)) {
            editTxtPhone.setError("Please enter Phone Number");
            editTxtPhone.requestFocus();
            return false;
        }
        if (!TextUtils.isDigitsOnly(phone)) {
            editTxtPhone.setError("Invalid Phone Number");
            editTxtPhone.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(image)) {
            autoCompleteTextView.setError("Please enter the name of image");
            autoCompleteTextView.requestFocus();
            return false;
        }
        return true;
    }
}


