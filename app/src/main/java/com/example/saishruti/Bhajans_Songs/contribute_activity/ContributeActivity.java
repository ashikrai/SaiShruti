package com.example.saishruti.Bhajans_Songs.contribute_activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saishruti.AppBarNavigation;
import com.example.saishruti.DatabaseHandler.DBHandler;
import com.example.saishruti.R;
import com.example.saishruti.Utils.MySharedPreferences;
import com.example.saishruti.Utils.constants.Constants;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class ContributeActivity extends AppBarNavigation {

    private Button bhajan;
    private Button song;
    private TextView title;
    private TextView lyrics;
    private Map<String, String[]> deityData;
    private Set<String> deityNames;
    private int selectedIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contribute);

        bhajan= findViewById(R.id.BhajanBtn);
        song= findViewById(R.id.SongBtn);
        title= findViewById(R.id.bhajan_song_title);
        lyrics= findViewById(R.id.bhajan_song_lyrics);
        deityNames= new LinkedHashSet<>();
        selectedIndex= 0;

        String ContributeType= getIntent().getStringExtra(getResources().getString(R.string.ContributeTypeIntent));
        if (ContributeType == null || ContributeType.equals("Bhajan")) {
            contributeBhajan(null);
        } else {
            contributeSong(null);
        }

        bhajan.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_check_circle_24, 0, 0, 0);

        DBHandler dbhandler= new DBHandler(this);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                deityData= dbhandler.getDeityMapFromDB();
//                deityNames= deityData.keySet().toArray(new String[0]);
                deityNames.add(getApplicationContext().getResources().getString(R.string.select_deity));
                deityNames.addAll(deityData.keySet());
                dbhandler.close();
            }
        });
        setupSpinner();
    }


    private void setupSpinner() {
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> deityDropdownAdpt= new ArrayAdapter(this, android.R.layout.simple_spinner_item, deityNames.toArray(new String[0])){
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;

                if (position == 0) {
                    tv.setTypeface(null, Typeface.BOLD);
                    tv.setTextColor(getResources().getColor(R.color.OrangeOrb));
                } else if(position == selectedIndex || position == 0) {
                    // Set the disable item text color{
                    tv.setTypeface(null, Typeface.BOLD_ITALIC);
                    tv.setTextColor(getResources().getColor(R.color.colorPrimary));
                } else {
                    tv.setTextColor(getResources().getColor(R.color.black));
                }
                return view;
            }
        };
//        ArrayAdapter<String> deityDropdownAdpt= new ArrayAdapter<>(this, R.layout.dropdown_item, deityNames);
        deityDropdownAdpt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(deityDropdownAdpt);

        String deityName= getIntent().getStringExtra(getResources().getString(R.string.DeityNameIntent));
        if (deityName != null) {
            selectedIndex= deityDropdownAdpt.getPosition(deityName);
            spinner.setSelection(selectedIndex);
        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                // Showing selected spinner item
                selectedIndex= position;
//                Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
    }

    // change the contribution type to Bhajan
    public void contributeBhajan(View v) {
        bhajan.setEnabled(false);
        song.setEnabled(true);

        bhajan.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_check_circle_24, 0, 0, 0);
        song.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);

        title.setHint(R.string.bhajan_title);
        lyrics.setHint(R.string.bhajan_lyrics);
        title.setText("");
        lyrics.setText("");
    }

    // change the contribution type to Song
    public void contributeSong(View v) {
        bhajan.setEnabled(true);
        song.setEnabled(false);

        song.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_check_circle_24, 0, 0, 0);
        bhajan.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);

        title.setHint(R.string.song_title);
        lyrics.setHint(R.string.song_lyrics);
        title.setText("");
        lyrics.setText("");
    }

    // Submit the contribution
    public void submitContribution(View v) {
        makeVibration();
        Spinner spinner= findViewById(R.id.spinner);
        if (title.getText().toString().length() == 0 || lyrics.getText().toString().length() == 0 || selectedIndex == 0) {
            TextView errMsg= findViewById(R.id.errorMessage);
            errMsg.setText(R.string.fields_empty);
            return;
        }
        DBHandler dbHandler= new DBHandler(ContributeActivity.this);
        if (!bhajan.isEnabled()) {
            String selectedDeity= spinner.getSelectedItem().toString();
            int DeityId= Integer.parseInt(this.deityData.get(selectedDeity)[0]);
            String Raag= ((TextView) findViewById(R.id.contributeRaag)).getText().toString();
            Log.d("ContributeActivity", "Selected Item"+selectedDeity+" Id "+DeityId);

            String result= dbHandler.contributeBhajan(title.getText().toString(), lyrics.getText().toString(), MySharedPreferences.getUserIdPref(), DeityId, Raag);
            if (result.equals(Constants.Success))
            {
                title.setText("");
                lyrics.setText("");
                ((TextView) findViewById(R.id.contributeRaag)).setText("");

                if (getIntent().getStringExtra(getResources().getString(R.string.DeityNameIntent)) == null)
                    selectedIndex= 0;
                spinner.setSelection(selectedIndex);
                Toast.makeText(this, "Bhajan Added successfully 👏", Toast.LENGTH_SHORT).show();
            } else {
                ((TextView) findViewById(R.id.errorMessage)).setText(result);
                Toast.makeText(this, "Please Try Again 😭", Toast.LENGTH_SHORT).show();
            }
        }
    }
}