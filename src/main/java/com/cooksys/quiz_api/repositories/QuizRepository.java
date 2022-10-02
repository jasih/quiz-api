package com.cooksys.quiz_api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.quiz_api.entities.Question;
import com.cooksys.quiz_api.entities.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

	// TODO: Do you need any derived queries? If so add them here.
	Optional<Quiz> findByIdAndDeletedFalse(Long id);
	
	List<Quiz> findAllByDeletedFalse();

	//void saveAndFlush(Question question);

}
