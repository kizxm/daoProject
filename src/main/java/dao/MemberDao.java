package dao;
import models.Member;
import java.util.List;

public interface MemberDao {


    ///..create..///
    void add (Member member);

    ///..read..///
    List<Member> getAll();

    ///..update..///
    Member findId(int id);
    void update(int id, String name, int memId);

    ///..delete..///
    void deleteId(int id); //single by id
    void deleteMembers(); //all members






}
