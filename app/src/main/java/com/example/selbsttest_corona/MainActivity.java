package com.example.selbsttest_corona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
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