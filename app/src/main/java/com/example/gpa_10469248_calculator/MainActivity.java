package com.example.gpa_10469248_calculator;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    private EditText Grade1, Grade2, Grade3, Grade4, Grade5;
    private Button Compute_GPA;
    private TextView textView;
    LinearLayout mainLayout;
    double grade1 = 0, grade2 = 0, grade3 = 0, grade4 = 0, grade5 = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView2);
        Grade1 = findViewById(R.id.editTextNumber);
        Grade2 = findViewById(R.id.editTextNumber2);
        Grade3 = findViewById(R.id.editTextNumber3);
        Grade4 = findViewById(R.id.editTextNumber4);
        Grade5 = findViewById(R.id.editTextNumber5);
        mainLayout = findViewById(R.id.linear_layout);
        Compute_GPA = findViewById(R.id.calculate_gpa_button);

        //resets the compute gpa button upon text entry
        resetButtonOnType(Grade1);
        resetButtonOnType(Grade2);
        resetButtonOnType(Grade3);
        resetButtonOnType(Grade4);
        resetButtonOnType(Grade5);


        //enables button functionality
        Compute_GPA.setOnClickListener(v -> {
            Button button = (Button) v;
            int grade_final;
            TextInputLayout tilcourse1 = findViewById(R.id.floating_hint_Course1);
            TextInputLayout tilcourse2 = findViewById(R.id.floating_hint_Course2);
            TextInputLayout tilcourse3 = findViewById(R.id.floating_hint_Course3);
            TextInputLayout tilcourse4 = findViewById(R.id.floating_hint_Course4);
            TextInputLayout tilcourse5 = findViewById(R.id.floating_hint_Course5);


            //resets error on entry fields
            tilcourse1.setErrorEnabled(false);
            tilcourse2.setErrorEnabled(false);
            tilcourse3.setErrorEnabled(false);
            tilcourse4.setErrorEnabled(false);
            tilcourse5.setErrorEnabled(false);

            //when button shows Clear Form, it enables different functionalities
            if (button.getText().equals("Clear Form")) {


                button.setText("Compute GPA");
                Grade1.setText("");
                Grade2.setText("");
                Grade3.setText("");
                Grade4.setText("");
                Grade5.setText("");
                textView.setText("");

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mainLayout.getWindowToken(), 0);

                return;
            }

            //checks all edit text for blanks
            if (TextUtils.isEmpty(Grade1.getText().toString())) {

                tilcourse1.setError("Cannot be blank");
                tilcourse1.setErrorEnabled(true);
            } else if (TextUtils.isEmpty(Grade2.getText().toString())) {

                tilcourse2.setError("Cannot be blank");
                tilcourse2.setErrorEnabled(true);
            } else if (TextUtils.isEmpty(Grade3.getText().toString())) {

                tilcourse3.setError("Cannot be blank");
                tilcourse3.setErrorEnabled(true);
            } else if (TextUtils.isEmpty(Grade4.getText().toString())) {

                tilcourse4.setError("Cannot be blank");
                tilcourse4.setErrorEnabled(true);
            } else if (TextUtils.isEmpty(Grade5.getText().toString())) {

                tilcourse5.setError("Cannot be blank");
                tilcourse5.setErrorEnabled(true);
            } else {
                //gets values from edit text
                grade1 = Double.parseDouble(Grade1.getText().toString());
                grade2 = Double.parseDouble(Grade2.getText().toString());
                grade3 = Double.parseDouble(Grade3.getText().toString());
                grade4 = Double.parseDouble(Grade4.getText().toString());
                grade5 = Double.parseDouble(Grade5.getText().toString());



                //Checks to to see inputted values are between 0 and 100 Inclusive, otherwise toast is called.
                if (inRange(grade1) && inRange(grade2) && inRange(grade3) && inRange(grade4) && inRange(grade5)) {
                    grade_final = (int)((grade1 + grade2 + grade3 + grade4 + grade5) / 5);

                    //changes text color depending on final GPA grade
                    if(grade_final <= 60){
                        mainLayout.setBackgroundColor(Color.parseColor("red"));
                    }
                    else if (grade_final <= 79){
                        mainLayout.setBackgroundColor(Color.parseColor("Yellow"));
                    }
                    else if (grade_final <= 100){
                        mainLayout.setBackgroundColor(Color.parseColor("Green"));
                    }

                    //updates textview to reflect the average GPA
                    textView.setText("Your GPA :" + grade_final);
                    button.setText("Clear Form");

                    //puts android keyboard away upon button click
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(mainLayout.getWindowToken(), 0);

                } else {
                    Toast.makeText(MainActivity.this, "Input range is 100 to 0 inclusive",
                            Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    private void resetButtonOnType(EditText grade) {
        grade.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Compute_GPA.setText("Compute GPA");


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private boolean inRange(double grade) {
        return (grade <= 100 && grade >= 0);
    }
}