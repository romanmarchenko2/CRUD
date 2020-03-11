package com.marchenko.student.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
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

    public String getName() {
      return name;
   }

    public void setName(String name) {
      this.name = name;
   }

    public String getSurname() {
      return surname;
   }

    public void setSurname(String surname) { this.surname = surname; }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }


}


