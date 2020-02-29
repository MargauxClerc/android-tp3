package fr.clerc.myapplication;

import fr.clerc.myapplication.model.FeatureCollection;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MetromobiliteService {

    @GET("/api/findType/json")
    Call<FeatureCollection> getAgencyList(@Query("types") String type);

    @GET("/api/findType/json")
    Call<FeatureCollection> getAgency(@Query("types") String type, @Query("query") String name);
}

