package com.codecademy.unquote;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView questionImageView;
    TextView questionTextView;
    TextView questionsRemainingTextView;
    Button answer0Button;
    Button answer1Button;
    Button answer2Button;
    Button answer3Button;
    Button submitButton;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionImageView = findViewById(R.id.iv_main_question_image);
        questionTextView = findViewById(R.id.tv_main_question_title);
        questionsRemainingTextView = findViewById(R.id.tv_main_questions_remaining);
        answer0Button = findViewById(R.id.btn_main_answer_0);
        answer1Button = findViewById(R.id.btn_main_answer_1);
        answer2Button = findViewById(R.id.btn_main_answer_2);
        answer3Button = findViewById(R.id.btn_main_answer_3);
        submitButton = findViewById(R.id.btn_main_submit_answer);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_unquote_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setElevation(0);

        startNewGame();

        answer0Button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                onAnswerSelected(0);
            }
        });

        answer1Button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                onAnswerSelected(1);
            }
        });

        answer2Button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                onAnswerSelected(2);
            }
        });

        answer3Button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                onAnswerSelected(3);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                onAnswerSubmission();
            }
        });
    }

    // TODO #1: add integer member variables here
    int currentQuestionIndex;
    int totalCorrect;
    int totalQuestions;
    // TODO #2: add ArrayList member variable here
    ArrayList<Question> questions = new ArrayList<>();
    // TODO #3 add startNewGame() here
    public void startNewGame()
    {
        Question question0 = new Question(R.drawable.img_quote_0, "Pretty good advice,\n" +
                "and perhaps a scientist\n" +
                "did say it… Who\n" +
                "actually did?", "Albert Einstein", "Isaac Newton", "Rita Mae Brown", "Rosalind Franklin", 2);

        Question question1 = new Question(R.drawable.img_quote_1, "Was honest Abe\n" +
                "honestly quoted? Who\n" +
                "authored this pithy bit\n" +
                "of wisdom?", "Edward Stieglitz", "Maya Angelou", "Abraham Lincoln", "Ralph Waldo Emerson", 0);

        Question question2 = new Question(R.drawable.img_quote_2, "Easy advice to read,\n" +
                "difficult advice to\n" +
                "follow — who actually", "Martin Luther King Jr.", "Mother Teresa", "Fred Rogers", "Oprah Winfrey", 1);

        Question question3 = new Question(R.drawable.img_quote_3, "Insanely inspiring,\n" +
                "insanely incorrect\n" +
                "(maybe). Who is the\n" +
                "true source of this\n" +
                "inspiration?", "Nelson Mandela", "Harriet Tubman", "Mahatma Gandhi", "Nicholas Klein", 3);

        Question question4 = new Question(R.drawable.img_quote_4, "A peace worth striving\n" +
                "for — who actually\n" +
                "reminded us of this?", "Malala Yousafzai", "Martin Luther King Jr.", "Liu Xiaobo", "Dalai Lama", 1);

        Question question5 = new Question(R.drawable.img_quote_5, "Unfortunately, true —\n" +
                "but did Marilyn Monroe\n" +
                "convey it or did\n" +
                "someone else?", "Laurel Thatcher Ulrich", "Eleanor Roosevelt", "Marilyn Monroe", "Queen Victoria", 0);

        ArrayList<Question> question = new ArrayList<>();
        questions = question;
        questions.add(question0);
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);
        questions.add(question4);
        questions.add(question5);

        totalCorrect = 0;
        totalQuestions = 0;

        Question firstQuestion = chooseNewQuestion();
        displayQuestion(firstQuestion);
        displayQuestionsRemaining(questions.size());
    }

    public void displayQuestionsRemaining(int remainingQuestions)
    {
        questionsRemainingTextView.setText(String.valueOf(remainingQuestions) + " questions remaining");
    }

    public void displayQuestion(Question firstQuestion)
    {
        questionImageView.setImageResource(firstQuestion.imageId);
        questionTextView.setText(firstQuestion.questionText);
        answer0Button.setText(firstQuestion.answer0);
        answer1Button.setText(firstQuestion.answer1);
        answer2Button.setText(firstQuestion.answer2);
        answer3Button.setText(firstQuestion.answer3);
    }

    // TODO #4 add chooseNewQuestion() here

    public void onAnswerSelected(int answer) {
        Question currentQuestion = getCurrentQuestion();
        currentQuestion.playerAnswer = answer;

        if (answer0Button.isPressed()) {
            answer0Button.setText("✔ " + currentQuestion.answer0);
            answer1Button.setText(currentQuestion.answer1);
            answer2Button.setText(currentQuestion.answer2);
            answer3Button.setText(currentQuestion.answer3);
        }
        if (answer1Button.isPressed()) {
            answer1Button.setText("✔ " + currentQuestion.answer1);
            answer0Button.setText(currentQuestion.answer0);
            answer2Button.setText(currentQuestion.answer2);
            answer3Button.setText(currentQuestion.answer3);
        }

        if (answer2Button.isPressed()) {
            answer2Button.setText("✔ " + currentQuestion.answer2);
            answer1Button.setText(currentQuestion.answer1);
            answer0Button.setText(currentQuestion.answer0);
            answer3Button.setText(currentQuestion.answer3);
        }

        if (answer3Button.isPressed()) {
            answer3Button.setText("✔ " + currentQuestion.answer3);
            answer1Button.setText(currentQuestion.answer1);
            answer2Button.setText(currentQuestion.answer2);
            answer0Button.setText(currentQuestion.answer0);
        }
    }

    public Question chooseNewQuestion()
    {
        int number = generateRandomNumber(2);
        currentQuestionIndex = number;

        // TODO: uncomment after implementing displayQuestion()
        //    displayQuestion(getCurrentQuestion());
        return questions.get(currentQuestionIndex);
    }
    // TODO #5 add getCurrentQuestion() here
    public Question getCurrentQuestion()
    {
        return questions.get(currentQuestionIndex);
    }
    // TODO #6 add onAnswerSubmission() here
    public void onAnswerSubmission() {
        Question question = getCurrentQuestion();
        if (question.isCorrect()) {
            totalCorrect += 1;
            questions.remove(questions.get(currentQuestionIndex));
        }

        questions.remove(question);

        displayQuestionsRemaining(questions.size());

        if (questions.size() == 0) {
            String gameOverMessage = getGameOverMessage(totalCorrect, totalQuestions);

            AlertDialog.Builder gameOverDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            gameOverDialogBuilder.setCancelable(false);
            gameOverDialogBuilder.setTitle("GAME OVER!!!!!!");
            gameOverDialogBuilder.setMessage(gameOverMessage);
            gameOverDialogBuilder.setPositiveButton("Play Again!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startNewGame();
                }
            });
            gameOverDialogBuilder.show();
            System.out.println(gameOverMessage);
        } else {
            chooseNewQuestion();
            getGameOverMessage(totalCorrect, totalQuestions);
        }
    }

    int generateRandomNumber(int max) {
        double randomNumber = Math.random();
        double result = max * randomNumber;
        return (int) result;
    }

    public String getGameOverMessage(int totalCorrect, int totalQuestions) {
        if (totalCorrect == totalQuestions) {
            return "You got all " + totalQuestions + " right! You won!";
        } else {
            return "You got " + totalCorrect + " right out of " + totalQuestions + ". Better luck next time!";
        }
    }
}