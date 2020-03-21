package com.example.selbsttest_corona;

import androidx.appcompat.app.AppCompatActivity;

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


    }

    public class TestClass implements View.OnClickListener {
        long l;


        public void doSelftest(final Long id2){
            final long l = id2;
            final Single<Long> selftestID = Communication.getInstance().createNewSelftest();
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
                            Communication.getInstance().setSelftestId(id);
                            Communication.getInstance().setSubjectId(id2);
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


}