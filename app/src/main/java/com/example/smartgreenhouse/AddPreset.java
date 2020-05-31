package com.example.smartgreenhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class AddPreset extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_preset);

        final TextView tempVal = (TextView)findViewById(R.id.textView4);
        tempVal.setText("26");

        final EditText nameOfPreset = (EditText)findViewById(R.id.editText);
        final RadioGroup hydration = (RadioGroup)findViewById(R.id.hydration);
        hydration.check(R.id.medium);

        Button buttonPlus = (Button)findViewById(R.id.button7);
        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                addTemp(tempVal);
            }
        });

        Button buttonMinus = (Button)findViewById(R.id.button6);
        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                minusTemp(tempVal);
            }
        });

        Button buttonAddPreset = (Button)findViewById(R.id.button8);
        buttonAddPreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                addPreset(nameOfPreset.getText().toString(),
                          Integer.parseInt((tempVal.getText()).toString()),
                          hydration);
            }
        });

    }

    private void addPreset(String name, int temperature, RadioGroup hydration) {
        int level;
        if(hydration.getCheckedRadioButtonId() == R.id.high)
            level = 2;
        else if(hydration.getCheckedRadioButtonId() == R.id.medium)
            level = 1;
        else
            level = 0;

        Preset pr = new Preset(name, temperature, level);
        MainMenu.listOfPresets.add(pr);
        System.out.println(MainMenu.listOfPresets);
        finish();
    }

    private void minusTemp(TextView tempVal) {
        int tmp = Integer.parseInt((tempVal.getText()).toString());
        if(tmp > 16)
        {
            tmp--;
            tempVal.setText(String.valueOf(tmp));
        }
    }

    private void addTemp(TextView tempVal) {
        int tmp = Integer.parseInt((tempVal.getText()).toString());
        System.out.println(tmp);
        if(tmp < 40)
        {
            tmp++;
            tempVal.setText(String.valueOf(tmp));
        }
    }
}
