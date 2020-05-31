package com.example.smartgreenhouse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Edit extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent intent = getIntent();
        final int idx = intent.getIntExtra("PRESET_IDX", 0);
        Preset preset = MainMenu.listOfPresets.get(idx);

        final TextView tempVal = (TextView)findViewById(R.id.textView4);
        tempVal.setText(String.valueOf(preset.getTemperature()));

        final EditText nameOfPreset = (EditText)findViewById(R.id.editText);
        nameOfPreset.setText(preset.getName());
        final RadioGroup hydration = (RadioGroup)findViewById(R.id.hydration);
        if(preset.getHumidity() == 2)
            hydration.check(R.id.high);
        else if(preset.getHumidity() == 1)
            hydration.check(R.id.medium);
        else
            hydration.check(R.id.low);


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
                editPreset(idx,
                           nameOfPreset.getText().toString(),
                           Integer.parseInt((tempVal.getText()).toString()),
                           hydration);

                Intent intentMainMenu = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(intentMainMenu);
            }
        });

        Button buttonDeletePreset = (Button)findViewById(R.id.button9);
        buttonDeletePreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                deletePreset(idx);
                Intent intentMainMenu = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(intentMainMenu);
            }
        });
    }

    private void deletePreset(int idx) {
        MainMenu.listOfPresets.remove(idx);
    }

    private void editPreset(int idx, String name, int temperature, RadioGroup hydration) {
        int level;
        if(hydration.getCheckedRadioButtonId() == R.id.high)
            level = 2;
        else if(hydration.getCheckedRadioButtonId() == R.id.medium)
            level = 1;
        else
            level = 0;

        Preset pr = new Preset(name, temperature, level);
        MainMenu.listOfPresets.set(idx, pr);
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


