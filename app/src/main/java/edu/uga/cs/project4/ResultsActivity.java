package edu.uga.cs.project4;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
/**
 * A simple {@link AppCompatActivity} subclass.
 * Shows quiz results page.
 */
public class ResultsActivity extends AppCompatActivity {
    private List<Quiz> quizList;
    private RecyclerView recycler;
    private QuizRecyclerAdapter adapter;

    private Context context;
    private long count;
    /**
     * sets onCreate
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        quizList = new ArrayList<>();

        recycler = (RecyclerView) findViewById(R.id.recyclerView);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        new MyAsyncTask(context).execute();

        adapter = new QuizRecyclerAdapter(this, quizList);
        recycler.setAdapter(adapter);

    }
    /**
     * This queries the id, date, and score columns from the database. It adds to a quiz array list which
     * the recycler adapter takes and displays the list.
     */
    private void accessDB() {
        QuizDBHelper db = QuizDBHelper.getInstance(this);
        SQLiteDatabase writeDB = db.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries( writeDB, QuizDBHelper.TABLE_QUIZZES );


        Cursor cursor = writeDB.rawQuery( "SELECT quiz_id, quiz_date, quiz_score FROM QUIZZES", null);
        if(cursor.moveToFirst()) {
            do {
                String column1 = cursor.getString(0);
                String column2 = cursor.getString(1);
                String column3 = cursor.getString(2);
                quizList.add(
                        new Quiz(Integer.parseInt(column3), column2)
                );
            } while( cursor.moveToNext() );
        }

    }
    /**
     * Used for Asynchronously setting up the database to be presented to the recycler view.
     */
    private class MyAsyncTask extends AsyncTask<String, String, String> {
        Context context;
        // String result;

        public MyAsyncTask (Context context) {
            this.context = context;
        }

        //@Override
        protected String doInBackground(String... args) {
            accessDB();
            return "1";
        }

        protected void onPostExecute(String result) {
            // implement
        }
    }
}