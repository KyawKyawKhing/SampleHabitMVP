package com.aceplus.padc_poc_one.network;

import com.aceplus.padc_poc_one.network.reponses.GetCategoriesProgramsResponse;
import com.aceplus.padc_poc_one.network.reponses.GetCurrentProgramResponse;
import com.aceplus.padc_poc_one.network.reponses.GetTopicsResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by kkk on 5/26/2018.
 */

public interface MeditateAPI {

    @FormUrlEncoded
    @POST("getCurrentProgram.php")
    Observable<GetCurrentProgramResponse> getCurrentProgram(@Field("page") int page, @Field("access_token") String accessToken);

    @FormUrlEncoded
    @POST("getCategoriesPrograms.php")
    Observable<GetCategoriesProgramsResponse> getCategoriesPrograms(@Field("page") int page, @Field("access_token") String accessToken);

    @FormUrlEncoded
    @POST("getTopics.php")
    Observable<GetTopicsResponse> getTopics(@Field("page") int page, @Field("access_token") String accessToken);

}
