package it.gdgperugia.hungrydroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import it.gdgperugia.hungrydroid.models.Exam;

public class CoursesActivity extends Activity {
    private TextView resultText = null;
    private Button fetchButton = null;
    private ProgressBar progressBar = null;
    private ListView coursesList = null;
    private ArrayAdapter<Exam> adapter = null;

    private RequestQueue requests = null;
    private String examAPI = "http://students-exam.herokuapp.com/api/exam/.json";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        // Object mapping
        resultText = (TextView) findViewById(R.id.resultText);
        fetchButton = (Button) findViewById(R.id.fetchButton);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        coursesList = (ListView) findViewById(R.id.coursesList);

        // ListView stuff!
        adapter = new ArrayAdapter<Exam>(getApplicationContext(), android.R.layout.simple_list_item_1, new ArrayList<Exam>());
        coursesList.setAdapter(adapter);

        // Volley!
        requests = Volley.newRequestQueue(this);
    }

    public void fetchExams(View v) {
        fetchingAnimation(true);
        adapter.clear();

        JsonArrayRequest request = new JsonArrayRequest(examAPI,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        fetchingAnimation(false);
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject exam = response.getJSONObject(i);
                                adapter.add(new Exam(exam));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        resultText.setText("Error => " + error.getMessage());
                    }
                }
        );
        requests.add(request);
    }

    private void fetchingAnimation(boolean isLoading) {
        resultText.setVisibility(View.GONE);
        progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        coursesList.setVisibility(isLoading ? View.GONE : View.VISIBLE);
    }
}