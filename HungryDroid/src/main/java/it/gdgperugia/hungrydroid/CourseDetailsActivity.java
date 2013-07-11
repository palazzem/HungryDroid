package it.gdgperugia.hungrydroid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class CourseDetailsActivity extends Activity {
    private TextView courseNameText = null;
    private TextView registrationDateText = null;
    private TextView voteText = null;
    private TextView honorText = null;
    private TextView NotesText = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        courseNameText = (TextView) findViewById(R.id.courseName);
        registrationDateText = (TextView) findViewById(R.id.courseDate);
        voteText = (TextView) findViewById(R.id.courseVote);
        honorText = (TextView) findViewById(R.id.courseHonor);
        NotesText = (TextView) findViewById(R.id.courseNote);

        // Fetch data from Intent
        courseNameText.setText(getIntent().getStringExtra("courseName"));
        registrationDateText.setText(getIntent().getStringExtra("registrationDate"));
        voteText.setText(getIntent().getStringExtra("vote"));
        honorText.setText(getIntent().getStringExtra("honor"));
        NotesText.setText(getIntent().getStringExtra("notes"));
    }
}