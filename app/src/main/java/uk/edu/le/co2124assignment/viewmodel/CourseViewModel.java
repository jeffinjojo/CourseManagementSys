package uk.edu.le.co2124assignment.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;
import uk.edu.le.co2124assignment.db.Course;
import uk.edu.le.co2124assignment.repository.CourseRepository;

public class CourseViewModel extends AndroidViewModel {

    private CourseRepository repository;
    private LiveData<List<Course>> allCourses;

    public CourseViewModel(@NonNull Application application) {
        super(application);
        repository = new CourseRepository(application);
        allCourses = repository.getAllCourses();
    }

    public LiveData<List<Course>> getAllCourses() {
        return allCourses;
    }

    public void insert(Course course) {
        repository.insert(course);
    }

    public void deleteCourse(int courseId) {
        repository.deleteCourse(courseId);
    }

    public Course getCourseSync(int courseId) {
        return repository.getCourseSync(courseId);
    }
}
