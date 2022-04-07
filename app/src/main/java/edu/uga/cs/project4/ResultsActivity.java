package edu.uga.cs.project4;

import android.content.res.Resources;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity {
    private String quizDate;
    private String quizResult;

    private ArrayList<Quiz> quizList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        /*Resources res = getResources();

        recyclerView = findViewById(R.id.recyclerView);
        quizList = new ArrayList<>();
        setQuizInfo();
        setAdapter();

        setQuizInfo();


        //make this a recycler or normal list??
       // TextView txt = findViewById(R.id.textView3);

        SQLiteDatabase db = openDatabase();
        // long count = DatabaseUtils.queryNumEntries(db, QuizDBHelper.TABLE_QUIZZES);
        *//*Cursor cursor = db.rawQuery("SELECT * FROM " + QuizDBHelper.TABLE_QUIZZES, null);*//*

         *//*if (cursor.moveToFirst()){
            do {
                String column1 = cursor.getString(0); //id
                // String column2 = cursor.getString(0); //date
                String column3 = cursor.getString(0); //score
                createQuizList( column1, column3 );
            } while(cursor.moveToNext());
        }*//*
         *//*for( int i = 0; i < count; i++ ) {


            createQuizList(); //pass recycler
        }*//*
    }

    private void setQuizInfo() {
        quizList.add(new Quiz( 2 ));
        quizList.add(new Quiz( 4 ));
    }

    private void setAdapter() {
        QuizRecAdapter ad = new QuizRecAdapter(quizList);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(lm);
        recyclerView.setItemAnimator( new DefaultItemAnimator() );
        recyclerView.setAdapter(ad);
    }

    private void retrieveResults() {
        //Quiz q = new Quiz();

        //return q;
    }

    private SQLiteDatabase openDatabase() {
        QuizDBHelper db = QuizDBHelper.getInstance(this);
        SQLiteDatabase writeDB = db.getReadableDatabase();
        return writeDB;
    }

    private void createQuizList( String score ) {

    }

    private String resultString( String qd, String re ) {
        String str = "Quiz Date: " + qd + "   Grade: " + re + "/6";
        return str;
    }*/
    }
}



//cv.put(QuizDBHelper.QUIZ_SCORE, 2);
//cv.put(QuizDBHelper.QUIZ_SCORE, 6);

        /*Cursor cursor = writeDB.rawQuery( "SELECT country_name FROM COUNTRIES", null);
        if(cursor.moveToFirst()) {
            do {
                String column1 = cursor.getString(0);
                txt.append(column1);
            } while( cursor.moveToNext() );
        }*/
// txt.setText( yeet ) ;