package edu.uga.cs.project4;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class QuizSwipeAdapter extends FragmentStateAdapter {

    public Quiz quiz;
    public long id;
    public List<Fragment> list;

    public QuizSwipeAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public QuizSwipeAdapter getInstance(@NonNull FragmentActivity fragmentActivity, Quiz quiz, long id) {
        QuizSwipeAdapter temp = new QuizSwipeAdapter(fragmentActivity);
        temp.quiz = quiz;
        temp.id = id;

        temp.list = new ArrayList<Fragment>();
        for (int i = 0; i<6; i++){
            QuizFragment qf = QuizFragment.newInstance();
            Bundle b = new Bundle();
            String country = quiz.quiz[i].getCountry();
            String answer = quiz.quiz[i].getAnswer();
            String[] choices = quiz.quiz[i].getChoices();
            b.putString("id", ""+id);
            b.putString("country", country);
            b.putString("answer", answer);
            b.putStringArray("choices", choices);

            qf.setArguments(b);

            temp.list.add(qf);
        }
        SubmitFragment sf = new SubmitFragment();
        Bundle sfBundle = new Bundle();
        sfBundle.putString("id", ""+id);
        sf.setArguments(sfBundle);
        temp.list.add(sf);
        return temp;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
//        QuizFragment frag = new QuizFragment();
//        Bundle b = new Bundle();
//        String country = "";
//        String answer = "";
//        String[] choices = new String[quiz.quiz[1].getChoices().length];
//        b.putString("pos", ""+position);
//        b.putString("id", ""+id);
//        switch(position){
//            case 0:
//                country = quiz.quiz[0].getCountry();
//                answer = quiz.quiz[0].getAnswer();
//                choices = quiz.quiz[0].getChoices();
//                break;
//            case 1:
//                country = quiz.quiz[1].getCountry();
//                answer = quiz.quiz[1].getAnswer();
//                choices = quiz.quiz[1].getChoices();
//                break;
//            case 2:
//                country = quiz.quiz[2].getCountry();
//                answer = quiz.quiz[2].getAnswer();
//                choices = quiz.quiz[2].getChoices();
//                break;
//            case 3:
//                country = quiz.quiz[3].getCountry();
//                answer = quiz.quiz[3].getAnswer();
//                choices = quiz.quiz[3].getChoices();
//                break;
//            case 4:
//                country = quiz.quiz[4].getCountry();
//                answer = quiz.quiz[4].getAnswer();
//                choices = quiz.quiz[4].getChoices();
//                break;
//            case 5:
//                country = quiz.quiz[5].getCountry();
//                answer = quiz.quiz[5].getAnswer();
//                choices = quiz.quiz[5].getChoices();
//                break;
//            case 6:
//                SubmitFragment temp = new SubmitFragment();
//                temp.setArguments(b);
//                return temp;
//        }
//        b.putString("country", country);
//        b.putString("answer", answer);
//        b.putStringArray("choices", choices);
//
//        frag.setArguments(b);

        return list.get(position);
    }

    @Override
    public int getItemCount() {
        return 7;
    }
}
