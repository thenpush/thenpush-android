package me.thenpush.rest;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pappacena on 12/04/16.
 */
public class RestApi {
    public static final String BASE_URL = "https://thenpush.me/";
    private Context context;
    private static RestApi instance;
    private Retrofit retrofit;

    private RestApi(Context context) {
        this.context = context;

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static RestApi getInstance(Context context) {
        if(RestApi.instance == null) {
            RestApi.instance = new RestApi(context);
        }
        return RestApi.instance;
    }

    public Retrofit getRetrofit() {
        return this.retrofit;
    }
}
