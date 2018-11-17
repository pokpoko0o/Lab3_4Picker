package my.edu.tarc.lab34;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showDatePicker(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),getString(R.string.datepicker));
    }

    private int MinimumBasicAmount = 0;
    public void processDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = (month_string +
                "/" + day_string + "/" + year_string);

        TextView textViewMsg = findViewById(R.id.selectDateButton);
        textViewMsg.setText(dateMessage);
        int Age = Calendar.getInstance().get(Calendar.YEAR) - year;
        TextView AgeView = findViewById(R.id.textViewAge);
        AgeView.setText(String.valueOf(Age));

        if(Age >=16 && Age <= 20)
            MinimumBasicAmount = 5000;
        else if(Age >= 21 && Age<=25)
            MinimumBasicAmount = 14000;
        else if(Age >= 26 && Age <=30)
            MinimumBasicAmount = 29000;
        else if(Age >= 31 && Age <=35)
            MinimumBasicAmount = 50000;
        else if(Age >= 36 && Age <=40)
            MinimumBasicAmount = 78000;
        else if(Age >= 41 && Age <=45)
            MinimumBasicAmount = 116000;
        else if(Age >= 46 && Age <=50)
            MinimumBasicAmount = 165000;
        else if(Age >= 51)
            MinimumBasicAmount = 228000;
    }

    public void Calculate(View view){
        TextView TxtViewCurrentBalance = findViewById(R.id.editTextAccountBalance);
        int CurrentBalance = Integer.parseInt(TxtViewCurrentBalance.getText().toString());

        TextView TextViewEligible = findViewById(R.id.textViewEligibleAmount);
        if(CurrentBalance >= MinimumBasicAmount){
            float amountAvailable = (CurrentBalance - MinimumBasicAmount);
            amountAvailable = amountAvailable * 0.3f;
            TextViewEligible.setText(String.valueOf(amountAvailable));
        }
        else{
            TextViewEligible.setText("Not Eligible for this investment");
        }
    }

    public void Reset(View view){
        TextView TxtViewCurrentBalance = findViewById(R.id.editTextAccountBalance);
        TxtViewCurrentBalance.setText("");
        TextView TextViewEligible = findViewById(R.id.textViewEligibleAmount);
        TextViewEligible.setText("0");
        TextView textViewMsg = findViewById(R.id.selectDateButton);
        textViewMsg.setText("Select date of birth");
        TextView AgeView = findViewById(R.id.textViewAge);
        AgeView.setText("0");
    }
}
