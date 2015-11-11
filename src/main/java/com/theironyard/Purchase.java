package com.theironyard;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by alhanger on 11/11/15.
 */
@Entity
public class Purchase {

    @Id
    @GeneratedValue
    Integer id;

    String category;
    String date;
    String cardNum;
    String validation;

    @ManyToOne
    Customer customer;
}
