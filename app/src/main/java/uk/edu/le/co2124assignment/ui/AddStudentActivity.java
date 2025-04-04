package uk.edu.le.co2124assignment.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import uk.edu.le.co2124assignment.R;
import uk.edu.le.co2124assignment.db.Student;
import uk.edu.le.co2124assignment.viewmodel.EnrollmentViewModel;
import uk.edu.le.co2124assignment.viewmodel.StudentViewModel;

public class AddStudentActivity extends AppCompatActivity {

    private static final String TAG = "AddStudentActivity";

    private EditText etStudentName, etStudentEmail, etStudentUserName;
    private Button btnAddStudent;
    private StudentViewModel studentViewModel;
    private EnrollmentViewModel enrollmentViewModel;
    private int courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        courseId = getIntent().getIntExtra("courseId", -1);

        etStudentName = findViewById(R.id.etStudentName);
        etStudentEmail = findViewById(R.id.etStudentEmail);
        etStudentUserName = findViewById(R.id.etStudentUserName);
        btnAddStudent = findViewById(R.id.btnAddStudent);

        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);
        enrollmentViewModel = new ViewModelProvider(this).get(EnrollmentViewModel.class);

        btnAddStudent.setOnClickListener(view -> {
            String name = etStudentName.getText().toString().trim();
            String email = etStudentEmail.getText().toString().trim();
            String userName = etStudentUserName.getText().toString().trim();

            if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(userName)) {
                // Check if a student with the same userName is already enrolled in this course
                List<Integer> enrolledIds = enrollmentViewModel.getStudentIdsForCourseSync(courseId);
                boolean alreadyEnrolled = false;
                for (int sid : enrolledIds) {
                    Student st = studentViewModel.getStudentSync(sid);
                    if (st != null && st.userName.equalsIgnoreCase(userName)) {
                        alreadyEnrolled = true;
                        break;
                    }
                }
                if (alreadyEnrolled) {
                    Toast.makeText(AddStudentActivity.this, "Student already enrolled", Toast.LENGTH_SHORT).show();
                } else {
                    // create and insert student
                    Student newStudent = new Student(name, email, userName);
                    studentViewModel.insert(newStudent);

                    // We need the ID of that newly inserted student. In a real app we’d do something more advanced to get it.
                    // As a clumsy approach, we can retrieve all students again and match by userName
                    // (This is simplistic and might fail if multiple students have same userName.)
                    List<Student> allSt = studentViewModel.getAllStudents().getValue();
                    if (allSt != null) {
                        for (Student s : allSt) {
                            if (s.userName.equalsIgnoreCase(userName)) {
                                enrollmentViewModel.enrollStudent(courseId, s.studentId);
                                break;
                            }
                        }
                    }

                    finish();
                }
            } else {
                Toast.makeText(this, "Please fill in all student details", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
