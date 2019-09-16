package com.example.gymapp;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Arrays;
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
    private int[] ids_button = {R.id.button_4, R.id.button_5, R.id.button_9, R.id.button_10};

    // Others
    private int count, period;
    private int[] counts;
    private int clicked;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // レイアウトファイル
        setContentView(R.layout.activity_running);

        count = 0;
        period = 100; // interval: 100msec

        counts = new int[4];
        Arrays.fill(counts, 0); // initialize counts

        clicked = 0; // saving clicked button

        // textView: Timer
        timerText = findViewById(R.id.text_clock);
        timerText.setText(dataFormat.format(0));

        // textView 記録たち
//        TextView t_4 = findViewById(R.id.text4);
//        TextView t_5 = findViewById(R.id.text5);
//        TextView t_9 = findViewById(R.id.text9);
//        TextView t_10 = findViewById(R.id.text10);

        // textView: Records
        texts = new TextView[ids_text.length];
        for (int i=0; i<ids_text.length; i++){
            texts[i] = findViewById(ids_text[i]);
        }
//        texts[0] = findViewById(R.id.text4);
//        texts[1] = findViewById(R.id.text5);
//        texts[2] = findViewById(R.id.text9);
//        texts[3] = findViewById(R.id.text10);

        // Buttons
        buttons = new Button[4];
        for (int i=0; i<ids_button.length; i++){
            buttons[i] = findViewById(ids_button[i]);
        }
//        buttons[0] = findViewById(R.id.button_4);
//        buttons[1] = findViewById(R.id.button_5);
//        buttons[2] = findViewById(R.id.button_9);
//        buttons[3] = findViewById(R.id.button_10);
        Button stop = findViewById(R.id.button_stop);
        Button back = findViewById(R.id.button_back);

        // 押したとき
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
                case R.id.button_4:
                    // タイマーがすでに動いてるときは先に殺す
                    if (count != 0) {
                        handler.removeCallbacks(runnable);
                        timerText.setText(dataFormat.format(0));
                        counts[clicked] += count;
                        texts[clicked].setText(dataFormat.format(counts[clicked]*period));
                        count = 0;
                    }
                    clicked = 0;
                    handler.post(runnable);
                    break;

                case R.id.button_5:
                    if (count != 0) {
                        handler.removeCallbacks(runnable);
                        timerText.setText(dataFormat.format(0));
                        counts[clicked] += count;
                        texts[clicked].setText(dataFormat.format(counts[clicked]*period));
                        count = 0;
                    }
                    clicked = 1;
                    handler.post(runnable);
                    break;

                case R.id.button_9:
                    if (count != 0) {
                        handler.removeCallbacks(runnable);
                        timerText.setText(dataFormat.format(0));
                        counts[clicked] += count;
                        texts[clicked].setText(dataFormat.format(counts[clicked]*period));
                        count = 0;
                    }
                    clicked = 2;
                    handler.post(runnable);
                    break;

                case R.id.button_10:
                    if (count != 0) {
                        handler.removeCallbacks(runnable);
                        timerText.setText(dataFormat.format(0));
                        counts[clicked] += count;
                        texts[clicked].setText(dataFormat.format(counts[clicked]*period));
                        count = 0;
                    }
                    clicked = 3;
                    handler.post(runnable);
                    break;

                case R.id.button_stop:
                    handler.removeCallbacks(runnable);
                    timerText.setText(dataFormat.format(0));
                    counts[clicked] += count;
                    texts[clicked].setText(dataFormat.format(counts[clicked]*period));
                    count = 0;
                    break;

                case R.id.button_back :
                    finish();
                    break;
            }
        }
    };
}
