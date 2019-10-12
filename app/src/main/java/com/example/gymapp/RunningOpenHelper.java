package com.example.gymapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class RunningOpenHelper extends SQLiteOpenHelper {

    // データベースのバージョン
    private static final int DATABASE_VERSION = 1;

    // データベース名
    private static final String DATABASE_NAME = "RunningDB.db";
    private static final String TABLE_NAME = "runningdb";
    private static final String _ID = "_id";
    private static final String COLUMN_NAME_YEAR = "year";
    private static final String COLUMN_NAME_MONTH = "month";
    private static final String COLUMN_NAME_DAY = "day";
    private static final String COLUMN_NAME_SPEED = "speed";
    private static final String COLUMN_NAME_START = "start";
    private static final String COLUMN_NAME_TIME = "time";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_NAME_YEAR + " INTEGER, " +
                    COLUMN_NAME_MONTH + " INTEGER, " +
                    COLUMN_NAME_DAY + " INTEGER, " +
                    COLUMN_NAME_SPEED + " REAL, " +
                    COLUMN_NAME_START + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                    COLUMN_NAME_TIME + " INTEGER)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    RunningOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // テーブル作成
        db.execSQL(
                SQL_CREATE_ENTRIES
        );

        Log.d("debug", "onCreate(SQLiteDatabase db)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // アップデートの判別、古いバージョンは削除
        db.execSQL(
                SQL_DELETE_ENTRIES
        );
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
