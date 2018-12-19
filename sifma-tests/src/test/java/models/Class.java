package models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Class {
    int advisor_id;
    int id;
    String name;
    String grade;
    String num_students;
    String num_teams_requested;
    String num_teams_assigned;
    TeamSet teams = new TeamSet();

    public Class(Class old_class) {
        this.id = old_class.id;
        this.name = old_class.name;
        this.grade = old_class.grade;
        this.num_students = old_class.num_students;
        this.num_teams_requested = old_class.num_teams_requested;
        this.num_teams_assigned = old_class.num_teams_assigned;
        this.teams = old_class.teams;
    }

    public Class() {

    }

    public String getNum_teams_assigned() {
        return num_teams_assigned;
    }

    public Class withNum_teams_assigned(String num_teams_assigned) {
        this.num_teams_assigned = num_teams_assigned;
        return this;
    }

    public TeamSet getTeams() {
        return new TeamSet(this.teams);
    }

    public Class addTeam(Team new_team){
        Team add_team = new Team(new_team);
        add_team.clas_id = this.id;
        add_team.advisor_id = this.advisor_id;
        this.teams.add(add_team);
        return this;
    }

    public Class addTeams(Collection<Team> new_teams){
        for(Team team : new_teams){
            this.addTeam(team);
        }
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

    public String getNum_teams_requested() {
        return num_teams_requested;
    }

    public Class withNum_teams_requested(String num_teams) {
        this.num_teams_requested = num_teams;
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
