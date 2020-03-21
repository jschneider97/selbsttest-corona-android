package com.example.selbsttest_corona;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button button;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eightchoices);

        // So koennen wir einen Button finden und Click abfangen
  //      button = findViewById(R.id.testButton);
        //     button.setOnClickListener(new TestClass());

        // API fuer REST fuer die Interaktion mit dem server
//        retrofit = new Retrofit.Builder()
//                .baseUrl("TODO: API's base URL")
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();
    }

    // Handler class fuer den Button
    public class TestClass implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            button.setEnabled(false);
        }
    }
}