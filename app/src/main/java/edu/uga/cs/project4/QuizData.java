package edu.uga.cs.project4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class QuizData {
    private SQLiteDatabase db;
    private SQLiteOpenHelper quizDbHelper;

    public QuizData( Context context ) {
        this.quizDbHelper = QuizDBHelper.getInstance( context );
    }

    public void open() {
        db = quizDbHelper.getWritableDatabase();
    }

    public void close() {
        if( quizDbHelper != null ) {
            quizDbHelper.close();
        }
    }

}
