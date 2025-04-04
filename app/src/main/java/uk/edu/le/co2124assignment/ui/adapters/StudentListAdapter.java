package uk.edu.le.co2124assignment.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import uk.edu.le.co2124assignment.R;
import uk.edu.le.co2124assignment.db.Student;

public class StudentListAdapter extends ListAdapter<Student, StudentListAdapter.StudentViewHolder> {

    public interface OnStudentClickListener {
        void onStudentClick(Student student);
    }

    private OnStudentClickListener listener;

    public StudentListAdapter(@NonNull DiffUtil.ItemCallback<Student> diffCallback,
                              OnStudentClickListener listener) {
        super(diffCallback);
        this.listener = listener;
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return StudentViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position) {
        Student current = getItem(position);
        holder.bind(current, listener);
    }

    public static class StudentDiff extends DiffUtil.ItemCallback<Student> {
        @Override
        public boolean areItemsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
            return oldItem.studentId == newItem.studentId;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
            return oldItem.name.equals(newItem.name) &&
                    oldItem.email.equals(newItem.email) &&
                    oldItem.userName.equals(newItem.userName);
        }
    }

    static class StudentViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        private final TextView tvName;
        private final TextView tvEmail;

        private StudentViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvStudentName);
            tvEmail = itemView.findViewById(R.id.tvStudentEmail);
        }

        public void bind(Student student, OnStudentClickListener listener) {
            tvName.setText(student.name);
            tvEmail.setText(student.email);

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onStudentClick(student);
                }
            });
        }

        static StudentViewHolder create(ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_student, parent, false);
            return new StudentViewHolder(view);
        }
    }
}
