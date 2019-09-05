package com.example.gymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
        // レイアウトファイル
        setContentView(R.layout.activity_main);

        // ボタンidを設定
        Button gym = findViewById(button_gym);
        Button house = findViewById(button_house);

        // TextViewを設定
        textView = findViewById(R.id.text_top);

        // gymボタンをクリックしたとき
        gym.setOnClickListener(buttonClick);

        // houseボタンをクリックしたとき
        house.setOnClickListener(buttonClick);
    }

    private  View.OnClickListener buttonClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                // ジムボタンの時
                case button_gym :
                    if (flag) {
                        textView.setText("in Gym");
                        flag = false;
                    } else {
                        textView.setText("Hello!");
                        flag = true;
                    }

                    Intent intent = new Intent(getApplication(), GymActivity.class);
                    startActivity(intent);
                    break;

                // ハウスボタンの時
                case button_house :
                    if (flag) {
                        textView.setText("in House");
                        flag = false;
                    } else {
                        textView.setText("Hello!");
                        flag = true;
                    }
                    break;
            }
        }
    };
}
