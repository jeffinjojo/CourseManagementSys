package uk.edu.le.co2124assignment.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "courses")
public class Course {

    @PrimaryKey(autoGenerate = true)
    public int courseId;

    @NonNull
    @ColumnInfo(name = "course_code")
    public String courseCode;

    @NonNull
    @ColumnInfo(name = "course_name")
    public String courseName;

    @NonNull
    @ColumnInfo(name = "lecturer_name")
    public String lecturerName;

    public Course(@NonNull String courseCode,
                  @NonNull String courseName,
                  @NonNull String lecturerName) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.lecturerName = lecturerName;
    }
}
