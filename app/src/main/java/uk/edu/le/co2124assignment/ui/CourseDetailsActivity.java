package uk.edu.le.co2124assignment.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import uk.edu.le.co2124assignment.R;
import uk.edu.le.co2124assignment.db.Course;
import uk.edu.le.co2124assignment.db.Student;
import uk.edu.le.co2124assignment.ui.adapters.StudentListAdapter;
import uk.edu.le.co2124assignment.viewmodel.CourseViewModel;
import uk.edu.le.co2124assignment.viewmodel.EnrollmentViewModel;
import uk.edu.le.co2124assignment.viewmodel.StudentViewModel;

public class CourseDetailsActivity extends AppCompatActivity {

    private static final String TAG = "CourseDetailsActivity";

    private TextView tvCourseCode, tvCourseName, tvLecturerName;
    private RecyclerView recyclerView;
    private StudentListAdapter studentAdapter;

    private CourseViewModel courseViewModel;
    private StudentViewModel studentViewModel;
    private EnrollmentViewModel enrollmentViewModel;

    private int courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        tvCourseCode = findViewById(R.id.tvCourseCodeDetails);
        tvCourseName = findViewById(R.id.tvCourseNameDetails);
        tvLecturerName = findViewById(R.id.tvLecturerDetails);

        recyclerView = findViewById(R.id.recyclerStudentsInCourse);
        studentAdapter = new StudentListAdapter(new StudentListAdapter.StudentDiff(), student -> {
            // On item click => Show a simple AlertDialog with "Edit" or "Remove"
            showStudentOptionsDialog(student);
        });
        recyclerView.setAdapter(studentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);
        enrollmentViewModel = new ViewModelProvider(this).get(EnrollmentViewModel.class);

        courseId = getIntent().getIntExtra("courseId", -1);
        if (courseId != -1) {
            // fetch the course details
            loadCourseDetails(courseId);
            // load the enrolled students
            loadStudentsForCourse(courseId);
        }

        FloatingActionButton fab = findViewById(R.id.fabAddStudentToCourse);
        fab.setOnClickListener(view -> {
            // Navigate to AddStudentActivity (where we can add new or existing students)
            Intent intent = new Intent(CourseDetailsActivity.this, AddStudentActivity.class);
            intent.putExtra("courseId", courseId);
            startActivity(intent);
        });
    }

    private void loadCourseDetails(int cId) {
        // This is sync, be careful in a real project. 
        // In a beginner style, we just do it. 
        Course course = courseViewModel.getCourseSync(cId);
        if (course != null) {
            tvCourseCode.setText(course.courseCode);
            tvCourseName.setText(course.courseName);
            tvLecturerName.setText(course.lecturerName);
        }
    }

    private void loadStudentsForCourse(int cId) {
        // We'll get the studentIds for this course from the enrollment VM, then fetch each Student synchronously.
        List<Integer> enrolledIds = enrollmentViewModel.getStudentIdsForCourseSync(cId);
        List<Student> enrolledStudents = new ArrayList<>();
        for (int sid : enrolledIds) {
            Student st = studentViewModel.getStudentSync(sid);
            if (st != null) {
                enrolledStudents.add(st);
            }
        }
        // We won't set up a LiveData observer here for a single course’s enrollments. We'll just set the list once. 
        // In a bigger design, we might do something fancier with triggers or transformations.
        studentAdapter.submitList(enrolledStudents);
    }

    private void showStudentOptionsDialog(Student student) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Student Options")
                .setMessage("Choose an action for " + student.name)
                .setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // navigate to EditStudentActivity
                        Intent intent = new Intent(CourseDetailsActivity.this, EditStudentActivity.class);
                        intent.putExtra("studentId", student.studentId);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Remove", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // remove from this course only
                        enrollmentViewModel.removeStudentFromCourse(courseId, student.studentId);
                        loadStudentsForCourse(courseId); // refresh
                    }
                })
                .create()
                .show();
    }
}
