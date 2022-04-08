package edu.uga.cs.project4;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SubmitFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SubmitFragment extends Fragment {

    TextView submissionInfo;
    Button submit;
    String id;
    /**
     * Basic constructor
     */
    public SubmitFragment() {
        // Required empty public constructor
    }

    /**
     * Instance method to get {@link SubmitFragment}
     * @return  new instance
     */
    public static SubmitFragment newInstance() {
        SubmitFragment fragment = new SubmitFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    /**
     * sets onCreate
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }
    /**
     * sets onCreateView
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return  The inflated view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_submit, container, false);
        id = getArguments().getString("id");
        submissionInfo = v.findViewById(R.id.sub_info);
        showScore(id);
        //new AsyncSetScore(getContext()).execute();
        submit = v.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalizeQuiz(id);
                Intent intent = new Intent( getContext(), ResultsActivity.class );
                startActivity(intent);
                getActivity().finish();
            }
        });
        submit.setText("Submit");

        return v;
        
    }
    /**
     * Sets final values of current quiz and exits from the quiz to the results
     * @param id current quiz id
     */
    private void finalizeQuiz(String id){
        QuizDBHelper db = QuizDBHelper.getInstance(getContext());
        SQLiteDatabase writeDB = db.getWritableDatabase();
        String date = Calendar.getInstance().getTime().toString();

        writeDB.execSQL("UPDATE "+QuizDBHelper.TABLE_QUIZZES+" SET "+QuizDBHelper.QUIZ_DATE+"='"+date+"' WHERE "+QuizDBHelper.QUIZ_ID+"="+id);
    }
    /**
     * sets textview value so that it shows the quiz result
     * @param id current quiz id
     */
    private void showScore(String id){
        QuizDBHelper db = QuizDBHelper.getInstance(getContext());
        SQLiteDatabase writeDB = db.getWritableDatabase();
        String val = "";

        Cursor cursor = writeDB.rawQuery("SELECT "+QuizDBHelper.QUIZ_SCORE+" FROM "+QuizDBHelper.TABLE_QUIZZES+" WHERE "+QuizDBHelper.QUIZ_ID+"="+id, null);
        if(cursor.moveToFirst()){
            val = cursor.getString(0);
        }
        submissionInfo.setText("You got "+val+" out of 6");
    }
    /**
     * Used for Asynchronously setting the quiz score to the textview
     */
    private class AsyncSetScore extends AsyncTask<String, String, String> {
        Context context;
        String result;

        public AsyncSetScore (Context context) {
            this.context = context;
        }

        //@Override
        protected String doInBackground(String... args) {
            showScore(id);
            return "1";
        }

        protected void onPostExecute(String result) {
            // implement
        }


    }
}