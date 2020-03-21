package com.example.selbsttest_corona;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button button;
        Retrofit retrofit;
        TextView textView = findViewById(R.id.textViewMain);
        EditText postalNumber = findViewById(R.id.postalNumber);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // So koennen wir einen Button finden und Click abfangen
        button = findViewById(R.id.testButton);
        button.setOnClickListener(new TestClass());

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
            EditText postalNumber = findViewById(R.id.postalNumber);
        TextView textView = findViewById(R.id.textViewMain);
        postalNumber.setPaintFlags(0);
        textView.setText(postalNumber.getEditableText().toString());
            Button button = findViewById( R.id.testButton);
            button.setEnabled(false);
        }
    }
}