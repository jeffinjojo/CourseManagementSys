package uk.edu.le.co2124assignment.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;

import java.util.List;
import uk.edu.le.co2124assignment.db.AppDatabase;
import uk.edu.le.co2124assignment.db.Course;
import uk.edu.le.co2124assignment.db.CourseDao;

public class CourseRepository {
    private CourseDao courseDao;
    private LiveData<List<Course>> allCourses;

    public CourseRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        courseDao = db.courseDao();
        allCourses = courseDao.getAllCourses();
    }

    public LiveData<List<Course>> getAllCourses() {
        return allCourses;
    }

    public void insert(Course course) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            courseDao.insert(course);
        });
    }

    public void deleteCourse(int courseId) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            courseDao.deleteCourseById(courseId);
        });
    }

    public Course getCourseSync(int courseId) {
        // This is synchronous, so be careful if calling from UI thread
        return courseDao.getOneCourseSync(courseId);
    }
}
