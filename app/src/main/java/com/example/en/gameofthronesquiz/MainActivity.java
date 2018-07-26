package com.example.en.gameofthronesquiz;

//Design and implement a short quiz app about some topic you are familiar with.

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.text.TextUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //This activity is hosting this layout.
    }

    /**
     * This method is called when the check button is pressed.
     */
    public void gradeResults(View view) {
        int numberOfCorrectAnswers = 0; //Correct answers counter

        //Check question types with radio buttons.
        //Check first question.
        RadioGroup firstQuestion = (RadioGroup) findViewById(R.id.question_one_radio_group);
        RadioButton correctAnswerOfFirstQuestion = (RadioButton) findViewById(R.id.question_one_answer_radio_button);
        boolean isFirstQuestionCorrect = this.checkRadioGroupQuestion(firstQuestion, correctAnswerOfFirstQuestion);
        if (isFirstQuestionCorrect) {
            numberOfCorrectAnswers++;
        }
        //Check second question.
        RadioGroup secondQuestion = (RadioGroup) findViewById(R.id.question_two_radio_group);
        RadioButton correctAnswerOfSecondQuestion = (RadioButton) findViewById(R.id.question_two_answer_radio_button);
        boolean isSecondQuestionCorrect = this.checkRadioGroupQuestion(secondQuestion, correctAnswerOfSecondQuestion);
        if (isSecondQuestionCorrect) {
            numberOfCorrectAnswers++;
        }
        //Check fourth question.
        RadioGroup fourthQuestion = (RadioGroup) findViewById(R.id.question_four_radio_group);
        RadioButton correctAnswerOfFourthQuestion = (RadioButton) findViewById(R.id.question_four_answer_radio_button);
        boolean isFourthQuestionCorrect = this.checkRadioGroupQuestion(fourthQuestion, correctAnswerOfFourthQuestion);
        if (isFourthQuestionCorrect) {
            numberOfCorrectAnswers++;
        }
        //Check sixth question.
        RadioGroup sixthQuestion = (RadioGroup) findViewById(R.id.question_six_radio_group);
        RadioButton correctAnswerOfSixthQuestion = (RadioButton) findViewById(R.id.question_six_answer_radio_button);
        if (this.checkRadioGroupQuestion(sixthQuestion, correctAnswerOfSixthQuestion)) {
            numberOfCorrectAnswers++;
        }

        //Check question types with check boxes.
        //Check fifth question
        if (this.checkFifthQuestion()) {
            numberOfCorrectAnswers++;
        }

        //Check question types with text input.
        //Check third question
        if (this.checkThirdQuestion()) {
            numberOfCorrectAnswers++;
        }

        //Show results.
        showTheResults(numberOfCorrectAnswers);
    }

    /**
     * This method will shows results in a Toast message.
     *
     * @param numberOfCorrectAnswers argument containing the number of correct answers.
     */
    private void showTheResults(int numberOfCorrectAnswers) {
        int numberOfQuestions = 6;
        String message;
        if (numberOfCorrectAnswers == 0) {
            message = getString(R.string.toastMessageZero);
        } else if (numberOfCorrectAnswers == 1) {
            message = getString(R.string.toastMessageEqualToOne, numberOfQuestions, numberOfCorrectAnswers);
        } else if (numberOfCorrectAnswers == numberOfQuestions) {
            message = getString(R.string.toastMessageCorrect);
        } else {
            message = getString(R.string.toastMessageMoreThanOne, numberOfQuestions, numberOfCorrectAnswers);
        }
        //Show the results in a Toast message
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    /**
     * This method checks whether a question that has radio buttons is correct or not.
     *
     * @param radioGroupQuestion       the RadioGroup view to be checked
     * @param radioButtonCorrectAnswer the integer value that represents the correct option.
     * @return returns a boolean value, true if the correct option is selected and false if not.
     */
    private boolean checkRadioGroupQuestion(RadioGroup radioGroupQuestion, RadioButton radioButtonCorrectAnswer) {
        //Find the ID of selected radio button.
        int radioButtonSelectedID = radioGroupQuestion.getCheckedRadioButtonId();
        //Catch the selected radio button in an object, so it can be compared to the radio button that is the correct answer.
        RadioButton radioButtonSelected = (RadioButton) findViewById(radioButtonSelectedID);
        boolean isCorrect = false;
        if (radioButtonSelected == radioButtonCorrectAnswer) {
            isCorrect = true;
        }
        return isCorrect;
    }

    /**
     * This method checks the fifth question whether the correct options are selected.
     *
     * @return Returns true if all the correct options are selected, otherwise returns false.
     */
    private boolean checkFifthQuestion() {
        CheckBox firstOption = findViewById(R.id.first_option_question_five_check_box);
        CheckBox secondOption = findViewById(R.id.second_option_question_five_check_box);
        CheckBox thirdOption = findViewById(R.id.third_option_question_five_check_box);
        CheckBox incorrectAnswer = findViewById(R.id.question_five_incorrect_check_box);
        if (firstOption.isChecked() && secondOption.isChecked() && thirdOption.isChecked() && !(incorrectAnswer.isChecked())) {
            return true;
        }
        return false;
    }

    /**
     * This method checks whether the name entered on the EditText view is correct.
     *
     * @return returns true only if the name entered is correct.
     */
    private boolean checkThirdQuestion() {
        EditText textEntered = (EditText) findViewById(R.id.question_three_field);
        //Make sure the text to be checked isn't empty.
        if (!(textEntered.getText().toString().isEmpty())) {
            //Make sure the text to be checked is digits only (numbers: 0, 1, 2, 3 ... and not characters).
            if (TextUtils.isDigitsOnly(textEntered.getText().toString())) {
                if (Integer.parseInt(textEntered.getText().toString()) == Integer.parseInt(getString(R.string.thirdQuestionAnswer))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method clears the edit text field from previous entered input.
     *
     * @param view
     */
    public void clearField(View view) {
        EditText textField = (EditText) findViewById(R.id.question_three_field);
        textField.getText().clear();
    }

}
