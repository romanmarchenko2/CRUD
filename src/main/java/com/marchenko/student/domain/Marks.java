package com.marchenko.student.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "marks")
public class Marks {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "marks_id")
    private Long id;

    @Column(name = "subject")
    private String subject;
    
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

}
