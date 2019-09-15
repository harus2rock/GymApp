package com.example.gymapp;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class RunningActivity extends AppCompatActivity {

    private Handler handler = new Handler();

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            count ++;
            timerText.setText(dataFormat.format(count*period));
            handler.postDelayed(this, period); // 100msec後にpost
        }
    };

    private TextView timerText;
    private SimpleDateFormat dataFormat = new SimpleDateFormat("mm:ss.S", Locale.US);

    private int count, period;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // レイアウトファイル
        setContentView(R.layout.activity_running);

        count = 0;
        period = 100; // 100msec間隔

        // textView タイマー表示箇所
        timerText = findViewById(R.id.text_clock);
        timerText.setText(dataFormat.format(0));

        // ボタンたち
        Button start = findViewById(R.id.button_start);
        Button stop = findViewById(R.id.button_stop);
        Button back = findViewById(R.id.button_back);

        // 押したとき
        start.setOnClickListener(buttonClick);
        stop.setOnClickListener(buttonClick);
        back.setOnClickListener(buttonClick);
    }

    private View.OnClickListener buttonClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_start:
                    // タイマーがすでに動いてるときは先に殺す
                    if (count != 0) {
                        handler.removeCallbacks(runnable);
                        timerText.setText(dataFormat.format(0));
                        count = 0;
                    }
                    handler.post(runnable);
                    break;

                case R.id.button_stop:
                    handler.removeCallbacks(runnable);
                    timerText.setText(dataFormat.format(0));
                    count = 0;
                    break;

                case R.id.button_back :
                    finish();
                    break;
            }
        }
    };
}
