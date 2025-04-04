package uk.edu.le.co2124assignment.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import uk.edu.le.co2124assignment.R;
import uk.edu.le.co2124assignment.db.Course;

public class CourseListAdapter extends ListAdapter<Course, CourseListAdapter.CourseViewHolder> {

    public interface OnCourseClickListener {
        void onCourseClick(Course course);
    }

    public interface OnCourseLongClickListener {
        boolean onCourseLongClick(Course course);
    }

    private OnCourseClickListener clickListener;
    private OnCourseLongClickListener longClickListener;

    public CourseListAdapter(@NonNull DiffUtil.ItemCallback<Course> diffCallback,
                             OnCourseClickListener clickListener,
                             OnCourseLongClickListener longClickListener) {
        super(diffCallback);
        this.clickListener = clickListener;
        this.longClickListener = longClickListener;
    }

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CourseViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position) {
        Course current = getItem(position);
        holder.bind(current, clickListener, longClickListener);
    }

    public static class CourseDiff extends DiffUtil.ItemCallback<Course> {
        @Override
        public boolean areItemsTheSame(@NonNull Course oldItem, @NonNull Course newItem) {
            return oldItem.courseId == newItem.courseId;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Course oldItem, @NonNull Course newItem) {
            return oldItem.courseCode.equals(newItem.courseCode)
                    && oldItem.courseName.equals(newItem.courseName)
                    && oldItem.lecturerName.equals(newItem.lecturerName);
        }
    }

    static class CourseViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        private final TextView tvCode;
        private final TextView tvName;
        private final TextView tvLecturer;

        private CourseViewHolder(View itemView) {
            super(itemView);
            tvCode = itemView.findViewById(R.id.tvCourseCode);
            tvName = itemView.findViewById(R.id.tvCourseName);
            tvLecturer = itemView.findViewById(R.id.tvLecturerName);
        }

        public void bind(Course course,
                         OnCourseClickListener clickListener,
                         OnCourseLongClickListener longClickListener) {
            tvCode.setText(course.courseCode);
            tvName.setText(course.courseName);
            tvLecturer.setText(course.lecturerName);

            itemView.setOnClickListener(v -> {
                if (clickListener != null) {
                    clickListener.onCourseClick(course);
                }
            });
            itemView.setOnLongClickListener(v -> {
                if (longClickListener != null) {
                    return longClickListener.onCourseLongClick(course);
                }
                return false;
            });
        }

        static CourseViewHolder create(ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_course, parent, false);
            return new CourseViewHolder(view);
        }
    }
}
