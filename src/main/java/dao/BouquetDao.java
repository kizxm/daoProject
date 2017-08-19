package dao;

import models.Bouquet;
import models.Member;

import java.util.List;

public interface BouquetDao {

    ///..create..///
    void add (Bouquet bouquet);

    ///..read..///
    List<Member> getAllBouquetMembers(int memId);
    List<Bouquet> getAll();

    ///..update..///
    ///..delete..///
}
