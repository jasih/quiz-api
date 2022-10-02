package com.cooksys.quiz_api;

import java.util.Arrays;

import com.cooksys.quiz_api.entities.Answer;
import com.cooksys.quiz_api.entities.Question;
import com.cooksys.quiz_api.entities.Quiz;
import com.cooksys.quiz_api.repositories.AnswerRepository;
import com.cooksys.quiz_api.repositories.QuestionRepository;
import com.cooksys.quiz_api.repositories.QuizRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Seeder implements CommandLineRunner {

  private final QuizRepository quizRepository;
  private final QuestionRepository questionRepository;
  private final AnswerRepository answerRepository;

  /**
   * This method seeds the database with 2 quizzes. Each quiz has 4 questions and
   * each question has 4 possible answers. Feel free to edit this seeder, but note
   * that the order in which you create and save quizzes/questions/answers to the
   * database does matter.
   */
  @Override
  public void run(String... args) throws Exception {
    Quiz quiz1 = new Quiz();
    quiz1.setName("Game of Thrones Quiz");

    Quiz quiz2 = new Quiz();
    quiz2.setName("Things About Janet Quiz");

    quizRepository.saveAll(Arrays.asList(new Quiz[] { quiz1, quiz2 }));

    Question question1 = new Question();
    question1.setText("Which of the following is NOT one of Daenerys's dragons?");
    question1.setQuiz(quiz1);

    Question question2 = new Question();
    question2.setText("Which of the following kingdoms is, on average, furthest south?");
    question2.setQuiz(quiz1);

    Question question3 = new Question();
    question3.setText("Who brought Jon Snow back from the dead?");
    question3.setQuiz(quiz1);

    Question question4 = new Question();
    question4.setText("Which of the following is NOT one of Daenerys's titles?");
    question4.setQuiz(quiz1);

    Question question5 = new Question();
    question5.setText("Which of the following is Janet's favorite color?");
    question5.setQuiz(quiz2);

    Question question6 = new Question();
    question6.setText("Which of the following is Janet's favorite snacks?");
    question6.setQuiz(quiz2);

    Question question7 = new Question();
    question7.setText("Which of the following is Janet's favorite hobbies or pasttimes?");
    question7.setQuiz(quiz2);

    Question question8 = new Question();
    question8.setText("Which of the following is Janet's favorite boba flavor?");
    question8.setQuiz(quiz2);

    questionRepository.saveAll(Arrays.asList(
        new Question[] { question1, question2, question3, question4, question5, question6, question7, question8 }));

    Answer answer1 = new Answer();
    answer1.setText("Aegon");
    answer1.setCorrect(true);
    answer1.setQuestion(question1);

    Answer answer2 = new Answer();
    answer2.setText("Viserion");
    answer2.setQuestion(question1);

    Answer answer3 = new Answer();
    answer3.setText("Rhaegal");
    answer3.setQuestion(question1);

    Answer answer4 = new Answer();
    answer4.setText("Drogon");
    answer4.setQuestion(question1);

    Answer answer5 = new Answer();
    answer5.setText("The Riverlands");
    answer5.setQuestion(question2);

    Answer answer6 = new Answer();
    answer6.setText("The Reach");
    answer6.setCorrect(true);
    answer6.setQuestion(question2);

    Answer answer7 = new Answer();
    answer7.setText("The Vale");
    answer7.setQuestion(question2);

    Answer answer8 = new Answer();
    answer8.setText("Dorne");
    answer8.setQuestion(question2);

    Answer answer9 = new Answer();
    answer9.setText("Jaqen H'ghar");
    answer9.setCorrect(true);
    answer9.setQuestion(question3);

    Answer answer10 = new Answer();
    answer10.setText("Brandon Stark");
    answer10.setQuestion(question3);

    Answer answer11 = new Answer();
    answer11.setText("The Children of the Forest");
    answer11.setQuestion(question3);

    Answer answer12 = new Answer();
    answer12.setText("Melisandre");
    answer12.setQuestion(question3);

    Answer answer13 = new Answer();
    answer13.setText("The Unsullied");
    answer13.setQuestion(question4);

    Answer answer14 = new Answer();
    answer14.setText("Breaker of Chains");
    answer14.setCorrect(true);
    answer14.setQuestion(question4);

    Answer answer15 = new Answer();
    answer15.setText("Khaleesi");
    answer15.setQuestion(question4);

    Answer answer16 = new Answer();
    answer16.setText("Mother of Dragons");
    answer16.setQuestion(question4);

    Answer answer17 = new Answer();
    answer17.setText("Pink");
    answer17.setCorrect(true);
    answer17.setQuestion(question5);

    Answer answer18 = new Answer();
    answer18.setText("Blue");
    answer18.setQuestion(question5);

    Answer answer19 = new Answer();
    answer19.setText("Purple");
    answer19.setQuestion(question5);

    Answer answer20 = new Answer();
    answer20.setText("Black");
    answer20.setQuestion(question5);

    Answer answer21 = new Answer();
    answer21.setText("Takis");
    answer21.setQuestion(question6);

    Answer answer22 = new Answer();
    answer22.setText("Sour gummy worms");
    answer22.setCorrect(true);
    answer22.setQuestion(question6);

    Answer answer23 = new Answer();
    answer23.setText("Pocky");
    answer23.setQuestion(question6);

    Answer answer24 = new Answer();
    answer24.setText("Ruffles potato chips");
    answer24.setQuestion(question6);

    Answer answer25 = new Answer();
    answer25.setText("Art");
    answer25.setCorrect(true);
    answer25.setQuestion(question7);

    Answer answer26 = new Answer();
    answer26.setText("Writing");
    answer26.setQuestion(question7);

    Answer answer27 = new Answer();
    answer27.setText("Gaming");
    answer27.setQuestion(question7);

    Answer answer28 = new Answer();
    answer28.setText("Watching TV shows or movies");
    answer28.setQuestion(question7);

    Answer answer29 = new Answer();
    answer29.setText("Strawberry Matcha");
    answer29.setQuestion(question8);

    Answer answer30 = new Answer();
    answer30.setText("Brown Sugar");
    answer30.setCorrect(true);
    answer30.setQuestion(question8);

    Answer answer31 = new Answer();
    answer31.setText("Classic");
    answer31.setQuestion(question8);

    Answer answer32 = new Answer();
    answer32.setText("Honeydew");
    answer32.setQuestion(question8);

    answerRepository.saveAll(Arrays.asList(new Answer[] { answer1, answer2, answer3, answer4, answer5, answer6, answer7,
        answer8, answer9, answer10, answer11, answer12, answer13, answer14, answer15, answer16, answer17, answer18,
        answer19, answer20, answer21, answer22, answer23, answer24, answer25, answer26, answer27, answer28, answer29,
        answer30, answer31, answer32 }));

  }

}
