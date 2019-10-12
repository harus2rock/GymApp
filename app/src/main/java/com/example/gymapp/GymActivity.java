package com.example.gymapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GymActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // レイアウトファイル
        setContentView(R.layout.activity_gym);

        // ボタン
        Button running = findViewById(R.id.button_running);
        Button machine = findViewById(R.id.button_machine);
        Button view = findViewById(R.id.button_view);
        Button home = findViewById(R.id.button_home);
        running.setOnClickListener(buttonClick);
        machine.setOnClickListener(buttonClick);
        view.setOnClickListener(buttonClick);
        home.setOnClickListener(buttonClick);

    }

    private View.OnClickListener buttonClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_running :
                    Intent intent_running = new Intent(getApplication(), RunningActivity.class);
                    startActivity(intent_running);
                    break;

                case R.id.button_machine :
                    break;

                case R.id.button_view :
                    Intent intent_view = new Intent(getApplication(), ViewActivity.class);
                    startActivity(intent_view);
                    break;

                case R.id.button_home :
                    finish();
                    break;
            }
        }
    };
}
