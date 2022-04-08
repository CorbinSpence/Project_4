package edu.uga.cs.project4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * QuizRecyclerAdapter class, creates a recycler view adapter for quiz results
 */
public class QuizRecyclerAdapter extends RecyclerView.Adapter<QuizRecyclerAdapter.QuizViewHolder> {
    private Context context;
    private List<Quiz> quizList;

    /**
     * QuizRecyclerAdapter constructor, initializes the context and quizList
     * @param con
     * @param list
     */
    public QuizRecyclerAdapter(Context con, List<Quiz> list) {
        context = con;
        quizList = list;
    }

    /**
     * onCreateViewHolder method
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.quiz_item, null);
        return new QuizViewHolder(view);
    }

    /**
     * onBindViewHolder method, creates a quiz object from an index position in the quizList array
     * from the parameter and sets the text of the textView as below
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        Quiz quiz = quizList.get(position);

        holder.txt.setText("Date: " + quiz.getDate() + " | Score" + quiz.getScore());
    }

    /**
     * returns the size of the quizList array
     * @return quizList length
     */
    @Override
    public int getItemCount() {
        return quizList.size();
    }

    /**
     * sets a text view for a QuizViewHolder object which is used to put the results
     * text in.
     */
    public class QuizViewHolder extends RecyclerView.ViewHolder {
        TextView txt;

        public QuizViewHolder(@NonNull View itemView) {
            super(itemView);

            txt = itemView.findViewById(R.id.textView3);
        }
    }
}
