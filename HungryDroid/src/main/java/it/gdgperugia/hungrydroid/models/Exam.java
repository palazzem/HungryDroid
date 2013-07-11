package it.gdgperugia.hungrydroid.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Exam {
    private String courseName;
    private String registrationDate;
    private String vote;
    private String honor;
    private String notes;

    public Exam(JSONObject exam) {
        // Extract data from JSONObject
        try {
            setCourseName(exam.getString("course"));
            setRegistrationDate(exam.getString("registration_date"));
            setVote(exam.getString("vote"));
            setHonor(exam.getString("honors"));
            setNotes(exam.getString("note"));
        } catch (JSONException e) {
            // TODO: log the error
        }
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public String getHonor() {
        return honor;
    }

    public void setHonor(String honor) {
        this.honor = honor;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return getCourseName();
    }
}
