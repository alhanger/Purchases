package com.theironyard;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Created by alhanger on 11/11/15.
 */
@Entity
public class Customer {

    @Id
    @GeneratedValue
    Integer id;

    String name;
    String email;

    @OneToMany
    Purchase purchases;
}
