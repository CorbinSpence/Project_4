/*package edu.uga.cs.project4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QuizRecAdapter extends RecyclerView.Adapter<QuizRecAdapter.ViewHolder> {
    private String[] localDataSet;
    private ArrayList<Quiz> quizList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv;

        public ViewHolder(View view) {
            super(view);

            tv = (TextView) view.findViewById(R.id.textViewItem);
        }

        public TextView getTextView() {
            return tv;
        }
    }

    public QuizRecAdapter(ArrayList<Quiz> quizList) {
        this.quizList = quizList;
        //localDataSet = dataSet;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        int score = quizList.get(position).getScore();
        viewHolder.getTextView().setText("Score: " + score);
    }

    @Override
    public int getItemCount() {
        return quizList.size();
    }
}*/
