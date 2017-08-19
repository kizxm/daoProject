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
    Bouquet findByBouquetId(int id);

    ///..update..///
    void update(int id, String name, String description);

    ///..delete..///
    void deleteByBouquetId(int id);
    void deleteAllBouquets();
}
