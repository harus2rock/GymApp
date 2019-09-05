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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setScreenMain();
    }

    private void setScreenMain() {
        // レイアウトファイル
        setContentView(R.layout.activity_main);

        // ボタンidを設定
        Button gym = findViewById(button_gym);
        Button house = findViewById(button_house);

        // gymボタンをクリックしたとき
        gym.setOnClickListener(buttonClick);

        // houseボタンをクリックしたとき
        house.setOnClickListener(buttonClick);
    }

//    private void setScreenGym() {
//        // レイアウトファイル
//        setContentView(R.layout.activity_gym);
//
//        // ボタン
//        Button home = findViewById(R.id.button_home);
//        home.setOnClickListener(buttonClick);
//    }
//
//    private void setScreenHouse() {
//        // レイアウトファイル
//        setContentView(R.layout.activity_house);
//
//        // ボタン
//        Button home = findViewById(R.id.button_home);
//        home.setOnClickListener(buttonClick);
//    }

    private  View.OnClickListener buttonClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                // ジムボタンの時
                case button_gym :
//                    setScreenGym();
                    Intent intent_gym = new Intent(getApplication(), GymActivity.class);
                    startActivity(intent_gym);
                    break;

                // ハウスボタンの時
                case button_house :
//                    setScreenHouse();
                    Intent intent_house = new Intent(getApplication(), HouseActivity.class);
                    startActivity(intent_house);
                    break;

//                // ホームボタンの時
//                case R.id.button_home :
//                    setScreenMain();
//                    break;
            }
        }
    };
}
