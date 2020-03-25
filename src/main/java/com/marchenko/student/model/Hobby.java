package com.marchenko.student.model;


import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@ToString
@Table(name = "HOBBY")
public class Hobby {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "hobby_id")
    private Long id;

    @ManyToOne(optional=false, cascade=CascadeType.ALL)
    @JoinColumn(name = "subject")
    private Student subject;

    public Student getSubject() {
        return subject;
    }

    public void setSubject(Student subject) {
        this.subject = subject;
    }
}
