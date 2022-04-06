package edu.uga.cs.project4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuizFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizFragment extends Fragment {

    TextView quizQuestion;
    RadioButton[] choices;
    int[] selectedButtons = new int[6];

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
        String pos = getArguments().getString("pos");
        String country = getArguments().getString("country");
        String answer = getArguments().getString("answer");
        String[] choices = getArguments().getStringArray("choices");
        quizQuestion.setText("What continent is "+country+"located in?");
        this.choices = new RadioButton[3];
        this.choices[0] = v.findViewById(R.id.radioButton);
        this.choices[1] = v.findViewById(R.id.radioButton2);
        this.choices[2] = v.findViewById(R.id.radioButton3);
        for(int i=0; i<3; i++){
            this.choices[i].setText(choices[i]);
        }
        return v;
    }
}