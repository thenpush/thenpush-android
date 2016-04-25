package me.thenpush.rest;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import me.thenpush.BuildConfig;
import me.thenpush.ConfigFetcher;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pappacena on 12/04/16.
 */
public class RestApi {
    public static final String BASE_URL = "https://thenpush.me/api/v1/";
    private Context context;
    private static RestApi instance;
    private Retrofit retrofit;

    private RestApi(final Context context) {
        this.context = context;

        // My machine :-)
        String url = BASE_URL;
        try {
            if((boolean) Class.forName(context.getPackageName() + ".BuildConfig").getField("DEBUG").get(null)) {
                url = "http://192.168.1.46:8000/";
            }
        } catch (ClassNotFoundException e) {
        } catch (NoSuchFieldException e) {
        } catch (IllegalAccessException e) {
        }

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        String token = ConfigFetcher.getToken(context);
                        Request request = chain.request().newBuilder()
                                .addHeader("Authorization", token).build();
                        return chain.proceed(request);
                    }
                }).build();

        this.retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .build();
    }

    public static RestApi getInstance(Context context) {
        if (RestApi.instance == null) {
            RestApi.instance = new RestApi(context);
        }
        return RestApi.instance;
    }

    public Retrofit getRetrofit() {
        return this.retrofit;
    }
}
