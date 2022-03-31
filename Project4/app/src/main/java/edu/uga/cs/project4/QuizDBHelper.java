package edu.uga.cs.project4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class QuizDBHelper extends SQLiteOpenHelper {

    // db information
    private static final String DB_NAME = "countries_quiz.db";
    private static final int DB_VERSION = 1;
    private static QuizDBHelper myInstance;

    // db columns
    private static final String TABLE_COUNTRIES = "COUNTRIES";
    private static final String COUNTRY_ID ="_id";
    private static final String COUNTRY_NAME = "";
    private static final String COUNTRY_CONTINENT = "";

    public static final String CREATE_COUNTRIES =
            "create table " + TABLE_COUNTRIES + " ("
            + COUNTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COUNTRY_NAME + " TEXT, "
            + COUNTRY_CONTINENT + " TEXT, "
            + ")";

    private QuizDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static synchronized QuizDBHelper getInstance( Context context ) {
        if( myInstance == null ) {
            myInstance = new QuizDBHelper( context.getApplicationContext() );
        }
        return myInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( CREATE_COUNTRIES );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists " + TABLE_COUNTRIES);
        onCreate( db );
    }
}
