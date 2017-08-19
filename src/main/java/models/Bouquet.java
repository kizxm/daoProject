package models;

import java.util.ArrayList;

public class Bouquet {
    private String teamName;
    private String teamDesc;
    private int id;


    public Bouquet(String teamName, String teamDesc) {
        this.teamName = teamName;
        this.teamDesc = teamDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bouquet bouquet = (Bouquet) o;

        if (id != bouquet.id) return false;
        if (!teamName.equals(bouquet.teamName)) return false;
        return teamDesc.equals(bouquet.teamDesc);
    }

    @Override
    public int hashCode() {
        int result = teamName.hashCode();
        result = 31 * result + teamDesc.hashCode();
        result = 31 * result + id;
        return result;
    }


    ///..getters & setters..///
    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }
    public String getTeamDesc() { return teamDesc; }
    public void setTeamDesc(String teamDesc) { this.teamDesc = teamDesc; }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    ///....................///
}





    ///..Obsolete from previous generation..///
    ///..Placed in order of removal..///
    //    private String flower3;
    //    private String flower4;
    //    private static ArrayList<Bouquet> allFlowers = new ArrayList<Bouquet>();
    //
    //    allFlowers.add(this);
    //    this.id = allFlowers.size();
    //    public void update(String flower1, String flower2, String flower3, String flower4) {
    //        this.flower1 = flower1;
    //        this.flower2 = flower2;
    //        this.flower3 = flower3;
    //        this.flower4 = flower4;
    //    }
    //        public static Bouquet findById(int id){
    //        return allFlowers.get(id-1)}
    //        public int getId() {return id;}
    //        public static void clearAllFlowers(){allFlowers.clear();}
    //        public static ArrayList<Bouquet> getAll() {return allFlowers;}
    //        public String getFlower1() {return flower1;}
    //        public String getFlower2() {return flower2;}
    //        public String getFlower3() {return flower3;}
    //        public String getFlower4() {return flower4;}
    //        public static ArrayList<Bouquet> getAllFlowers() {return allFlowers;}