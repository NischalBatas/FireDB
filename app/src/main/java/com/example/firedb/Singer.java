package com.example.firedb;

public class Singer {
    private String singerID;
    private String singerName;
    private String singerGenre;

    //default constructor
    public Singer(){
    }

    //parameterized constructor
    public Singer(String singerID, String singerName, String singerGenre) {
        this.singerID = singerID;
        this.singerName = singerName;
        this.singerGenre = singerGenre;
    }

    //getter
    public String getSingerID() {
        return singerID;
    }

    public String getSingerName() {
        return singerName;
    }

    public String getSingerGenre() {
        return singerGenre;
    }




}
