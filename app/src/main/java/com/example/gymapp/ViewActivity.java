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
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class ViewActivity extends AppCompatActivity {

    private RunningOpenHelper helper;
    private SQLiteDatabase db;
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // layout file
        setContentView(R.layout.activity_view);

        // Button
        Button back = findViewById(R.id.button_back);
        Button delete = findViewById(R.id.button_delete);
        back.setOnClickListener(buttonClick);
        delete.setOnClickListener(buttonClick);

        // textView
//        textView = findViewById(R.id.text_view);

        Cursor cursor = readData();
        String result_text = viewTextData(cursor);
//        viewTextData(cursor);

        ViewFragmentPagerAdapter adapter = new ViewFragmentPagerAdapter(getSupportFragmentManager(), result_text);
        ViewPager viewPager = findViewById(R.id.layout_pager);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private View.OnClickListener buttonClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_back :
                    finish();
                    break;

                case R.id.button_delete :
                    deleteData();
                    break;
            }
        }
    };

    private Cursor readData() {
        // Check db
        if(helper == null){
            helper = new RunningOpenHelper(getApplicationContext());
        }
        if(db == null){
            db = helper.getReadableDatabase();
        }

        String sql = "select _id, start, speed, time from runningdb";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();

        return cursor;
    }

    private String viewTextData(Cursor cursor){
        StringBuilder sbuilder = new StringBuilder();
        SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss.S", Locale.US);

        // For calculating running time
        Resources res = getResources();
        int period = res.getInteger(R.integer.period);

        // header
        sbuilder.append("id : TimeStamp : Speed (km/h) : Time\n\n");

        for (int i=0; i<cursor.getCount(); i++){
            sbuilder.append(String.format(Locale.US, "%d : ", cursor.getInt(0)));
            if (cursor.getDouble(2) == 0){
                sbuilder.append("Stop.");
            } else {
                sbuilder.append(cursor.getString(1));
                sbuilder.append(String.format(Locale.US, "  %.1f (km/h) ", cursor.getDouble(2)));
                sbuilder.append(timeFormat.format(cursor.getInt(3) * period));
            }
            sbuilder.append("\n");
            cursor.moveToNext();
        }

        cursor.close();

        return sbuilder.toString();

//        textView.setText(sbuilder.toString());
    }

    private void deleteData() {
        if(helper == null){
            helper = new RunningOpenHelper(getApplicationContext());
        }
        if(db == null){
            db = helper.getReadableDatabase();
        }

        db.delete("runningdb", null, null);
        viewTextData(readData());
    }
}
