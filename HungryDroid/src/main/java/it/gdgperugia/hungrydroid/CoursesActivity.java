package it.gdgperugia.hungrydroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class CoursesActivity extends Activity {
    private TextView resultText = null;
    private Button fetchButton = null;
    private ProgressBar progressBar = null;

    private RequestQueue requests = null;
    private String examAPI = "http://students-exam.herokuapp.com/api/exam/.json";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        // Object mapping
        resultText = (TextView) findViewById(R.id.resultText);
        fetchButton = (Button) findViewById(R.id.fetchButton);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        // Volley!
        requests = Volley.newRequestQueue(this);
    }

    public void fetchExams(View v) {
        fetchingAnimation(true);
        JsonArrayRequest request = new JsonArrayRequest(examAPI,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        fetchingAnimation(false);
                        resultText.setText("Response => " + response.toString());
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
        resultText.setVisibility(isLoading ? View.GONE : View.VISIBLE);
        progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }
}