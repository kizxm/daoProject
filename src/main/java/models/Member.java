package models;

public class Member {
    private String name;
    private String contact;
    private int id;
    private int memId;


    public Member(String name, int memId) {
        this.name = name;
        this.contact = contact;
        this.memId = memId;
    }



    ///..getters & setters..///
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getMemId() { return memId; }
    public void setMemId(int memId) { this.memId = memId; }
    ///....................///

    ///..Member.java Overrides..///
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;

        if (id != member.id) return false;
        if (memId != member.memId) return false;
        if (contact != member.contact) return false;
        return name.equals(member.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + contact.hashCode();
        result = 31 * result + id;
        result = 31 * result + memId;
        return result;
    }
    ///.......................///
}
