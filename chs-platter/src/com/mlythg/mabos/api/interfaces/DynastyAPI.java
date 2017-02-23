package com.mlythg.mabos.api.interfaces;

import com.mlythg.mabos.api.models.Dynasty;

import retrofit.http.GET;
import retrofit.http.Query;

public interface DynastyAPI {
    @GET("/computeDynasty")
    Dynasty fetchDynastyScore(
            @Query("team") String team
    );
}