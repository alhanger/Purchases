package com.theironyard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by alhanger on 11/11/15.
 */
public interface PurchaseRepository extends CrudRepository<Purchase, Integer> {
    List<Purchase> findByCategory(String category);

    @Query("from Purchase p order by p.category asc")
    List<Purchase> orderByAsc();

    @Query("from Purchase p order by p.category desc")
    List<Purchase> orderByDsc();
}
