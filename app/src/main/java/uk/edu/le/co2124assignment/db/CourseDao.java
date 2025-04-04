package uk.edu.le.co2124assignment.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Delete;
import java.util.List;

@Dao
public interface CourseDao {

    @Insert
    void insert(Course course);

    @Delete
    void delete(Course course);

    @Query("SELECT * FROM courses ORDER BY course_code ASC")
    LiveData<List<Course>> getAllCourses();

    // For removing a course by ID, if needed
    @Query("DELETE FROM courses WHERE courseId = :cId")
    void deleteCourseById(int cId);

    // For retrieving a single course
    @Query("SELECT * FROM courses WHERE courseId = :cId LIMIT 1")
    Course getOneCourseSync(int cId);
}
