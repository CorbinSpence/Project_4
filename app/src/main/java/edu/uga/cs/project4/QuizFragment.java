package edu.uga.cs.project4;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuizFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizFragment extends Fragment {

    TextView quizQuestion;
    RadioButton[] choices;
    RadioGroup rGroup;
    int[] selectedButtons = new int[6];
    boolean pointGiven = false;

    public QuizFragment() {
        // Required empty public constructor
    }

    public static QuizFragment newInstance() {
        QuizFragment fragment = new QuizFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_quiz, container, false);
        quizQuestion = v.findViewById(R.id.quiz_question);
        rGroup = v.findViewById(R.id.radio_group);
        String pos = getArguments().getString("pos");
        String country = getArguments().getString("country");
        String answer = getArguments().getString("answer");
        String[] choices = getArguments().getStringArray("choices");
        String id = getArguments().getString("id");
        quizQuestion.setText("What continent is "+country+"located in?");
        this.choices = new RadioButton[3];
        this.choices[0] = v.findViewById(R.id.radioButton);
        this.choices[1] = v.findViewById(R.id.radioButton2);
        this.choices[2] = v.findViewById(R.id.radioButton3);
        for(int i=0; i<3; i++){
            this.choices[i].setText(choices[i]);
        }
        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rb = v.findViewById(i);
                if(rb.getText().equals(answer)&&pointGiven==false){
                    addPoint(id);
                    pointGiven = true;
                }else if(!rb.getText().equals(answer)&&pointGiven==true){
                    subtractPoint(id);
                    pointGiven = false;
                }
            }
        });
        return v;
    }

    private void addPoint(String id){
        QuizDBHelper db = QuizDBHelper.getInstance(getContext());
        SQLiteDatabase writeDB = db.getWritableDatabase();
        String initalVal = "";

        Cursor cursor = writeDB.rawQuery("SELECT "+QuizDBHelper.QUIZ_SCORE+" FROM "+QuizDBHelper.TABLE_QUIZZES+" WHERE "+QuizDBHelper.QUIZ_ID+"="+id, null);
        if(cursor.moveToFirst()){
            initalVal = cursor.getString(0);
        }
        int val = Integer.parseInt(initalVal);
        val+=1;
        writeDB.execSQL("UPDATE "+QuizDBHelper.TABLE_QUIZZES+" SET "+QuizDBHelper.QUIZ_SCORE+"="+val+" WHERE "+QuizDBHelper.QUIZ_ID+"="+id);
    }
    private void subtractPoint(String id){
        QuizDBHelper db = QuizDBHelper.getInstance(getContext());
        SQLiteDatabase writeDB = db.getWritableDatabase();
        String initalVal = "";

        Cursor cursor = writeDB.rawQuery("SELECT "+QuizDBHelper.QUIZ_SCORE+" FROM "+QuizDBHelper.TABLE_QUIZZES+" WHERE "+QuizDBHelper.QUIZ_ID+"="+id, null);
        if(cursor.moveToFirst()){
            initalVal = cursor.getString(0);
        }
        int val = Integer.parseInt(initalVal);
        val-=1;
        writeDB.execSQL("UPDATE "+QuizDBHelper.TABLE_QUIZZES+" SET "+QuizDBHelper.QUIZ_SCORE+"="+val+" WHERE "+QuizDBHelper.QUIZ_ID+"="+id);
    }

}