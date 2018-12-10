package models;

import java.util.List;

public class Advisor {
    String id;
    int num_classes;
    int num_teams;
    String last_name;
    String login;
    String password;
    List<Class> classes;

     public void incNumTeamsBy(int num){
         this.num_teams += num;
     }

    public void incNumClassesBy(int num){
        this.num_classes += num;
    }

    public void decNumTeamsBy(int num){
        this.num_teams -= num;
    }

    public void decNumClassesBy(int num){
        this.num_classes -= num;
    }




    public String getLogin() {
        return login;
    }

    public Advisor withLogin(String login) {
        this.login = login;
        return this;
    }

    public List<Class> getClasses() {

        return classes;
    }

    public Advisor withClasses(List<Class> classes) {
        this.classes = classes;
        return this;

    }
    public String getPassword() {
        return password;
    }

    public Advisor withPassword(String password) {
        this.password = password;
        return this;
    }

    public String getLast_name() {
        return last_name;
    }

    public Advisor withLast_name(String last_name) {
        this.last_name = last_name;
        return this;
    }

    public String getId() {
        return id;
    }

    public Advisor withId(String id) {
        this.id = id;
        return this;
    }

    public int getNum_classes() {
        return num_classes;
    }

    public Advisor withNum_classes(int num_classes) {
        this.num_classes = num_classes;
        return this;
    }

    public int getNum_teams() {
        return num_teams;
    }

    public Advisor withNum_teams(int num_teams) {
        this.num_teams = num_teams;
        return this;
    }

    public Advisor() {

    }
}
