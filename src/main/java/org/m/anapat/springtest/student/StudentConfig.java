package org.m.anapat.springtest.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student anapat = new Student("Anapat", "anapat.m@gmail.com", LocalDate.of(2000, Month.SEPTEMBER, 5));
            Student praneed = new Student("Praneed", "praneed.b@gmail.com", LocalDate.of(2000, Month.OCTOBER, 9));
            studentRepository.saveAll(
                    List.of(anapat, praneed)
            );
        };
    }

}
