package com.sanemaniac.calculationgame;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    CountDownTimer gameTimer;
    Button btnStartStop, btnConfirm;
    TextView tvTimer, tvEquations, tvScore, tvO1, tvO2, tvO3, tvO4;
    Boolean timerStatus, gameStatus;

    int[] options = new int[4];
    int val1, val2, answer, score, totalQuestions;
    String userAns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String initTime = "30";
        tvTimer = findViewById(R.id.tvTimer);
        tvTimer.setText(initTime + "s");

        tvScore = findViewById(R.id.tvScore);
        tvScore.setText("0/0");

        btnConfirm = findViewById(R.id.btnConfirm);
        btnConfirm.setVisibility(View.INVISIBLE);

        timerStatus = false;
        gameStatus = false;
    }

    public void startGame(View view) {
        if (timerStatus == false) {
            timerStatus = true;
            tvTimer = findViewById(R.id.tvTimer);
            btnStartStop = findViewById(R.id.btnStartGame);
            btnStartStop.setText("Cancel");
            btnConfirm = findViewById(R.id.btnConfirm);
            btnConfirm.setVisibility(View.VISIBLE);

            score = 0;
            totalQuestions = 0;

            playGame();

            gameTimer = new CountDownTimer(31000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    String timeLeft = String.valueOf(millisUntilFinished / 1000) + "s";
                    tvTimer.setText(timeLeft);
                }

                @Override
                public void onFinish() {
                    timerStatus = false;
                    btnStartStop.setText("Start");
                    btnConfirm.setVisibility(View.INVISIBLE);
                }
            }.start();
        } else {
            gameTimer.cancel();
            timerStatus = false;
            btnStartStop.setText("Start");
            btnConfirm.setVisibility(View.INVISIBLE);
        }
    }

    public void playGame() {
        tvEquations = findViewById(R.id.tvEquations);
        tvScore = findViewById(R.id.tvScore);

        tvO1 = findViewById(R.id.tvOption1);
        tvO2 = findViewById(R.id.tvOption2);
        tvO3 = findViewById(R.id.tvOption3);
        tvO4 = findViewById(R.id.tvOption4);


        int upperBoundEq = 10;
        int upperBoundsAns = 20;
        int optionBounds = 4;
        Random eqVals = new Random();
        Random possibleAns = new Random();

        val1 = eqVals.nextInt(upperBoundEq + 1);
        val2 = eqVals.nextInt(upperBoundEq + 1);
        answer = val1 + val2;


        Log.i("val1", String.valueOf(val1));
        Log.i("val2", String.valueOf(val2));
        Log.i("ans", String.valueOf(answer));


        for (int i = 0; i < 4; i++) {
            options[i] = possibleAns.nextInt(upperBoundsAns);
            Log.i("opt" + i, String.valueOf(options[i]));
        }
        options[possibleAns.nextInt(optionBounds)] = answer;

        tvEquations.setText(val1 + " + " + val2 + " = ");
        tvO1.setText(String.valueOf(options[0]));
        tvO2.setText(String.valueOf(options[1]));
        tvO3.setText(String.valueOf(options[2]));
        tvO4.setText(String.valueOf(options[3]));

/*        if (tvAmt == 1) {
            userAns = tvO1.getText().toString();
        } else if (tvAmt == 2) {
            userAns = tvO2.getText().toString();
        } else if (tvAmt == 3) {
            userAns = tvO3.getText().toString();
        } else {
            userAns = tvO4.getText().toString();
        }
        Log.i("User answer", String.valueOf(userAns));*/
    }

    public void opt1Selected(View view) {
        if (timerStatus == true) {
            Log.i("1", "selected");
            tvO1 = findViewById(R.id.tvOption1);
            userAns = String.valueOf(tvO1.getText().toString());
            tvEquations = findViewById(R.id.tvEquations);
            tvEquations.setText(String.valueOf(val1) + " + " + String.valueOf(val2) + " = " + userAns);
        }
    }
    public void opt2Selected(View view) {
        if (timerStatus == true) {
            Log.i("2", "selected");
            tvO2 = findViewById(R.id.tvOption2);
            userAns = String.valueOf(tvO2.getText().toString());
            tvEquations = findViewById(R.id.tvEquations);
            tvEquations.setText(String.valueOf(val1) + " + " + String.valueOf(val2) + " = " + userAns);
        }
    }
    public void opt3Selected(View view) {
        if (timerStatus == true) {
            Log.i("3", "selected");
            tvO3 = findViewById(R.id.tvOption3);
            userAns = String.valueOf(tvO3.getText().toString());
            tvEquations = findViewById(R.id.tvEquations);
            tvEquations.setText(String.valueOf(val1) + " + " + String.valueOf(val2) + " = " + userAns);
        }
    }
    public void opt4Selected(View view) {
        if (timerStatus == true) {
            Log.i("4", "selected");
            tvO4 = findViewById(R.id.tvOption4);
            userAns = String.valueOf(tvO4.getText().toString());
            tvEquations = findViewById(R.id.tvEquations);
            tvEquations.setText(String.valueOf(val1) + " + " + String.valueOf(val2) + " = " + userAns);
        }
    }

    public void checkAns(View view) {
        totalQuestions++;
        if (userAns == String.valueOf(answer)) {
            score++;
        }
        tvScore = findViewById(R.id.tvScore);
        tvScore.setText(score + "/" + totalQuestions);
        Log.i("Answer", "confirmed");
        playGame();
    }
}