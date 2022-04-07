package edu.uga.cs.project4;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
   
    public SubmitFragment() {
        // Required empty public constructor
    }

   
    public static SubmitFragment newInstance(String param1, String param2) {
        SubmitFragment fragment = new SubmitFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_submit, container, false);
        String id = getArguments().getString("id");
        submissionInfo = v.findViewById(R.id.sub_info);
        showScore(id);
        submit = v.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalizeQuiz(id);
            }
        });
        submit.setText("Submit");

        return v;
        
    }
    private void finalizeQuiz(String id){
        QuizDBHelper db = QuizDBHelper.getInstance(getContext());
        SQLiteDatabase writeDB = db.getWritableDatabase();
        String date = Calendar.getInstance().getTime().toString();

        writeDB.execSQL("UPDATE "+QuizDBHelper.TABLE_QUIZZES+" SET "+QuizDBHelper.QUIZ_DATE+"="+date+" WHERE "+QuizDBHelper.QUIZ_ID+"="+id);
    }
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
}