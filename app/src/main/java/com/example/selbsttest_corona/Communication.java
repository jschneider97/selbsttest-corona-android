package com.example.selbsttest_corona;

import android.content.SharedPreferences;

import com.wirvsvirus.selftest.api.SelftestSubject;
import com.wirvsvirus.selftest.api.selftest.Answer;
import com.wirvsvirus.selftest.api.selftest.Question;
import com.wirvsvirus.selftest.api.selftest.Selftest;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Communication {
    // TODO: Waere cool wenn wir die SubjectID auch zwischen den App-Aufrufen speichern koennen
    // waere besser als jedes mal einen neuen Eintrag auf dem Server zu erstellen
    private Retrofit retrofit;
    private ApiSelftest api;

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    public long getSelftestId() {
        return selftestId;
    }

    public void setSelftestId(long selftestId) {
        this.selftestId = selftestId;
    }

    private long subjectId;
    private long selftestId;

    private static Communication instance;
    public static Communication getInstance() {
        if (instance == null) {
            instance = new Communication();
        }
        return instance;
    }

    public Communication() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://82.165.206.240:8088/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        this.api = retrofit.create(ApiSelftest.class);

        // TODO: Wir laeuft die Kommunikation mit dem Server hier ab?
        // Muessen wir erst pruefen, ob die deviceId in der Datenbank hinterlegt ist,
        // sollen wir hier pruefen, ob ein Test angefangen, aber nicht beendet wurde,
        // ...
    }

    // TODO: Alternativ kann man hier auch die daten uebergeben, um ein selftestSubject zu erstellen,
    // sprich
    //  String zipCode;
    //  String city;
    //  AccessDevice accessDevice;
    //  String deviceID;
    public Single<Long> createSubjectId(SelftestSubject selftestSubject) {
        // TODO: Herausfinden in wie fern das compositeDisposable im Zusammenhang steht und
        // wie die Leute der GUI das nutzen sollen / koennen

        Single<Long> subjectIdJob = api.subjectOnServerCreateNew(selftestSubject);
        return subjectIdJob;
    }

    public Single<Long> createNewSelftest() {
        Single<Long> selftestIdJob = api.selftestCreateNew(this.getSubjectId());
        return selftestIdJob;
    }

    public Single<Selftest> getSelftestFromId() {
        Single<Selftest> selftestJob = api.selftestGetFromId(this.getSubjectId(), this.getSelftestId());
        return selftestJob;
    }

    public Single<Question> getQuestion() {
        Single<Question> questionJob = api.questionGetNext(this.getSubjectId(), this.getSelftestId());
        return questionJob;
    }

    public Single<Void> answerQuestion(Question answeredQuestion) {
        Single<Void> answerQuestionJob = api.questionAnswer(this.getSubjectId(), this.getSelftestId(), answeredQuestion);
        return answerQuestionJob;
    }
}
