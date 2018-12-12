package models;

public class Team {
    String login;
    String password;
    int id;
    int advisor_id;
    int clas_id;

    public Team(Team team) {
        this.login = team.login;
        this.password = team.password;
        this.id = team.id;
        this.advisor_id = team.advisor_id;
        this.clas_id = team.clas_id;
    }

    public Team() {

    }

    public String getLogin() {
        return login;
    }

    public Team withLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Team withPassword(String password) {
        this.password = password;
        return this;
    }

    public int getId() {
        return id;
    }

    public Team withId(int id) {
        this.id = id;
        return this;
    }

    public int getAdvisor_id() {
        return advisor_id;
    }

    public Team withAdvisor_id(int advisor_id) {
        this.advisor_id = advisor_id;
        return this;
    }

    public int getClas_id() {
        return clas_id;
    }

    public Team withClas_id(int clas_id) {
        this.clas_id = clas_id;
        return this;
    }
}
