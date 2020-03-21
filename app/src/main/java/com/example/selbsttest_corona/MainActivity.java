package com.example.selbsttest_corona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wirvsvirus.selftest.api.SelftestSubject;
import com.wirvsvirus.selftest.api.selftest.Question;

import java.io.Serializable;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button;
        Retrofit retrofit;
        TextView textView = findViewById(R.id.textViewMain);
        EditText postalNumber = findViewById(R.id.postalNumber);
        setContentView(R.layout.activity_main);

        // So koennen wir einen Button finden und Click abfangen
        button = findViewById(R.id.testButton);
        button.setOnClickListener(new TestClass());

        // pruefe, ob subject bereits erstellt wurde auf diesem Geraet
        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.selbsttest_corona", MODE_PRIVATE);
        long subjectId = sharedPreferences.getLong("com.example.selbsttest_corona.subjectId", 0);
        if (subjectId == 0) {
            // Subjekt id existiert nicht,
            // TODO: Postleihzahl usw. abfragen
        } else {
            // Subjekt Id existiert
            Communication.getInstance().setSubjectId(subjectId);

            // TODO: Zu einer Uebersicht springen
            //  - Neuen test
            //  - Generelle Informationen?
            //  - ...
        }
    }

    public class TestClass implements View.OnClickListener {
        long l;


        public void doSelftest(final Long id2){
            final long l = id2;
            Single<Long> selftestID = Communication.getInstance().createNewSelftest(id2);
            final CompositeDisposable comepositeDisposable = new CompositeDisposable();
            selftestID.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<Long>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            comepositeDisposable.add(d);

                        }

                        @Override
                        public void onSuccess(Long id) {
                            startTest(l,id);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast info = Toast.makeText(getApplicationContext(),"Fehler",Toast.LENGTH_SHORT);
                            info.show();
                        }
                    });
        }
        @Override
        public void onClick(View v) {

            Single<Long> selftestID = Communication.getInstance().createSubjectId(new SelftestSubject());
            final CompositeDisposable comepositeDisposable = new CompositeDisposable();
            selftestID.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<Long>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            comepositeDisposable.add(d);

                        }

                        @Override
                        public void onSuccess(Long id) {
                        doSelftest(id);

                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast info = Toast.makeText(getApplicationContext(),"Fehler",Toast.LENGTH_SHORT);
                            info.show();
                        }
                    });
            EditText postalNumber = findViewById(R.id.postalNumber);
            TextView textView = findViewById(R.id.textViewMain);
            postalNumber.setPaintFlags(0);
            textView.setText(postalNumber.getEditableText().toString());
            Button button = findViewById(R.id.testButton);
            button.setEnabled(false);
        }
    public void startTest(final Long subjectID, final Long SelftestID)	{
            Single<Question> keineAhnungWas = Communication.getInstance().getQuestion(subjectID,SelftestID);
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
                        Communication.getInstance().setSelftestId(SelftestID);
                        Communication.getInstance().setSubjectId(subjectID);
                        Intent i = new Intent(MainActivity.this,SecondActivity.class);
                        startActivity(i);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast info = Toast.makeText(getApplicationContext(),"Fehler",Toast.LENGTH_SHORT);
                        info.show();
                    }
                });
    }
    }

}
