package com.example.smartgreenhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class EditPreset extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_preset);

        String[] array = new String[MainMenu.listOfPresets.size()];
        for(int i = 0; i < array.length; i++)
        {
            Preset temporary = MainMenu.listOfPresets.get(i);
            array[i] = temporary.getName();
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, R.id.label, array);
        ListView listView = (ListView) findViewById(R.id.preset_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent presetToEdit = new Intent(getApplicationContext(), Edit.class);
                presetToEdit.putExtra("PRESET_IDX", position);
                startActivity(presetToEdit);
            }
        });
    }
}
