package com.vimalrajravi.rwrapplication;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetRetrofit {
    private static Retrofit retrofit;
    private static final String BASE_URL  = "https://jsonplaceholder.typicode.com/";


    public static Retrofit retrofitClientInstance() {
        if (retrofit == null) {
            retrofit=new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;

    }
}
