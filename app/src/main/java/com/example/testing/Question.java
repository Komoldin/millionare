package com.example.testing;

public class Question {

    private String question;
    private String var1;
    private String var2;
    private String var3;
    private String var4;
    private int correctAns;

    public Question(String question, String var1, String var2, String var3, String var4, int correctAns) {
        this.question = question;
        this.var1 = var1;
        this.var2 = var2;
        this.var3 = var3;
        this.var4 = var4;
        this.correctAns = correctAns;
    }

    public String getQuestion() {
        return question;
    }

    public boolean answerIsCorrect(String answer) {
        return answer.equals(var1) && correctAns == 1 ||
                answer.equals(var2) && correctAns == 2 ||
                answer.equals(var3) && correctAns == 3 ||
                answer.equals(var4) && correctAns == 4;
    }

    public boolean answerIsCorrect(int answer) {
        return correctAns == answer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getVar1() {
        return var1;
    }

    public String getVar2() {
        return var2;
    }

    public String getVar3() {
        return var3;
    }

    public String getVar4() {
        return var4;
    }
}
