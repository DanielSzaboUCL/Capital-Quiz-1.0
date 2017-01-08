package com.danielszabo.android.capitalquiz;


public class Ranking {
    private int Id;
    private int Score;
    private String Name;

    public Ranking(int id, int score, String name) {
        Id = id;
        Score = score;
        Name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getScore() {
        return Score;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
