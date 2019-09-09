package com.example.gymapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
        // レイアウトファイル
        setContentView(R.layout.activity_main);

//        TODO delete commentout
//        if(savedInstanceState == null) {
//            // FragmentManagerのインスタンス生成
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            // FragmentTransactionのインスタンスを取得
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            // インスタンスに対して張り付け方を指定する
//            fragmentTransaction.replace(R.id.layout_clock, new ClockFragment());
//            // 張り付けを実行
//            fragmentTransaction.commit();
//        }

        // ボタンidを設定
        Button gym = findViewById(button_gym);
        Button house = findViewById(button_house);

        // gymボタンをクリックしたとき
        gym.setOnClickListener(buttonClick);

        // houseボタンをクリックしたとき
        house.setOnClickListener(buttonClick);
//        setScreenMain();
    }

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

//    private void setScreenMain() {
//        // レイアウトファイル
//        setContentView(R.layout.activity_main);
//
//        // ボタンidを設定
//        Button gym = findViewById(button_gym);
//        Button house = findViewById(button_house);
//
//        // gymボタンをクリックしたとき
//        gym.setOnClickListener(buttonClick);
//
//        // houseボタンをクリックしたとき
//        house.setOnClickListener(buttonClick);
//    }

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

}
