package com.example.testing;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TestLayout extends LinearLayout implements View.OnClickListener {
    private Test test;

    private TextView tvScore;
    private TextView tvQuestion;
    private Button btA;
    private Button btB;
    private Button btC;
    private Button btD;
    private Test.GameOverListener gameOverListener;

    public TestLayout(Context context) {
        super(context);
        init();
    }

    public TestLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setTest(Test test) {
        this.test = test;
        if (gameOverListener != null) {
            test.setGameOverListener(gameOverListener);
        }
    }

    private void init() {
        gameOverListener = new Test.GameOverListener() {
            @Override
            public void onGameOver() {
                showGameOverDialog();
            }
        };

        initUI();
        setOnClickListeners();
    }

    private void showGameOverDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext())
                .setMessage(String.format("You lost the game! Your score: %s", test.getScore()))
                .setPositiveButton("RESTART", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        restart();
                    }
                });
        dialog.show();
    }

    private void restart() {
        setTest(new Test());
        start();
    }

    private void initUI() {
        View.inflate(getContext(), R.layout.test_layout, this);

        tvScore = findViewById(R.id.tvScore);
        tvQuestion = findViewById(R.id.tvQuestion);
        btA = findViewById(R.id.btAVariant);
        btB = findViewById(R.id.btBVariant);
        btC = findViewById(R.id.btCVariant);
        btD = findViewById(R.id.btDVariant);
    }

    private void setOnClickListeners() {
        btA.setOnClickListener(this);
        btB.setOnClickListener(this);
        btC.setOnClickListener(this);
        btD.setOnClickListener(this);
    }

    public void start() {
        nextTest();
    }

    private void nextTest()
    {
        if (test.hasNext()) {
            showTest();
        } else {
            showFinishDialog();
        }
    }

    private void showTest() {
        Question question = test.getNextQuestion();

        tvScore.setText(String.format("Score: %s", test.getScore()));
        tvQuestion.setText(String.format("%s. %s", test.getIndex(), question.getQuestion()));
        btA.setText(String.format("a) %s", question.getVar1()));
        btB.setText(String.format("b) %s", question.getVar2()));
        btC.setText(String.format("c) %s", question.getVar3()));
        btD.setText(String.format("d) %s", question.getVar4()));
    }

    private void showFinishDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext())
                .setMessage(String.format("You won the game! Your score: %s", test.getScore()))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        restart();
                    }
                });
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btAVariant:
                checkAnswerAndGoOn(1);
                break;
            case R.id.btBVariant:
                checkAnswerAndGoOn(2);
                break;
            case R.id.btCVariant:
                checkAnswerAndGoOn(3);
                break;
            case R.id.btDVariant:
                checkAnswerAndGoOn(4);
                break;
        }
    }

    private void checkAnswerAndGoOn(int answer) {
        test.checkAnswer(answer);
        nextTest();
    }
}
