package models;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class TeamSet extends ForwardingSet {

    private Set<Team> delegate = new HashSet<>();

    public TeamSet(TeamSet teamSet) {
        this.delegate = new HashSet<Team>(teamSet.delegate());
    }

    public TeamSet() {

    }


    @Override
    protected Set delegate() {
        return delegate;
    }

    public TeamSet withTeam(Team new_team){
        this.add(new_team);
        return this;
    }

    public TeamSet withTeams(TeamSet new_teams){
        this.add(new_teams);
        return this;
    }

    public TeamSet withoutTeam(Team del_team){
        this.remove(del_team);
        return this;
    }

    public TeamSet withoutTeams(TeamSet del_teams){
        this.remove(del_teams);
        return this;
    }





}
