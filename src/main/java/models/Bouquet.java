package models;

import java.util.ArrayList;

public class Team {
    private String teamName;
    private String descName;
    private String memberName;

    private int id;
    private static ArrayList<Team> allFlowers = new ArrayList<Team>();

    public Team(String teamName, String descName, String memberName) {
        this.teamName = teamName;
        this.descName = descName;
        this.memberName = memberName;
        allFlowers.add(this);
        this.id = allFlowers.size();
    }

    public void update(String teamName, String descName, String memberName) {
        this.teamName = teamName;
        this.descName = descName;
        this.memberName = memberName;
    }

    public static Team findById(int id){
        return allFlowers.get(id-1);
    }
    public int getId() {
        return id;
    }




    public static void clearAllFlowers() {
        allFlowers.clear();
    }

    public static ArrayList<Team> getAll() {
        return allFlowers;
    }

    /// getters //////
    public String getteamName() {
        return teamName;
    }
    public String getdescName() {
        return descName;
    }
    public String getmemberName() { return memberName; }
    public static ArrayList<Team> getAllFlowers() {
        return allFlowers;
    }
    ////////////////
}