package com.example.gymapp;

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
        Button home = findViewById(R.id.button_home);
        home.setOnClickListener(buttonClick);

    }

    private View.OnClickListener buttonClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_home :
                    finish();
                    break;
            }
        }
    };
}
