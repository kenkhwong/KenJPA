package org.springframework.ken.jpa;

import javax.persistence.*;

@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue
    private long id;
}

