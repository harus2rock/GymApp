package com.example.gymapp;

import android.content.ContentValues;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class RunningActivity extends AppCompatActivity {

    private Handler handler = new Handler();

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            count ++;
            timerText.setText(dataFormat.format(count*period));
            handler.postDelayed(this, period); // post after 100msec
        }
    };

    // Timer
    private TextView timerText;
    private SimpleDateFormat dataFormat = new SimpleDateFormat("mm:ss.S", Locale.US);

    // Records
    private TextView[] texts;
    private Integer[] ids_text = {R.id.text4, R.id.text5, R.id.text9, R.id.text10};

    // Buttons
    private Button[] buttons;
    private Integer[] ids_button = {R.id.button_4, R.id.button_5, R.id.button_9, R.id.button_10};

    // Speeds
    private Float[] speeds = {(float)4, (float)5.5, (float)9, (float)10};

    // Others
    private int count, period;
    private int[] counts;
    private int clicked;

    // Saving data
    private RunningOpenHelper helper;
    private  SQLiteDatabase db;
    ArrayList<ContentValues> insertValues = new ArrayList<ContentValues>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Layout
        setContentView(R.layout.activity_running);

        count = 0;
        Resources res = getResources();
        period = res.getInteger(R.integer.period); // interval: msec
        counts = new int[4];
        Arrays.fill(counts, 0); // initialize counts

        clicked = 0; // Saving clicked button

        // TextView: Timer
        timerText = findViewById(R.id.text_clock);
        timerText.setText(dataFormat.format(0));

        // TextView: Records
        texts = new TextView[ids_text.length];
        for (int i=0; i<ids_text.length; i++){
            texts[i] = findViewById(ids_text[i]);
        }

        // Buttons
        buttons = new Button[ids_button.length];
        for (int i=0; i<ids_button.length; i++){
            buttons[i] = findViewById(ids_button[i]);
        }
        Button stop = findViewById(R.id.button_stop);
        Button back = findViewById(R.id.button_back);

        // When buttons are clicked
        for (Button b : buttons) {
            b.setOnClickListener(buttonClick);
        }
        stop.setOnClickListener(buttonClick);
        back.setOnClickListener(buttonClick);
    }

    private View.OnClickListener buttonClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_back :
                    finish();
                    break;

                default:
                    // Stop callbacks when other button was clicked before
                    if (count != 0) {
                        handler.removeCallbacks(runnable);

                        // Reset timer
                        timerText.setText(dataFormat.format(0));

                        // Add data to insertValues
                        addData(speeds[clicked], count);

                        // Sum counts
                        counts[clicked] += count;

                        // Display counts and distance
                        String time = String.valueOf(dataFormat.format(counts[clicked]*period));
                        Float dist = counts[clicked]*speeds[clicked]*period/3600000;
                        String distance = String.format(Locale.US," (%.2f km)", dist);
                        texts[clicked].setText((time+distance));
                        texts[clicked].setBackgroundColor(Color.argb(0,0,0,0));

                        // Reset count
                        count = 0;
                    }

                    // Restart
                    if (view.getId() != R.id.button_stop){
                        List<Integer> list = Arrays.asList(ids_button);
                        int index = list.indexOf(view.getId());
                        texts[index].setBackgroundResource(R.color.colorA);
                        clicked = index;
                        handler.post(runnable);
                    } else {
                        if(helper == null) {
                            helper = new RunningOpenHelper(getApplicationContext());
                        }
                        if(db == null) {
                            db = helper.getWritableDatabase();
                        }

                        for (ContentValues values: insertValues) {
                            db.insert("runningdb", null, values);
                        }
                    }

                    break;
            }
        }
    };

    private void addData(float speed, int time) {
        ContentValues values = new ContentValues();
        values.put("speed", speed);
        values.put("time", time);

        insertValues.add(values);
    }

}
