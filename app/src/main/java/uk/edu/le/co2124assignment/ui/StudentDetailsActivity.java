package uk.edu.le.co2124assignment.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import uk.edu.le.co2124assignment.R;
import uk.edu.le.co2124assignment.db.Course;
import uk.edu.le.co2124assignment.db.Student;
import uk.edu.le.co2124assignment.viewmodel.CourseViewModel;
import uk.edu.le.co2124assignment.viewmodel.EnrollmentViewModel;
import uk.edu.le.co2124assignment.viewmodel.StudentViewModel;

public class StudentDetailsActivity extends AppCompatActivity {

    private TextView tvStudentName, tvStudentEmail, tvStudentUserName, tvEnrolledCourses;
    private StudentViewModel studentViewModel;
    private EnrollmentViewModel enrollmentViewModel;
    private CourseViewModel courseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        tvStudentName = findViewById(R.id.tvStudentNameDetails);
        tvStudentEmail = findViewById(R.id.tvStudentEmailDetails);
        tvStudentUserName = findViewById(R.id.tvStudentUserNameDetails);
        tvEnrolledCourses = findViewById(R.id.tvEnrolledCoursesList);

        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);
        enrollmentViewModel = new ViewModelProvider(this).get(EnrollmentViewModel.class);
        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

        int studentId = getIntent().getIntExtra("studentId", -1);
        if (studentId != -1) {
            Student st = studentViewModel.getStudentSync(studentId);
            if (st != null) {
                tvStudentName.setText(st.name);
                tvStudentEmail.setText(st.email);
                tvStudentUserName.setText(st.userName);

                // list courses
                List<Integer> courseIds = enrollmentViewModel.getCourseIdsForStudentSync(studentId);
                List<String> courseNames = new ArrayList<>();
                for (int cid : courseIds) {
                    Course c = courseViewModel.getCourseSync(cid);
                    if (c != null) {
                        courseNames.add(c.courseCode + " - " + c.courseName);
                    }
                }
                if (courseNames.isEmpty()) {
                    tvEnrolledCourses.setText("No courses enrolled");
                } else {
                    StringBuilder sb = new StringBuilder();
                    for (String line : courseNames) {
                        sb.append(line).append("\n");
                    }
                    tvEnrolledCourses.setText(sb.toString());
                }
            }
        }
    }
}
