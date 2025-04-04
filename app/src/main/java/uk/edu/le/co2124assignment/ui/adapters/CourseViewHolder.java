package uk.edu.le.co2124assignment.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import uk.edu.le.co2124assignment.R;
import uk.edu.le.co2124assignment.db.Course;

/**
 * A standalone ViewHolder for a single Course item, inflating item_course.xml
 */
public class CourseViewHolder extends RecyclerView.ViewHolder {

    private final TextView tvCourseCode;
    private final TextView tvCourseName;
    private final TextView tvLecturerName;

    public CourseViewHolder(@NonNull View itemView) {
        super(itemView);
        tvCourseCode = itemView.findViewById(R.id.tvCourseCode);
        tvCourseName = itemView.findViewById(R.id.tvCourseName);
        tvLecturerName = itemView.findViewById(R.id.tvLecturerName);
    }

    public void bind(Course course) {
        tvCourseCode.setText(course.courseCode);
        tvCourseName.setText(course.courseName);
        tvLecturerName.setText(course.lecturerName);
    }

    /**
     * Inflate the layout and create a new holder instance.
     * Typically called in onCreateViewHolder of the adapter.
     */
    @NonNull
    public static CourseViewHolder create(@NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course, parent, false);
        return new CourseViewHolder(view);
    }
}
