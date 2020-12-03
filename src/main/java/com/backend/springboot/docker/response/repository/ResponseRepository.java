package com.backend.springboot.docker.response.repository;

import com.backend.springboot.docker.response.entity.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long> {

    boolean existsByPerson_PersonIdAndQuestion_QuestionId(Long personId, Long questionId);

    Response findByPerson_PersonIdAndQuestion_QuestionId(Long person, Long questionId);

    List<Response> findByQuestion_QuestionId(Long questionId);
}
