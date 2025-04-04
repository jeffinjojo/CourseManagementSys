package uk.edu.le.co2124assignment.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "students")
public class Student {

    @PrimaryKey(autoGenerate = true)
    public int studentId;

    @NonNull
    @ColumnInfo(name = "name")
    public String name;

    @NonNull
    @ColumnInfo(name = "email")
    public String email;

    @NonNull
    @ColumnInfo(name = "user_name")
    public String userName;

    public Student(@NonNull String name,
                   @NonNull String email,
                   @NonNull String userName) {
        this.name = name;
        this.email = email;
        this.userName = userName;
    }
}
