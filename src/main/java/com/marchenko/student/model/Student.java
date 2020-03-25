package com.marchenko.student.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;
@Data
@Entity
@Table(name = "STUDENT")
 public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "student_id")
    private Long id;

    @Size(min=2, max=15)
    @Column(name = "name")
    private String name;

    @Size(min=2, max=30)
    @Column(name = "surname")
    private String surname;

    @Size(min=2, max=30)
    @Column(name = "hobby")
    private String hobby;

    @OneToMany(mappedBy = "subject", fetch=FetchType.EAGER)
    private Set<Hobby> stud_subject;

    public Student() {
    }
    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

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

    public Set<Hobby> getStud_subject() {
        return stud_subject;
    }

    public void setStud_subject(Set<Hobby> stud_subject) {
        this.stud_subject = stud_subject;
    }
}


