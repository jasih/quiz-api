package com.cooksys.quiz_api.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.cooksys.quiz_api.dtos.QuestionRequestDto;
import com.cooksys.quiz_api.dtos.QuestionResponseDto;
import com.cooksys.quiz_api.dtos.QuizRequestDto;
import com.cooksys.quiz_api.dtos.QuizResponseDto;
import com.cooksys.quiz_api.entities.Answer;
import com.cooksys.quiz_api.entities.Question;
import com.cooksys.quiz_api.entities.Quiz;
import com.cooksys.quiz_api.mappers.QuestionMapper;
import com.cooksys.quiz_api.mappers.QuizMapper;
import com.cooksys.quiz_api.repositories.AnswerRepository;
import com.cooksys.quiz_api.repositories.QuestionRepository;
import com.cooksys.quiz_api.repositories.QuizRepository;
import com.cooksys.quiz_api.services.QuizService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

	private final QuizRepository quizRepository;
	private final QuizMapper quizMapper;

	private final QuestionRepository questionRepository;
	private final QuestionMapper questionMapper;

	private final AnswerRepository answerRepository;

	private Quiz getQuiz(Long id) {
		Optional<Quiz> quiz = quizRepository.findByIdAndDeletedFalse(id);
		return quiz.get();
	}
	
	private Question getQuestion(Long questionID) {
		Optional<Question> question = questionRepository.findByIdAndDeletedFalse(questionID);
		return question.get();
	}

	@Override
	public List<QuizResponseDto> getAllQuizzes() {
		return quizMapper.entitiesToDtos(quizRepository.findAllByDeletedFalse());
	}

	// returns the created quiz
	@Override
	public QuizResponseDto createQuiz(QuizRequestDto quizRequestDTO) {
		Quiz quiz = quizMapper.dtoToEntity(quizRequestDTO);
		quizRepository.saveAndFlush(quiz);
		quiz.setDeleted(false);
		for (Question question : quiz.getQuestions()) {
			question.setQuiz(quiz);
			question.setDeleted(false);
			questionRepository.saveAndFlush(question);
			for (Answer answer : question.getAnswers()) {
				answer.setQuestion(question);
				answer.setDeleted(false);
				answerRepository.saveAndFlush(answer);
			}
		}
		return quizMapper.entityToDto(quiz);
	}

	// returns the deleted quiz from the repository
	// 1. get the specified quiz to delete
	// 2. set the quiz to delete to true (meaning it should only be deleted when
	// this method is called)
	// 3. return deleted quiz and save to repository
	@Override
	public QuizResponseDto deleteQuiz(Long id) {
		Quiz quizToDelete = getQuiz(id);
		quizToDelete.setDeleted(true);
		return quizMapper.entityToDto(quizRepository.saveAndFlush(quizToDelete));
	}

	// returns the specified quiz with a newName
	// 1. get the specified quiz to rename
	// 2. set the quiz to a newName
	// 3. return that quiz with its newName and save to the repository
	@Override
	public QuizResponseDto renameQuiz(Long id, String newName) {
		Quiz quizToRename = getQuiz(id);
		quizToRename.setName(newName);
		return quizMapper.entityToDto(quizRepository.saveAndFlush(quizToRename));
	}

	// returns a random question from a specified quiz id
	// 1. get the specified quiz to retrieve the random question from
	// 2. create a randomizer to get the random question (use Math.random function)
	// 3. get the random question using the randomizer
	// 4. return the random question
	@Override
	public QuestionResponseDto randomQuizQuestion(Long id) {
		Quiz quizToGetRandomQuestion = getQuiz(id);
		List<Question> questions = quizToGetRandomQuestion.getQuestions();
		Random randomizer = new Random();
		Question randomQuestionFromQuiz = questions.get(randomizer.nextInt(questions.size()));
		return questionMapper.entityToDto(randomQuestionFromQuiz);
	}

	//TODO
	// returns the specified quiz with the added question using quiz id
	// 1. get the specific quiz to add questions to
	// 2. create the question and set it in the quiz
	// 4. return quiz with added question and save to the repository
	@Override
	public QuizResponseDto addQuestionToQuiz(Long id, QuestionRequestDto questionRequestDto) {
		Quiz quizToAddQuestionTo = getQuiz(id);
		Question questionToAdd = questionMapper.dtoToEntity(questionRequestDto);
		
		questionToAdd.setQuiz(quizToAddQuestionTo);
		questionRepository.saveAndFlush(questionToAdd);
		
		questionToAdd.setAnswers(questionToAdd.getAnswers());
		
		for (Answer answer : questionToAdd.getAnswers()) {
			answer.setQuestion(questionToAdd);
			answerRepository.saveAndFlush(answer);
		}
		quizToAddQuestionTo.getQuestions().add(questionToAdd);
		return quizMapper.entityToDto(quizRepository.saveAndFlush(quizToAddQuestionTo));
	}

	
	// returns the deleted specified question from the specified quiz using the quiz
	// and question id
	// 1. get the specified quiz to delete from
	// 2. get the list of questions from that quiz to delete 
	// 3. get the id of the question to delete
	// 4. iterate through the questions in the quiz and if the question remove it from the quiz
	// 5. return the deleted question and save to the repository
	@Override
	public QuestionResponseDto deleteQuestionFromQuiz(Long id, Long questionID) {
		Quiz quizToDeleteQuestionFrom = getQuiz(id);
		Question questionToDelete = getQuestion(questionID);
		
		List<Question> questionList = quizToDeleteQuestionFrom.getQuestions();
		
		for (Question question : questionList) {
			if (question.getQuestionID() == questionID) {
				questionToDelete.setDeleted(true);
			}
		}
		
		quizToDeleteQuestionFrom.setQuestions(questionList);
		return questionMapper.entityToDto(questionRepository.saveAndFlush(questionToDelete));
	}
}
