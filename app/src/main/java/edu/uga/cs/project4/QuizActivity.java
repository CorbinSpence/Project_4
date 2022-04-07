package edu.uga.cs.project4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import java.util.Calendar;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {
    Context context;
    Quiz quiz;
    int[] randomIndex;
    Question[] temp;
    TextView txt;
    ViewPager2 v2;
    QuizSwipeAdapter qa;
    TabLayout tl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        v2 = findViewById(R.id.v_pager);
        tl = findViewById(R.id.tabLayout);




//        txt = findViewById(R.id.textView2);
//        txt.setText("");
//
//        //connects answer buttons
//        RadioButton[] radioButtons = new RadioButton[] {
//                findViewById(R.id.radioButton),
//                findViewById(R.id.radioButton2),
//                findViewById(R.id.radioButton3)
//        };
//
        //creates array of non-duplicate id numbers
        Random rand = new Random();
        randomIndex = new int[6];
        int count = 0;
        while( count < randomIndex.length ) {
            int temp = rand.nextInt(194) + 1;
            if( isDuplicate(temp, randomIndex) == false ) {
                randomIndex[count] = temp;
                count++;
            }
        }

        temp = new Question[6];

        quiz = generateQuiz( randomIndex, temp );
        long id = addCompletedQuiz(quiz);

        qa = new QuizSwipeAdapter(this);
        qa = qa.getInstance(this, quiz, id);
        v2.setAdapter(qa);
//
//        //sets displayed question (Unfinished)
//        txt.append("" + quiz.quiz[0].getCountry());
//
//        //sets choices to radio buttons from generated quiz
//        for(int i = 0; i < radioButtons.length; i++) {
//            radioButtons[i].setText(quiz.quiz[0].getChoices()[i]);
//        }
//
//        txt.append(quiz.quiz[0].getAnswer());

        // get the async to work
        // add save functionality
        // set radio button correct option
        // swipe functionality, viewpager
        // results

    }

    //generates a random quiz
    private Quiz generateQuiz(int[] t, Question[] q ) {
        QuizDBHelper db = QuizDBHelper.getInstance(this);
        SQLiteDatabase writeDB = db.getWritableDatabase();
        String col1, col2;

        for( int i = 0; i < t.length; i++ ) {
            Cursor cursor = writeDB.rawQuery( "SELECT country_name, _id, country_continent FROM COUNTRIES " +
                    "WHERE _id='" + t[i] + "'", null);
            if(cursor.moveToFirst()) {
                do {
                    q[i] = new Question( cursor.getString(0), cursor.getString(2) );
                } while( cursor.moveToNext() );
            }
        }

        return new Quiz( q );
    }

    //checks if an array has int x already
    private boolean isDuplicate( int x, int[] xArray) {
        for( int i = 0; i < xArray.length; i++ ) {
            if( x == xArray[i] ) {
                return true;
            }
        }
        return false;

    }

    //saves completed quiz to sql
    private long addCompletedQuiz( Quiz q ) {
        QuizDBHelper db = QuizDBHelper.getInstance(this);
        SQLiteDatabase writeDB = db.getWritableDatabase();

        String date = Calendar.getInstance().getTime().toString();
        q.setDate( date );

        ContentValues cv = new ContentValues();
        cv.put(QuizDBHelper.QUIZ_SCORE, q.getScore());
        cv.put(QuizDBHelper.QUIZ_DATE, q.getDate());

        long id = writeDB.insert(QuizDBHelper.TABLE_QUIZZES, null, cv);
        return id;
    }

    //used to query data asynchronously
    private class MyAsyncTask extends AsyncTask<Void, Void, Void> {
        Context context;
        Quiz quiz;
        String option;

        public MyAsyncTask(Context context, String opt) {
            this.context = context;
            option = opt;
        }

        public MyAsyncTask(Context context, Quiz q, String opt) {
            this.context = context;
            quiz = q;
            option = opt;
        }

        //@Override
        protected Void doInBackground(Void... args) {

            if( option.equals("1") ) {
                // option 1: add completed quiz to database
                addCompletedQuiz( quiz );

            } else if ( option.equals("2") ) {
                // option 2: read countries from database
                QuizDBHelper db = QuizDBHelper.getInstance(context);
                SQLiteDatabase writeDB = db.getWritableDatabase();
                String col1, col2;

                for( int i = 0; i < randomIndex.length; i++ ) {
                    Cursor cursor = writeDB.rawQuery( "SELECT country_name, _id, country_continent FROM COUNTRIES " +
                            "WHERE _id='" + randomIndex[i] + "'", null);
                    if(cursor.moveToFirst()) {
                        do {
                            temp[i] = new Question( cursor.getString(0), cursor.getString(2) );
                        } while( cursor.moveToNext() );
                    }
                }
            }

            return null;
        }

        //@Override
        protected void onPostExecute(Void... args) {
            txt.append("" + temp[0].getAnswer() );
        }

    }

}



