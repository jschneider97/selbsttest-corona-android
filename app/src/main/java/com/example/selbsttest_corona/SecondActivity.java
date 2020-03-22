package com.example.selbsttest_corona;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wirvsvirus.selftest.api.enums.QuestionType;
import com.wirvsvirus.selftest.api.selftest.Answer;
import com.wirvsvirus.selftest.api.selftest.ChoiceAnswer;
import com.wirvsvirus.selftest.api.selftest.DateAnswer;
import com.wirvsvirus.selftest.api.selftest.Question;

import java.util.Date;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.wirvsvirus.selftest.api.enums.QuestionType.CHOICE_QUESTION;
import static com.wirvsvirus.selftest.api.enums.QuestionType.DATE_QUESTION;

public class SecondActivity extends AppCompatActivity {
    int numberofAnswers;
    Question nextQuestion;
    public void getQuestion(){
        Single<Question> keineAhnungWas = Communication.getInstance().getQuestion();
        final CompositeDisposable comepositeDisposable = new CompositeDisposable();
        keineAhnungWas.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Question>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        comepositeDisposable.add(d);

                    }

                    @Override
                    public void onSuccess(Question question) {
                        if (question == null) {
                            Intent i = new Intent(SecondActivity.this,FinalActivity.class);
                            startActivity(i);
                        }
                        else {
                            receivedQuestion(question);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        Toast info = Toast.makeText(getApplicationContext(),"Fehler bei getQuestion",Toast.LENGTH_SHORT);
                        info.show();
                    }
                });
    }

    protected void receivedQuestion(Question question) {
        Question q = question;
        switch (q.getQuestionType()) {
            case DATE_QUESTION:
                numberofAnswers = 1;
                break;
            case CHOICE_QUESTION:
                numberofAnswers = q.getAnswers().size();
                break;
            default: Toast info = Toast.makeText(getApplicationContext(),"Fehler",Toast.LENGTH_SHORT);
                info.show();
        }
        Button b;
        switch (numberofAnswers) {
            case 1:
                setContentView(R.layout.datechoice);
                b = findViewById(R.id.datebutton);
                TextView tv = findViewById(R.id.textViewDate);
                tv.setText(q.getQuestionText());
                EditText date = findViewById(R.id.dateEdit);
                b.setOnClickListener(new Class2());
                break;

            case 2:
                setContentView(R.layout.twochoices);
                b = findViewById(R.id.submitButtontwo);
                TextView tv2 = findViewById(R.id.textViewtwo);
                tv2.setText(q.getQuestionText());
                RadioGroup rg = findViewById(R.id.grouptwo);
                RadioButton r21 = findViewById(R.id.buttontwo1);
                RadioButton r22 = findViewById(R.id.buttontwo2);
                r21.setText(q.getAnswers().get(0).getAnswertText());
                r22.setText(q.getAnswers().get(1).getAnswertText());
                b.setOnClickListener(new Class2());
                break;
            case 3:
                setContentView(R.layout.threechoices);
                b = findViewById(R.id.submitbuttonthree);
                TextView tv3 = findViewById(R.id.textviewthree);
                tv3.setText(q.getQuestionText());
                RadioGroup rg3 = findViewById(R.id.radioGroupThree);
                RadioButton r31 = findViewById(R.id.buttonthree1);
                RadioButton r32 = findViewById(R.id.buttonthree2);
                RadioButton r33 = findViewById(R.id.buttonthree3);
                r31.setText(q.getAnswers().get(0).getAnswertText());
                r32.setText(q.getAnswers().get(1).getAnswertText());
                r33.setText(q.getAnswers().get(2).getAnswertText());
                b.setOnClickListener(new Class2());
                break;
            case 4:
                setContentView(R.layout.fourchoices);
                b = findViewById(R.id.submitbuttonfour);
                TextView tv4 = findViewById(R.id.textViewFour);
                tv4.setText(q.getQuestionText());
                RadioGroup rg4 = findViewById(R.id.radioGroupfour);
                RadioButton r41 = findViewById(R.id.buttonfour1);
                RadioButton r42 = findViewById(R.id.buttonfour2);
                RadioButton r43 = findViewById(R.id.buttonfour3);
                RadioButton r44 = findViewById(R.id.buttonfour4);
                r41.setText(q.getAnswers().get(0).getAnswertText());
                r42.setText(q.getAnswers().get(1).getAnswertText());
                r43.setText(q.getAnswers().get(2).getAnswertText());
                r44.setText(q.getAnswers().get(3).getAnswertText());
                b.setOnClickListener(new Class2());
                break;
            case 5:
                setContentView(R.layout.fivechoices);
                b = findViewById(R.id.submitbuttonfive);
                TextView tv5 = findViewById(R.id.textViewFive);
                tv5.setText(q.getQuestionText());
                RadioGroup rg5 = findViewById(R.id.radioGroupfive);
                RadioButton r51 = findViewById(R.id.radiobuttonfive1);
                RadioButton r52 = findViewById(R.id.radiobuttonfive2);
                RadioButton r53 = findViewById(R.id.radiobuttonfive3);
                RadioButton r54 = findViewById(R.id.radiobuttonfive4);
                RadioButton r55 = findViewById(R.id.radiobuttonfive5);
                r51.setText(q.getAnswers().get(0).getAnswertText());
                r52.setText(q.getAnswers().get(1).getAnswertText());
                r53.setText(q.getAnswers().get(2).getAnswertText());
                r54.setText(q.getAnswers().get(3).getAnswertText());
                r55.setText(q.getAnswers().get(4).getAnswertText());
                b.setOnClickListener(new Class2());
                break;
            case 6:
                setContentView(R.layout.sixchoices);
                b = findViewById(R.id.submitbuttonsix);
                TextView tv6 = findViewById(R.id.textViewSix);
                tv6.setText(q.getQuestionText());
                RadioGroup rg6 = findViewById(R.id.RadioGroupSix);
                RadioButton r61 = findViewById(R.id.buttonsix1);
                RadioButton r62 = findViewById(R.id.buttonsix2);
                RadioButton r63 = findViewById(R.id.buttonsix3);
                RadioButton r64 = findViewById(R.id.buttonsix4);
                RadioButton r65 = findViewById(R.id.buttonsix5);
                RadioButton r66 = findViewById(R.id.buttonsix6);
                r61.setText(q.getAnswers().get(0).getAnswertText());
                r62.setText(q.getAnswers().get(1).getAnswertText());
                r63.setText(q.getAnswers().get(2).getAnswertText());
                r64.setText(q.getAnswers().get(3).getAnswertText());
                r65.setText(q.getAnswers().get(4).getAnswertText());
                r66.setText(q.getAnswers().get(5).getAnswertText());
                b.setOnClickListener(new Class2());
                break;
            case 7:
                setContentView(R.layout.sevenchoices);
                b = findViewById(R.id.submitbuttonseven);
                TextView tv7 = findViewById(R.id.textViewSeven);
                tv7.setText(q.getQuestionText());
                RadioGroup rg7 = findViewById(R.id.radioGroupSeven);
                RadioButton r71 = findViewById(R.id.radioButtonseven1);
                RadioButton r72 = findViewById(R.id.radioButtonseven2);
                RadioButton r73 = findViewById(R.id.radioButtonseven3);
                RadioButton r74 = findViewById(R.id.radioButtonseven4);
                RadioButton r75 = findViewById(R.id.radioButtonseven5);
                RadioButton r76 = findViewById(R.id.radioButtonseven6);
                RadioButton r77 = findViewById(R.id.radioButtonseven7);
                r71.setText(q.getAnswers().get(0).getAnswertText());
                r72.setText(q.getAnswers().get(1).getAnswertText());
                r73.setText(q.getAnswers().get(2).getAnswertText());
                r74.setText(q.getAnswers().get(3).getAnswertText());
                r75.setText(q.getAnswers().get(4).getAnswertText());
                r76.setText(q.getAnswers().get(5).getAnswertText());
                r77.setText(q.getAnswers().get(6).getAnswertText());
                b.setOnClickListener(new Class2());
                break;
            case 8:
                setContentView(R.layout.eightchoices);
                b = findViewById(R.id.submitbutton8);
                TextView tv8 = findViewById(R.id.textViewSeven);
                tv8.setText(q.getQuestionText());
                RadioGroup rg8 = findViewById(R.id.radioGroupeight);
                RadioButton r81 = findViewById(R.id.buttoneight1);
                RadioButton r82 = findViewById(R.id.buttoneight2);
                RadioButton r83 = findViewById(R.id.buttoneight3);
                RadioButton r84 = findViewById(R.id.buttoneight4);
                RadioButton r85 = findViewById(R.id.buttoneight5);
                RadioButton r86 = findViewById(R.id.buttoneight6);
                RadioButton r87 = findViewById(R.id.buttoneight7);
                RadioButton r88 = findViewById(R.id.buttoneight8);
                r81.setText(q.getAnswers().get(0).getAnswertText());
                r82.setText(q.getAnswers().get(1).getAnswertText());
                r83.setText(q.getAnswers().get(2).getAnswertText());
                r84.setText(q.getAnswers().get(3).getAnswertText());
                r85.setText(q.getAnswers().get(4).getAnswertText());
                r86.setText(q.getAnswers().get(5).getAnswertText());
                r87.setText(q.getAnswers().get(6).getAnswertText());
                r88.setText(q.getAnswers().get(7).getAnswertText());
                b.setOnClickListener(new Class2());
                break;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        getQuestion();
        // TODO: Hier vielleicht ein "bitte warten" oder lade-symbol / animation einbinden
    }

    class Class2 implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (numberofAnswers) {
                case 1:
                    datechoice();
                    break;
                case 2:
                    twochoices();
                    break;
                case 3:
                    threechoices();
                    break;
                case 4:
                    fourchoices();
                    break;
                case 5:
                    fivechoices();
                    break;
                case 6:
                    sixchoices();
                    break;
                case 7:
                    sevenchoices();
                    break;
                case 8:
                    eightchoices();
                    break;
            }
        }

        public void datechoice() {
            EditText date = findViewById(R.id.dateEdit);
            Date d = (Date) date.getText();
            DateAnswer dateAnswer = new DateAnswer();
            dateAnswer.setAnswerDate(d);
            giveAnswer(dateAnswer);
        }

        public void twochoices() {
            RadioButton r21 = findViewById(R.id.buttontwo1);
            RadioButton r22 = findViewById(R.id.buttontwo2);
            ChoiceAnswer a;
            if(r21.isChecked()){
                a = nextQuestion.getAnswers().get(0);
            }
            else{
                a = nextQuestion.getAnswers().get(1);
            }
            giveAnswer(a);
        }

        public void threechoices() {
            RadioButton r31 = findViewById(R.id.buttonthree1);
            RadioButton r32 = findViewById(R.id.buttonthree2);
            RadioButton r33 = findViewById(R.id.buttonthree3);
            ChoiceAnswer a;
            if(r31.isChecked()){
             a = nextQuestion.getAnswers().get(0);
            }
            else if(r32.isChecked()){
             a = nextQuestion.getAnswers().get(1);
            }
            else {
             a = nextQuestion.getAnswers().get(2);
            }
            giveAnswer(a);
        }

        public void fourchoices() {
            RadioButton r41 = findViewById(R.id.buttonfour1);
            RadioButton r42 = findViewById(R.id.buttonfour2);
            RadioButton r43 = findViewById(R.id.buttonfour3);
            RadioButton r44 = findViewById(R.id.buttonfour4);
            ChoiceAnswer a;
            if(r41.isChecked()){
                a = nextQuestion.getAnswers().get(0);
            }
            else if(r42.isChecked()){
                a=nextQuestion.getAnswers().get(1);
            }
            else if(r43.isChecked()){
                a = nextQuestion.getAnswers().get(2);
            }
            else{
                a = nextQuestion.getAnswers().get(3);
            }
            giveAnswer(a);
        }

        public void fivechoices() {
            RadioButton r51 = findViewById(R.id.radiobuttonfive1);
            RadioButton r52 = findViewById(R.id.radiobuttonfive2);
            RadioButton r53 = findViewById(R.id.radiobuttonfive3);
            RadioButton r54 = findViewById(R.id.radiobuttonfive4);
            ChoiceAnswer a;
            if(r51.isChecked()){
                a = nextQuestion.getAnswers().get(0);
            }
            else if(r52.isChecked()){
                a=nextQuestion.getAnswers().get(1);
            }
            else if(r53.isChecked()){
                a = nextQuestion.getAnswers().get(2);
            }
            else if(r54.isChecked()){
                a = nextQuestion.getAnswers().get(3);
            }
            else{
                a = nextQuestion.getAnswers().get(4);
            }
            giveAnswer(a);
        }

        public void sixchoices() {
            RadioButton r61 = findViewById(R.id.buttonsix1);
            RadioButton r62 = findViewById(R.id.buttonsix2);
            RadioButton r63 = findViewById(R.id.buttonsix3);
            RadioButton r64 = findViewById(R.id.buttonsix4);
            RadioButton r65 = findViewById(R.id.buttonsix5);
            ChoiceAnswer a;
            if(r61.isChecked()){
                a = nextQuestion.getAnswers().get(0);
            }
            else if(r62.isChecked()){
                a=nextQuestion.getAnswers().get(1);
            }
            else if(r63.isChecked()){
                a = nextQuestion.getAnswers().get(2);
            }
            else if(r64.isChecked()){
                a = nextQuestion.getAnswers().get(3);
            }
            else if(r65.isChecked()){
                a = nextQuestion.getAnswers().get(4);
            }
            else{
                a = nextQuestion.getAnswers().get(5);
            }
            giveAnswer(a);
        }

        public void sevenchoices() {
            RadioButton r71 = findViewById(R.id.radioButtonseven1);
            RadioButton r72 = findViewById(R.id.radioButtonseven2);
            RadioButton r73 = findViewById(R.id.radioButtonseven3);
            RadioButton r74 = findViewById(R.id.radioButtonseven4);
            RadioButton r75 = findViewById(R.id.radioButtonseven5);
            RadioButton r76 = findViewById(R.id.radioButtonseven6);
            ChoiceAnswer a;
            if(r71.isChecked()){
                a = nextQuestion.getAnswers().get(0);
            }
            else if(r72.isChecked()){
                a=nextQuestion.getAnswers().get(1);
            }
            else if(r73.isChecked()){
                a = nextQuestion.getAnswers().get(2);
            }
            else if(r74.isChecked()){
                a = nextQuestion.getAnswers().get(3);
            }
            else if(r75.isChecked()){
                a = nextQuestion.getAnswers().get(4);
            }
            else if(r76.isChecked()){
                a = nextQuestion.getAnswers().get(5);
            }
            else{
                a = nextQuestion.getAnswers().get(6);
            }
            giveAnswer(a);
        }

        public void eightchoices() {
            RadioButton r81 = findViewById(R.id.buttoneight1);
            RadioButton r82 = findViewById(R.id.buttoneight2);
            RadioButton r83 = findViewById(R.id.buttoneight3);
            RadioButton r84 = findViewById(R.id.buttoneight4);
            RadioButton r85 = findViewById(R.id.buttoneight5);
            RadioButton r86 = findViewById(R.id.buttoneight6);
            RadioButton r87 = findViewById(R.id.buttoneight7);
            RadioButton r88 = findViewById(R.id.buttoneight8);
            ChoiceAnswer a;
            if(r81.isChecked()){
                a = nextQuestion.getAnswers().get(0);
            }
            else if(r82.isChecked()){
                a = nextQuestion.getAnswers().get(1);
            }
            else if(r83.isChecked()){
                a = nextQuestion.getAnswers().get(2);
            }
            else if(r84.isChecked()){
                a = nextQuestion.getAnswers().get(3);
            }
            else if(r85.isChecked()){
                a = nextQuestion.getAnswers().get(4);
            }
            else if(r86.isChecked()){
                a = nextQuestion.getAnswers().get(5);
            }
            else if(r87.isChecked()){
                a = nextQuestion.getAnswers().get(6);
            }
            else{
                a = nextQuestion.getAnswers().get(7);
            }
            giveAnswer(a);
        }
        public void giveAnswer(Answer a){
            if(a instanceof DateAnswer){
                DateAnswer answer = (DateAnswer) a;
                nextQuestion.setDateAnswer(answer);
            } else {
                ChoiceAnswer answer = (ChoiceAnswer) a;
                nextQuestion.setAnswer(answer);
            }
                Single<Void> answerJob = Communication.getInstance().answerQuestion(nextQuestion);
                final CompositeDisposable comepositeDisposable = new CompositeDisposable();
                answerJob.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<Void>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                comepositeDisposable.add(d);

                            }

                            @Override
                            public void onSuccess(Void voidy) {
                                Intent i = new Intent(SecondActivity.this,SecondActivity.class);
                                startActivity(i);
                            }
                            @Override
                            public void onError(Throwable e) {
                                Toast info = Toast.makeText(getApplicationContext(),"Fehler bei answerQuestion",Toast.LENGTH_SHORT);
                                info.show();
                            }
                        });
            }
        }
    }