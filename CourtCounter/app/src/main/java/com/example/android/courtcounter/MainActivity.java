package com.example.android.courtcounter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Tracks score for TeamA
    int scoreForA = 0;
    //Tracks score for TeamA
    int scoreForB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Adds 3 in Score for TeamA
    public void addThreeForTeamA(View view) {
        scoreForA += 3;
        displayScoreOfTeamA(scoreForA);
    }

    //Adds 2 in Score for TeamA
    public void addTwoForTeamA(View view) {
        scoreForA += 2;
        displayScoreOfTeamA(scoreForA);
    }

    //Adds 1 in Score for TeamA
    public void addOneForTeamA(View view) {
        scoreForA++;
        displayScoreOfTeamA(scoreForA);
    }

    //Displays the score for TeamA
    public void displayScoreOfTeamA(int i) {
        TextView textView = (TextView)findViewById(R.id.teamA_Score);
        textView.setText(String.valueOf(i));
    }

    //Adds 3 in Score for TeamB
    public void addThreeForTeamB(View view) {
        scoreForB += 3;
        displayScoreOfTeamB(scoreForB);
    }

    //Adds 2 in Score for TeamB
    public void addTwoForTeamB(View view) {
        scoreForB += 2;
        displayScoreOfTeamB(scoreForB);
    }

    //Adds 1 in Score for TeamA
    public void addOneForTeamB(View view) {
        scoreForB++;
        displayScoreOfTeamB(scoreForB);
    }

    //Displays the score for TeamB
    public void displayScoreOfTeamB(int i) {
        TextView textView = (TextView) findViewById(R.id.teamB_Score);
        textView.setText(String.valueOf(i));
    }

    public void resetScores(View view) {
        scoreForA = 0;
        scoreForB = 0;
        displayScoreOfTeamA(scoreForA);
        displayScoreOfTeamB(scoreForB);
    }
}
