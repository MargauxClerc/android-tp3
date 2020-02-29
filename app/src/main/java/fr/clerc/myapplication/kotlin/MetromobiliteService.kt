package fr.clerc.myapplication.kotlin

import fr.clerc.myapplication.kotlin.model.FeatureCollection
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MetromobiliteService {

    @GET("/api/findType/json")
    fun getAgencyList(@Query("types") type: String): Call<FeatureCollection?>

    @GET("/api/findType/json")
    fun getAgency(@Query("types") type: String, @Query("query") name: String): Call<FeatureCollection?>
}