package com.example.gymapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ViewActivity extends AppCompatActivity {

    private RunningOpenHelper helper;
    private SQLiteDatabase db;
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // レイアウトファイル
        setContentView(R.layout.activity_view);

        // ボタン
        Button back = findViewById(R.id.button_back);
        back.setOnClickListener(buttonClick);

        // textview
        textView = findViewById(R.id.text_view);

        readData();
    }

    private View.OnClickListener buttonClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_back :
                    finish();
                    break;
            }
        }
    };

    private void readData() {
        if(helper == null){
            helper = new RunningOpenHelper(getApplicationContext());
        }

        if(db == null){
            db = helper.getReadableDatabase();
        }

        Cursor cursor = db.query(
                "runningdb",
                new String[] { "speed", "time"},
                null,
                null,
                null,
                null,
                null
        );

        cursor.moveToFirst();

        StringBuilder sbuilder = new StringBuilder();

        for (int i=0; i<cursor.getCount(); i++){
            sbuilder.append(cursor.getFloat(0));
            sbuilder.append(" : ");
            sbuilder.append(cursor.getInt(1));
            sbuilder.append("\n");
            cursor.moveToNext();
        }

        cursor.close();

        textView.setText(sbuilder.toString());
    }
}
