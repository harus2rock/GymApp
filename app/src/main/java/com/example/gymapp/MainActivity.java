package com.example.gymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.gymapp.R.id.button_gym;
import static com.example.gymapp.R.id.button_house;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ボタンを設定
        Button gym = findViewById(button_gym);
        Button house = findViewById(button_house);

        // textview
        textView = findViewById(R.id.text_top);

        gym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag) {
                    textView.setText("in Gym");
                    flag = false;
                }

                else {
                    textView.setText("Hello!");
                    flag = true;
                }
            }
        });

        house.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag) {
                    textView.setText("in House");
                    flag = false;
                }

                else {
                    textView.setText("Hello!");
                    flag = true;
                }
            }
        });
    }
}
