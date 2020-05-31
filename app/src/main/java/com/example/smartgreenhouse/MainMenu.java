package com.example.smartgreenhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class MainMenu extends AppCompatActivity {

    public static final LinkedList<Preset> listOfPresets = new LinkedList<Preset>();
    File presetFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        presetFile = new File(MainMenu.this.getFilesDir(), "presets.txt");

        try {
            getPresetsList();
        } catch (IOException e) {
            try {
                presetFile.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
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

    protected void onStop() {
        super.onStop();

        BufferedWriter br = null;
        try {
            br = new BufferedWriter(new FileWriter(presetFile));
            PrintWriter pw = new PrintWriter(presetFile.getPath());
            pw.close();
            for(int i = 0; i < listOfPresets.size()-1; i++)
            {
                Preset pr = listOfPresets.get(i);
                String name = pr.getName();
                String temp =  String.valueOf(pr.getTemperature());
                String humidity = String.valueOf(pr.getHumidity());
                br.write(name,0, name.length());
                br.newLine();
                br.write(temp,0, temp.length());
                br.newLine();
                br.write(humidity,0, humidity.length());
                br.newLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getPresetsList() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(presetFile));
        String line = null;
        while((line = reader.readLine()) != null)
        {
            Preset pr = new Preset();
            pr.setName(line);
            pr.setTemperature(Integer.parseInt(reader.readLine()));
            pr.setHumidity(Integer.parseInt(reader.readLine()));
            listOfPresets.addLast(pr);
        }
        reader.close();
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
