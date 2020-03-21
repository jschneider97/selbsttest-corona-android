package com.example.selbsttest_corona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wirvsvirus.selftest.api.SelftestSubject;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button;
        setContentView(R.layout.activity_main);

        // So koennen wir einen Button finden und Click abfangen
        button = findViewById(R.id.testButton);
        button.setOnClickListener(new TestClass());

        // pruefe, ob subjectId bereits erstellt wurde auf diesem Geraet
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
        CompositeDisposable compositeDisposable;

        @Override
        public void onClick(View v) {
            compositeDisposable = new CompositeDisposable();

            Single<Long> subjectIdJob = Communication.getInstance().createSubjectId(new SelftestSubject());
            subjectIdJob.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<Long>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            compositeDisposable.add(d);
                        }

                        @Override
                        public void onSuccess(Long id) {
                            Communication.getInstance().setSubjectId(id.longValue());
                            doSelftest();
                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast info = Toast.makeText(getApplicationContext(), "Fehler", Toast.LENGTH_SHORT);
                            compositeDisposable.dispose();
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

        public void doSelftest() {
            Single<Long> selftestIdJob = Communication.getInstance().createNewSelftest();
            selftestIdJob.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<Long>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            compositeDisposable.add(d);
                        }

                        @Override
                        public void onSuccess(Long id) {
                            Communication.getInstance().setSelftestId(id.longValue());
                            Intent i = new Intent(MainActivity.this, SecondActivity.class);
                            compositeDisposable.dispose();
                            startActivity(i);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast info = Toast.makeText(getApplicationContext(), "Fehler", Toast.LENGTH_SHORT);
                            compositeDisposable.dispose();
                            info.show();
                        }
                    });
        }
    }
}