package uk.edu.le.co2124assignment.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "enrollments")
public class Enrollment {
    // For simplicity, autoGenerate a single primary key for each row in the enrollment table
    @PrimaryKey(autoGenerate = true)
    public int enrollmentId;

    public int courseId;   // references Course.courseId
    public int studentId;  // references Student.studentId

    public Enrollment(int courseId, int studentId) {
        this.courseId = courseId;
        this.studentId = studentId;
    }
}
