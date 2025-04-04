package uk.edu.le.co2124assignment.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import uk.edu.le.co2124assignment.R;
import uk.edu.le.co2124assignment.db.Course;
import uk.edu.le.co2124assignment.ui.adapters.CourseListAdapter;
import uk.edu.le.co2124assignment.viewmodel.CourseViewModel;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private CourseViewModel courseViewModel;
    private RecyclerView recyclerView;
    private CourseListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerCourses);
        adapter = new CourseListAdapter(new CourseListAdapter.CourseDiff(), course -> {
            // On item click
            Intent intent = new Intent(MainActivity.this, CourseDetailsActivity.class);
            intent.putExtra("courseId", course.courseId);
            startActivity(intent);
        }, course -> {
            // On item long click => delete
            Toast.makeText(MainActivity.this, "Deleting course: " + course.courseCode, Toast.LENGTH_SHORT).show();
            courseViewModel.deleteCourse(course.courseId);
            return true; // to indicate we handled the long-click
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        courseViewModel.getAllCourses().observe(this, new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courses) {
                // Update RecyclerView
                adapter.submitList(courses);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fabAddCourse);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CreateCourseActivity.class);
            startActivity(intent);
        });
    }
}
