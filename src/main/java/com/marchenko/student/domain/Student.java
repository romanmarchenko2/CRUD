package com.marchenko.student.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter

@AllArgsConstructor
@ToString
@Table(name = "student")
 public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "student_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<Marks> Marks;

    public Student(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Student() {}
}


