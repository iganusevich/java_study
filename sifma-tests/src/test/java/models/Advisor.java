package models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Advisor {
    int id;
    int num_classes;
    int num_teams;
    String last_name;
    String login;
    String password;
    private List<Class> classes = new ArrayList<Class>();

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
        return Collections.unmodifiableList(classes);
    }

    public Advisor addClass(Class new_class) {
         Class add_class = new Class(new_class);
         add_class.withId(this.id);
         this.classes.add(new_class);
         return this;
    }

    public Advisor addClasses(Collection<Class> new_classes) {
         for(Class new_class : new_classes){
             this.addClass(new_class);
         }
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

    public int getId() {
        return id;
    }

    public Advisor withId(int id) {
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
