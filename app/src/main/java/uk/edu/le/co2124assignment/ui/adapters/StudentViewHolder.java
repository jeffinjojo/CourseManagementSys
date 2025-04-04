package uk.edu.le.co2124assignment.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import uk.edu.le.co2124assignment.R;
import uk.edu.le.co2124assignment.db.Student;

/**
 * A standalone ViewHolder for a single Student item, inflating item_student.xml
 */
public class StudentViewHolder extends RecyclerView.ViewHolder {

    private final TextView tvStudentName;
    private final TextView tvStudentEmail;

    public StudentViewHolder(@NonNull View itemView) {
        super(itemView);
        tvStudentName = itemView.findViewById(R.id.tvStudentName);
        tvStudentEmail = itemView.findViewById(R.id.tvStudentEmail);
    }

    public void bind(Student student) {
        tvStudentName.setText(student.name);
        tvStudentEmail.setText(student.email);
    }

    /**
     * Inflate the layout and create a new holder instance.
     * Typically called in onCreateViewHolder of the adapter.
     */
    @NonNull
    public static StudentViewHolder create(@NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(view);
    }
}
