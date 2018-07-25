package com.example.hibernatemetadata.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TEST")
@Data
public class Test {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue
    private Long id;

    @Column(name = "NAME")
    private String name;
}
