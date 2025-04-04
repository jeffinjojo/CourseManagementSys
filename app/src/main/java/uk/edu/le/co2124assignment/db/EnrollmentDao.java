package uk.edu.le.co2124assignment.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface EnrollmentDao {

    @Insert
    void insert(Enrollment enrollment);

    // Remove the relationship between a student and a course
    @Query("DELETE FROM enrollments WHERE courseId = :cId AND studentId = :sId")
    void removeStudentFromCourse(int cId, int sId);

    // Deleting a course => remove all enrollments for that course
    @Query("DELETE FROM enrollments WHERE courseId = :cId")
    void removeAllStudentsFromCourse(int cId);

    // Deleting a student => remove them from all courses
    @Query("DELETE FROM enrollments WHERE studentId = :sId")
    void removeStudentFromAllCourses(int sId);

    // Returns all the studentIds for a particular course
    @Query("SELECT studentId FROM enrollments WHERE courseId = :cId")
    List<Integer> getAllStudentIdsForCourse(int cId);

    // Returns all the courseIds a particular student is enrolled in
    @Query("SELECT courseId FROM enrollments WHERE studentId = :sId")
    List<Integer> getAllCourseIdsForStudent(int sId);
}
