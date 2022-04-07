package edu.uga.cs.project4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuizRecyclerAdapter extends RecyclerView.Adapter<QuizRecyclerAdapter.QuizViewHolder> {
    private Context context;
    private List<Quiz> quizList;

    public QuizRecyclerAdapter(Context con, List<Quiz> list) {
        context = con;
        quizList = list;
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.quiz_item, null);
        return new QuizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        Quiz quiz = quizList.get(position);

        holder.txt.setText("Date: " + quiz.getDate() + " | Score" + quiz.getScore());
    }

    @Override
    public int getItemCount() {
        return quizList.size();
    }

    public class QuizViewHolder extends RecyclerView.ViewHolder {
        TextView txt;

        public QuizViewHolder(@NonNull View itemView) {
            super(itemView);

            txt = itemView.findViewById(R.id.textView3);
        }
    }
}
