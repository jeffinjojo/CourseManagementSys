package uk.edu.le.co2124assignment.repository;

import android.app.Application;
import uk.edu.le.co2124assignment.db.AppDatabase;
import uk.edu.le.co2124assignment.db.Enrollment;
import uk.edu.le.co2124assignment.db.EnrollmentDao;
import java.util.List;

public class EnrollmentRepository {
    private EnrollmentDao enrollmentDao;

    public EnrollmentRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        enrollmentDao = db.enrollmentDao();
    }

    public void enrollStudent(int courseId, int studentId) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            enrollmentDao.insert(new Enrollment(courseId, studentId));
        });
    }

    public void removeStudentFromCourse(int courseId, int studentId) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            enrollmentDao.removeStudentFromCourse(courseId, studentId);
        });
    }

    public void removeAllStudentsFromCourse(int courseId) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            enrollmentDao.removeAllStudentsFromCourse(courseId);
        });
    }

    public List<Integer> getStudentIdsForCourseSync(int courseId) {
        return enrollmentDao.getAllStudentIdsForCourse(courseId);
    }

    public List<Integer> getCourseIdsForStudentSync(int studentId) {
        return enrollmentDao.getAllCourseIdsForStudent(studentId);
    }
}
