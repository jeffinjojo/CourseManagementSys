package uk.edu.le.co2124assignment.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import uk.edu.le.co2124assignment.R;
import uk.edu.le.co2124assignment.db.Course;
import uk.edu.le.co2124assignment.viewmodel.CourseViewModel;

public class CreateCourseActivity extends AppCompatActivity {

    private static final String TAG = "CreateCourseActivity";
    private EditText etCourseCode, etCourseName, etLecturerName;
    private CourseViewModel courseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_course);

        etCourseCode = findViewById(R.id.etCourseCode);
        etCourseName = findViewById(R.id.etCourseName);
        etLecturerName = findViewById(R.id.etLecturerName);

        Button btnCreate = findViewById(R.id.btnCreateCourse);

        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = etCourseCode.getText().toString().trim();
                String name = etCourseName.getText().toString().trim();
                String lecturer = etLecturerName.getText().toString().trim();

                if (!code.isEmpty() && !name.isEmpty() && !lecturer.isEmpty()) {
                    Course course = new Course(code, name, lecturer);
                    courseViewModel.insert(course);
                }

                finish(); // go back to MainActivity
            }
        });
    }
}
