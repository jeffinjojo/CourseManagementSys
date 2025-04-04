package uk.edu.le.co2124assignment.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import uk.edu.le.co2124assignment.R;
import uk.edu.le.co2124assignment.db.Student;
import uk.edu.le.co2124assignment.viewmodel.StudentViewModel;

public class EditStudentActivity extends AppCompatActivity {

    private static final String TAG = "EditStudentActivity";

    private EditText etName, etEmail, etUserName;
    private Button btnSave;
    private int studentId;
    private StudentViewModel studentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        etName = findViewById(R.id.etEditStudentName);
        etEmail = findViewById(R.id.etEditStudentEmail);
        etUserName = findViewById(R.id.etEditStudentUserName);
        btnSave = findViewById(R.id.btnSaveStudentChanges);

        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);

        studentId = getIntent().getIntExtra("studentId", -1);
        if (studentId != -1) {
            Student st = studentViewModel.getStudentSync(studentId);
            if (st != null) {
                etName.setText(st.name);
                etEmail.setText(st.email);
                etUserName.setText(st.userName);
            }
        }

        btnSave.setOnClickListener(view -> {
            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String userN = etUserName.getText().toString().trim();
            if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(userN)) {
                studentViewModel.updateStudent(studentId, name, email, userN);
            }
            finish();
        });
    }
}
