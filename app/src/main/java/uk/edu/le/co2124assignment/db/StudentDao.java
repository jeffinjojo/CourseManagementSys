package uk.edu.le.co2124assignment.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Delete;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StudentDao {

    @Insert
    void insert(Student student);

    @Delete
    void delete(Student student);

    @Query("SELECT * FROM students ORDER BY name ASC")
    LiveData<List<Student>> getAllStudents();

    // For retrieving a single student (if we want direct access)
    @Query("SELECT * FROM students WHERE studentId = :sId LIMIT 1")
    Student getOneStudentSync(int sId);

    // Update student details by ID (manually)
    @Query("UPDATE students SET name = :name, email = :email, user_name = :userName WHERE studentId = :sId")
    void updateStudent(int sId, String name, String email, String userName);
}
