package com.example.smartgreenhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;

public class MainMenu extends AppCompatActivity {

    public static final LinkedList<Preset> listOfPresets = new LinkedList<Preset>();
    File presetFile = new File(getApplicationContext().getFilesDir(), "presets.txt");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        try {
            getPresetsList();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Button button_add = (Button)findViewById(R.id.button2);
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                goToAddPreset();
            }
        });

        Button button_edit = (Button)findViewById(R.id.button3);
        button_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                goToEditPreset();
            }
        });

        Button button_upload = (Button)findViewById(R.id.button4);
        button_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                goToUploadPreset();
            }
        });

        Button button_params = (Button)findViewById(R.id.button5);
        button_params.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                goToParams();
            }
        });
    }

    private void getPresetsList() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(presetFile.getPath()));
        String line = null;
        while((line = reader.readLine()) != null)
        {
            Preset pr = new Preset();
            pr.setName(line);
            pr.setTemperature(Integer.getInteger(reader.readLine()));
            pr.setHumidity(Integer.getInteger(reader.readLine()));
        }
    }

    private void goToAddPreset()
    {
        Intent intent_add = new Intent(this, AddPreset.class);
        startActivity(intent_add);
    }

    private void goToEditPreset()
    {
        Intent intent_edit = new Intent(this, EditPreset.class);
        startActivity(intent_edit);
    }

    private void goToUploadPreset()
    {
        Intent intent_upload = new Intent(this, UploadPreset.class);
        startActivity(intent_upload);
    }

    private void goToParams()
    {
        Intent intent_params = new Intent(this, GHParams.class);
        startActivity(intent_params);
    }
}
