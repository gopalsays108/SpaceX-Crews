package com.gopal.spacexcrews.network;

import com.google.gson.annotations.SerializedName;
import com.gopal.spacexcrews.modal.CrewsModal;
import com.gopal.spacexcrews.modal.ReponseModal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;

public interface Api {

    @POST("v4/crew/query/")
    @SerializedName( "docs" )
    Call<ReponseModal> getAllCrews();
}
