package ru.samsung.itschool.book;

public class Situation {
    Situation[] direction;
    String subject, text;
    int dR, dI, dE;

    public Situation (String subject, String text, int variants, int dr, int di, int de) {
        this.subject = subject;
        this.text = text;
        dR = dr;
        dI = di;
        dE = de;
        direction = new Situation[variants];
    }
}
