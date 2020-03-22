package com.example.selbsttest_corona;


import com.wirvsvirus.selftest.api.Contact;
import com.wirvsvirus.selftest.api.SelftestSubject;
import com.wirvsvirus.selftest.api.selftest.Question;
import com.wirvsvirus.selftest.api.selftest.Selftest;

import java.util.List;

import io.reactivex.Single;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiSelftest {
    @GET("/api/subject/")
    Single<Selftest> subjectGet(@Query("id") long id);

    @POST("/api/subject/")
    Single<Long> subjectOnServerCreateNew(@Body SelftestSubject selftestSubject);

    @PUT("/api/subject/")
    Single<ResponseBody> subjectOnServerOverwriteId(@Query("id") long id,
                                                @Body SelftestSubject selftestSubject);

    @DELETE("/api/subject/")
    Single<ResponseBody> subjectOnServerDeleteId(@Query("id") long id);



    @GET("/api/subject/{id}/selftest/")
    Single<Selftest> selftestGetFromId(@Path("id") long id, @Query("selftest-id") long selftestId);

    @GET("/api/subject/{id}/selftest/")
    Single<List<Selftest>> selftestGetAll(@Path("id") long id);

    @POST("/api/subject/{id}/selftest/")
    Single<Long> selftestCreateNew(@Path("id") long id);



    @GET("/api/subject/{id}/selftest/{selftest-id}/question")
    Single<Question> questionGetNext(@Path("id") long id, @Path("selftest-id") long selftestId);

    @POST("/api/subject/{id}/selftest/{selftest-id}/question")
    Single<ResponseBody> questionAnswer(@Path("id") long id, @Path("selftest-id") long selftestId,
                                        @Body Question answeredQuestion);



    @POST("/api/subject/{id}/contact")
    Single<ResponseBody> contactSend(@Path("id") long id,
                                     @Body Contact contact);
}