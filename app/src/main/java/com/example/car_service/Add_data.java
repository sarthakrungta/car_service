package com.example.car_service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;


public class Add_data extends AppCompatActivity {
    Button button1;
    EditText name, car, number;
    DatePicker date;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        button1 = findViewById(R.id.save_button);
        name = findViewById(R.id.getName);
        car = findViewById(R.id.getCar);
        number = findViewById(R.id.getNumber);
        date = findViewById(R.id.datePicker1);




        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                data_model data;
                if (name.getText().toString().isEmpty() || car.getText().toString().isEmpty()){
                    Toast.makeText(Add_data.this, "Please fill details", Toast.LENGTH_LONG).show();
                }
                else{
                    try{
                        data = new data_model(-1, name.getText().toString(), car.getText().toString(), datepickertostring(date));
                        Toast.makeText(Add_data.this, "Success" , Toast.LENGTH_SHORT).show();


                    }catch (Exception e){
                        Toast.makeText(Add_data.this, "Error creating data", Toast.LENGTH_SHORT).show();
                        data = new data_model(-1, "Error", "Error", "Error");
                    }

                    if(number.getText().toString().length() != 10){
                        databaseHelper databaseHelper = new databaseHelper(Add_data.this);
                        boolean b = databaseHelper.addOne(data);
                    }
                    else{
                        sendSMS(name.getText().toString(), car.getText().toString(), datepickertostring(date), number.getText().toString());
                        databaseHelper databaseHelper = new databaseHelper(Add_data.this);
                        boolean b = databaseHelper.addOne(data);
                    }
                    finish();

                }





            }
        });
    }

    protected String datepickertostring(DatePicker datePicker){
        int year = datePicker.getYear();
        String month;
        String day;


        if (String.valueOf(datePicker.getMonth()+1).length() == 1){
            month = "0" + String.valueOf(datePicker.getMonth()+1);
        }
        else{
            month = String.valueOf(datePicker.getMonth()+1);
        }

        if (String.valueOf(datePicker.getDayOfMonth()).length() == 1){
            day = "0" + String.valueOf(datePicker.getDayOfMonth());
        }
        else{
            day = String.valueOf(datePicker.getDayOfMonth());
        }

        return day + "/" + month + "/" + String.valueOf(year);

    };

    protected void sendSMS(String custname, String custcar, String custdate, String number){

        Log.i("SEND SMS", "");
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
        String date_after = six_months_date(custdate);
        smsIntent.setData(Uri.parse("smsto:"));
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address", new String("+91" + number));
        smsIntent.putExtra("sms_body", "Dear " + custname +", Your car, " + custcar + " has been serviced on " + custdate + ". Please get your car back for servicing on " + date_after);

        try{
            startActivity(smsIntent);
            finish();
            Log.i("SMS SENDING COMPLETED", "");
        }catch(android.content.ActivityNotFoundException ex){
            Toast.makeText(Add_data.this, "SMS FAILED", Toast.LENGTH_SHORT).show();
        }

    }

    protected String six_months_date(String date){
        String sday = date.split("/")[0];
        String smonth = date.split("/")[1];
        String sYear = date.split("/")[2];

        int day = Integer.parseInt(sday);
        int month = Integer.parseInt(smonth);
        int year = Integer.parseInt(sYear);

        if (month + 6 > 12){
            return sday+"/"+ (month - 6) + "/" + (year+1);
        }
        else return sday+"/"+ (month + 6) + "/" + (year);

    }
}
