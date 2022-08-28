package com.vimalrajravi.rwrapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CustumerApi {
    @GET("posts")
    Call<List<CustumerDetails>>
    getPost();
}
