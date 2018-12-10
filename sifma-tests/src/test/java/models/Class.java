package models;

import java.util.List;

public class Class {
    int id;
    String name;
    String grade;
    String num_students;
    String num_teams;
    List<Team> teams;

    public List<Team> getTeams() {
        return teams;
    }

    public Class withTeams(List<Team> teams) {
        this.teams = teams;
        return this;
    }

    public String getName() {
        return name;
    }

    public Class withName(String name) {
        this.name = name;
        return this;
    }

    public String getGrade() {
        return grade;
    }

    public Class withGrade(String grade) {
        this.grade = grade;
        return this;
    }

    public String getNum_students() {
        return num_students;
    }

    public Class withNum_students(String num_students) {
        this.num_students = num_students;
        return this;
    }

    public String getNum_teams() {
        return num_teams;
    }

    public Class withNum_teams(String num_teams) {
        this.num_teams = num_teams;
        return this;
    }

    public int getId() {
        return id;
    }

    public Class withId(int id) {
        this.id = id;
        return this;
    }
}
