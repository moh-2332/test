package com.simi.codestrokealert.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CasesResponse {

    @SerializedName("result")
    private List<Cases> results;

    public List<Cases> getResults() {
        return results;
    }

    public void setResults(List<Cases> results) {
        this.results = results;
    }


}
