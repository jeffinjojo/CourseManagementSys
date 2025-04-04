package uk.edu.le.co2124assignment.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

import uk.edu.le.co2124assignment.repository.EnrollmentRepository;

public class EnrollmentViewModel extends AndroidViewModel {

    private EnrollmentRepository repository;

    public EnrollmentViewModel(@NonNull Application application) {
        super(application);
        repository = new EnrollmentRepository(application);
    }

    public void enrollStudent(int courseId, int studentId) {
        repository.enrollStudent(courseId, studentId);
    }

    public void removeStudentFromCourse(int courseId, int studentId) {
        repository.removeStudentFromCourse(courseId, studentId);
    }

    public void removeAllStudentsFromCourse(int courseId) {
        repository.removeAllStudentsFromCourse(courseId);
    }

    public List<Integer> getStudentIdsForCourseSync(int courseId) {
        return repository.getStudentIdsForCourseSync(courseId);
    }

    public List<Integer> getCourseIdsForStudentSync(int studentId) {
        return repository.getCourseIdsForStudentSync(studentId);
    }
}
