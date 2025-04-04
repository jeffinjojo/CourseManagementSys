package uk.edu.le.co2124assignment.db;

public class DbSeeder {
    public static void seed(AppDatabase db) {
        // Just a small example so the app isn't empty on first launch
        db.courseDao().deleteCourseById(-1); // dummy line just for example
        db.enrollmentDao().removeStudentFromCourse(-1, -1); // dummy line

        Course c1 = new Course("CO2124", "Intro to Programming", "Dr. Alice");
        db.courseDao().insert(c1);

        Course c2 = new Course("CO3001", "Software Architecture", "Prof. Bob");
        db.courseDao().insert(c2);

        Student s1 = new Student("John Doe", "j.doe@uni.ac.uk", "jd123");
        db.studentDao().insert(s1);

        Student s2 = new Student("Jane Smith", "jsmith@uni.ac.uk", "js456");
        db.studentDao().insert(s2);

        // Example enrollment
        // For this to work, we need the actual IDs after insertion. In a real app you'd fetch them.
        // This is just a fake approach:
        // We’ll do a quick hack: getOneCourseSync(1) etc.
        Course co1 = db.courseDao().getOneCourseSync(1);
        Student st1 = db.studentDao().getOneStudentSync(1);
        if (co1 != null && st1 != null) {
            db.enrollmentDao().insert(new Enrollment(co1.courseId, st1.studentId));
        }
    }
}
