package com.example.testing;

import java.util.ArrayList;
import java.util.Collections;

public class Test {

    private ArrayList<Question> questions;
    private GameOverListener gameOverListener;
    private int index = -1;
    private int score = 0;

    public Test() {
        questions = getQuestions();
        Collections.shuffle(questions);
    }

    public void setGameOverListener(GameOverListener listener) {
        gameOverListener = listener;
    }

    public Question getNextQuestion() {
        return questions.get(++index);
    }

    public boolean isFinished() {
        return index >= questions.size();
    }

    public boolean hasNext() {
        return index < questions.size()-1;
    }

    public void increaseScore() {
        score++;
    }

    public String getScore() {
        return String.valueOf(score);
    }

    public String getIndex() {
        return String.valueOf(index + 1);
    }

    public void checkAnswer(int answer) {
        if (questions.get(index).answerIsCorrect(answer)) {
            score += 100;
        } else {
            gameOver();
        }
    }

    private void gameOver() {
        if (gameOverListener != null) {
            gameOverListener.onGameOver();
        }
    }

    private static ArrayList<Question> getQuestions() {
        ArrayList<Question> questions = new ArrayList<>();

        questions.add(new Question("Question", "VariantA", "VariantB", "VariantC", "VariantD", 1));
        questions.add(new Question("Question", "VariantA", "VariantB", "VariantC", "VariantD", 1));
        questions.add(new Question("Question", "VariantA", "VariantB", "VariantC", "VariantD", 1));
        questions.add(new Question("Question", "VariantA", "VariantB", "VariantC", "VariantD", 1));
        questions.add(new Question("Question", "VariantA", "VariantB", "VariantC", "VariantD", 1));

        return questions;
    }

    interface GameOverListener {
        void onGameOver();
    }
}
