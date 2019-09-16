package com.example.gymapp;

import android.content.res.Resources;
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
import java.util.Arrays;
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
    private int[] ids_text = {R.id.text4, R.id.text5, R.id.text9, R.id.text10};

    // Buttons
    private Button[] buttons;
    private Integer[] ids_button = {R.id.button_4, R.id.button_5, R.id.button_9, R.id.button_10};

    // Others
    private int count, period;
    private int[] counts;
    private int clicked;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Layout
        setContentView(R.layout.activity_running);

        count = 0;
        period = 100; // interval: 100msec

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
                        timerText.setText(dataFormat.format(0));
                        counts[clicked] += count;
                        texts[clicked].setText(dataFormat.format(counts[clicked]*period));
                        texts[clicked].setBackgroundColor(Color.argb(0,0,0,0));
                        count = 0;
                    }

                    if (view.getId() != R.id.button_stop){
                        List<Integer> list = Arrays.asList(ids_button);
                        int index = list.indexOf(view.getId());
                        texts[index].setBackgroundResource(R.color.colorA);
                        clicked = index;
                        handler.post(runnable);
                    }

                    break;
            }
        }
    };
}
