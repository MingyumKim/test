package com.test.test.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
public class Member extends JpaBaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long no;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String pw;

    //getter, setter

}
