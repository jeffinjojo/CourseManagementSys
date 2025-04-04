package uk.edu.le.co2124assignment.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;
import uk.edu.le.co2124assignment.db.AppDatabase;
import uk.edu.le.co2124assignment.db.Student;
import uk.edu.le.co2124assignment.db.StudentDao;

public class StudentRepository {
    private StudentDao studentDao;
    private LiveData<List<Student>> allStudents;

    public StudentRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        studentDao = db.studentDao();
        allStudents = studentDao.getAllStudents();
    }

    public LiveData<List<Student>> getAllStudents() {
        return allStudents;
    }

    public void insert(Student student) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            studentDao.insert(student);
        });
    }

    public void deleteStudent(Student student) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            studentDao.delete(student);
        });
    }

    public void updateStudent(int studentId, String name, String email, String userName) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            studentDao.updateStudent(studentId, name, email, userName);
        });
    }

    public Student getStudentSync(int studentId) {
        return studentDao.getOneStudentSync(studentId);
    }
}
