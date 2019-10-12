package com.example.gymapp;

import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Locale;

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

        SimpleDateFormat dataFormat = new SimpleDateFormat("mm:ss.S", Locale.US);
        Resources res = getResources();
        int period = res.getInteger(R.integer.period);

        for (int i=0; i<cursor.getCount(); i++){
            sbuilder.append(String.format(Locale.US,"%.1f",cursor.getDouble(0)));
            sbuilder.append(" : ");
            sbuilder.append(dataFormat.format(cursor.getInt(1) * period));
            sbuilder.append("\n");
            cursor.moveToNext();
        }

        cursor.close();

        textView.setText(sbuilder.toString());
    }
}
