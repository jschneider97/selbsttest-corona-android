package com.example.selbsttest_corona;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wirvsvirus.selftest.api.selftest.Question;

public class SecondActivity extends AppCompatActivity {
    int numberofAnswers;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Question q = Communication.getInstance().getQuestion();
        switch (q.getQuestionType()) {
            case DATE_QUESTION:
                numberofAnswers = 1;
                break;
            case CHOICE_QUESTION:
                numberofAnswers = q.getAnswers().size();
                break;
        }
        Button b;
            switch (numberofAnswers){
                case 1: setContentView(R.layout.datechoice);
                b = findViewById(R.id.datebutton);
                break;
                case 2: setContentView(R.layout.twochoices);
                    b = findViewById(R.id.submitButtontwo);
                break;
                case 3: setContentView(R.layout.threechoices);
                    b = findViewById(R.id.submitbuttonthree);
                    break;
                case 4: setContentView(R.layout.fourchoices);
                    b = findViewById(R.id.submitbuttonfour);
                break;
                case 5: setContentView(R.layout.fivechoices);
                    b = findViewById(R.id.submitbuttonfive);
                break;
                case 6: setContentView(R.layout.sixchoices);
                    b = findViewById(R.id.submitbuttonsix);
                    break;
                case 7: setContentView(R.layout.sevenchoices);
                    b = findViewById(R.id.submitbuttonseven);
                break;
                case 8: setContentView(R.layout.eightchoices);
                    b = findViewById(R.id.submitbutton8);
                break;
                b.setOnClickListener(new Class2);
        }
        }
        class Class2 implements View.OnClickListener{

            @Override
            public void onClick(View v) {
                switch (numberofAnswers) {
                    case 1: datechoice(); break;
                    case 2: twochoices(); break;
                    case 3: threechoices(); break;
                    case 4: fourchoices(); break;
                    case 5: fivechoices(); break;
                    case 6: sixchoices(); break;
                    case 7: sevenchoices(); break;
                    case 8: eightchoices(); break;
                }
            }
            public void datechoice(){
                TextView tv = findViewById()
            }
            public void twochoices(){

            }
            public void threechoices(){

            }
            public void fourchoices(){

            }
            public void fivechoices(){

            }
            public void sixchoices(){

            }
            public void sevenchoices(){

            }
            public void eightchoices(){

            }
        }
    }
