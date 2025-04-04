package uk.edu.le.co2124assignment.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;
import uk.edu.le.co2124assignment.db.Student;
import uk.edu.le.co2124assignment.repository.StudentRepository;

public class StudentViewModel extends AndroidViewModel {

    private StudentRepository repository;
    private LiveData<List<Student>> allStudents;

    public StudentViewModel(@NonNull Application application) {
        super(application);
        repository = new StudentRepository(application);
        allStudents = repository.getAllStudents();
    }

    public LiveData<List<Student>> getAllStudents() {
        return allStudents;
    }

    public void insert(Student student) {
        repository.insert(student);
    }

    public void deleteStudent(Student student) {
        repository.deleteStudent(student);
    }

    public void updateStudent(int studentId, String name, String email, String userName) {
        repository.updateStudent(studentId, name, email, userName);
    }

    public Student getStudentSync(int studentId) {
        return repository.getStudentSync(studentId);
    }
}
