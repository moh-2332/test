package com.simi.codestrokealert.model.rest;

import com.simi.codestrokealert.model.CaseAssessments;
import com.simi.codestrokealert.model.CaseAssessmentsResponse;
import com.simi.codestrokealert.model.CaseEdResponse;
import com.simi.codestrokealert.model.CaseEds;
import com.simi.codestrokealert.model.CaseHistories;
import com.simi.codestrokealert.model.CaseHistoriesResponse;
import com.simi.codestrokealert.model.CaseManagements;
import com.simi.codestrokealert.model.CaseManagmentsResponse;
import com.simi.codestrokealert.model.CaseRadiologies;
import com.simi.codestrokealert.model.CaseRadiologiesResponse;
import com.simi.codestrokealert.model.Cases;
import com.simi.codestrokealert.model.CasesResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RequestInterface {

    @POST("cases/")
  //  Call<Cases> addCase(@Body Cases cases, @Query("api_key") String apiKey);
    Call<Cases> addCase(@Body Cases cases);


    @PUT("case_histories/{id}/")
    Call<CaseHistories> updateCase(@Path("id") int case_id , @Body CaseHistories caseHistories);

    @PUT("case_assessments/{id}/")
    Call<CaseAssessments> sendCaseAssessments(@Path("id") int case_id, @Body CaseAssessments caseAssessments);

    @GET("cases/")
    Call<CasesResponse> getCases();

    @GET("case_eds/{id}/")
    Call<CaseEdResponse> getCaseEd(@Path("id") int case_id);

    @PUT("case_eds/{id}/")
    Call<CaseEds> updateCaseEd(@Path("id") int case_id, @Body CaseEds caseEds);

    @GET("cases/{id}/")
    Call<CasesResponse> getPatient(@Path("id") int case_id);

    @PUT("cases/{id}/")
    Call<Cases> updatePatientDetails(@Path("id") int case_id, @Body Cases cases);

    @GET("case_histories/{id}/")
    Call<CaseHistoriesResponse> getCaseHistories(@Path("id") int case_id);

    @GET("case_assessments/{id}/")
    Call<CaseAssessmentsResponse> getCaseAssessments(@Path("id") int case_id);

    @PUT("case_assessments/{id}/")
    Call<CaseAssessments> updateCaseAssessment(@Path("id") int case_id, @Body CaseAssessments caseAssessments);

    @GET("case_radiologies/{id}/")
    Call<CaseRadiologiesResponse> getCaseRadiologies(@Path("id") int case_id);

    @PUT("case_radiologies/{id}/")
    Call<CaseRadiologies> updateCaseRadiologies(@Path("id") int case_id, @Body CaseRadiologies caseRadiologies);

    @GET("case_managements/{id}/")
    Call<CaseManagmentsResponse> getCaseManagments(@Path("id") int case_id);

    @PUT("case_managements/{id}/")
    Call<CaseManagements> updateCaseManagements(@Path("id") int case_id, @Body CaseManagements caseManagements);





}
