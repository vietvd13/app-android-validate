package com.example.android_validate;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    Spinner elPropertyType;
    EditText elBedrooms;
    EditText elDateTimeAdding;
    EditText elMonthlyRentPrice;
    RadioGroup elFurnitureTypes;
    EditText elNameReporter;
    Button elBtnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        elBtnSubmit = findViewById(R.id.button_submit);

        elBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] arrValidate = {};
                List<String> listvalidate = new ArrayList<>(Arrays.asList((arrValidate)));

                String[] arrPropertyType = {"Flat", "House", "Bungalow"};
                String[] arrFurnitureTypes = {"Furnished", "Unfurnished", "PartFurnished"};

                List<String> listPropertyType = new ArrayList<>(Arrays.asList(arrPropertyType));
                List<String> listFurnitureTypes = new ArrayList<>(Arrays.asList(arrFurnitureTypes));

                elPropertyType = findViewById(R.id.property_type);
                String valPropertyType = elPropertyType.getSelectedItem().toString();

                elBedrooms = findViewById(R.id.bed_room);
                String valBedrooms = elBedrooms.getText().toString();

                elDateTimeAdding = findViewById(R.id.date_and_time_of_adding_the_property);
                String valDateTimeAdding = elDateTimeAdding.getText().toString();

                elMonthlyRentPrice = findViewById(R.id.monthly_rent_price);
                String valMonthlyRentPrice = elMonthlyRentPrice.getText().toString();

                elFurnitureTypes = findViewById(R.id.furniture_types);
                String valFurnitureTypes;
                if (elFurnitureTypes.getCheckedRadioButtonId() != -1) {
                    valFurnitureTypes = ((RadioButton)findViewById(elFurnitureTypes.getCheckedRadioButtonId())).getText().toString();
                } else {
                    valFurnitureTypes = "";
                }

                elNameReporter = findViewById(R.id.name_reporter);
                String valNameReporter = elNameReporter.getText().toString();

                if (!(listPropertyType.contains(valPropertyType))) {
                    listvalidate.add("Property Type");
                }

                if (!(validateNumber(valBedrooms))) {
                    listvalidate.add("Bed rooms");
                }

                if (!(validateDate(valDateTimeAdding))) {
                    listvalidate.add("Date and time of adding the Property");
                }

                if (!(validateNumber(valMonthlyRentPrice))) {
                    listvalidate.add("Monthly Rent Price");
                }

                if (!(listFurnitureTypes.contains(valFurnitureTypes))) {
                    listvalidate.add("Furniture types");
                }

                if (validateEmptyOrWhiteSpace(valNameReporter)) {
                    listvalidate.add("Name Reporter");
                }

                if (listvalidate.size() > 0) {
                    Toast toast = Toast.makeText(MainActivity.this, createMsg(listvalidate), Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(MainActivity.this, "You have successfully created the form!", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }

    public final static String createMsg(List validate) {
        String msg = "";
        for (int index = 0; index < validate.size(); index++) {
            msg += (validate.get(index) + ", ");
        }

        return "You entered " + msg + "incorrectly";
    }

    public final static boolean validateDate(String val)
    {
        return Pattern.compile("^\\d{4}[\\/.]\\d{1,2}[\\/.]\\d{1,2}$").matcher(val).matches();
    }

    public final static boolean validateEmptyOrWhiteSpace(String val)
    {
        return Pattern.compile("^\\s*$").matcher(val).matches();
    }

    public final static boolean validateNumber(String val)
    {
        return Pattern.compile("^\\d+$").matcher(val).matches();
    }
}