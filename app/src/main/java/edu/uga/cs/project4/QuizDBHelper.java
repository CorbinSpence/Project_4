package edu.uga.cs.project4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import androidx.viewpager2.adapter.FragmentStateAdapter;

/**
 * A simple {@link SQLiteOpenHelper} subclass.
 * Allows the program to efficiently make references to the quiz and country tables.
 */
public class QuizDBHelper extends SQLiteOpenHelper {

    // db information
    private static final String DB_NAME = "countries_quiz.db";
    private static final int DB_VERSION = 1;
    private static QuizDBHelper myInstance;

    // db: countries tables and columns
    public static final String TABLE_COUNTRIES = "COUNTRIES";
    public static final String COUNTRY_ID ="_id";
    public static final String COUNTRY_NAME = "country_name";
    public static final String COUNTRY_CONTINENT = "country_continent";

    public static final String CREATE_COUNTRIES =
            "create table " + TABLE_COUNTRIES + " ("
            + COUNTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COUNTRY_NAME + " TEXT, "
            + COUNTRY_CONTINENT + " TEXT "
            + ")";

    // db: quizzes tables and columns
    public static final String TABLE_QUIZZES = "QUIZZES";
    public static final String QUIZ_ID ="quiz_id";
    public static final String QUIZ_DATE = "quiz_date";
    public static final String QUIZ_SCORE = "quiz_score";

    public static final String CREATE_QUIZZES =
            "create table " + TABLE_QUIZZES + " ("
                    + QUIZ_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + QUIZ_DATE + " TEXT, "
                    + QUIZ_SCORE + " TEXT "
                    + ")";

    private QuizDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    /**
     * Instance method to get {@link QuizSwipeAdapter}
     * @param context
     * @return  new instance
     */
    public static synchronized QuizDBHelper getInstance( Context context ) {
        if( myInstance == null ) {
            myInstance = new QuizDBHelper( context.getApplicationContext() );
        }
        return myInstance;
    }
    /**
     * sets onCreate
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( CREATE_COUNTRIES );
        db.execSQL( CREATE_QUIZZES );
    }
    /**
     * sets onUpgrade
     * @param db
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists " + TABLE_COUNTRIES);
        db.execSQL("drop table if exists " + TABLE_QUIZZES);
        onCreate( db );
    }
}
