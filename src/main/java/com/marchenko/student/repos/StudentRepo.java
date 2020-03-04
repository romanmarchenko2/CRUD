package com.marchenko.student.repos;

import com.marchenko.student.domain.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepo extends CrudRepository<Student,Long> {

}
