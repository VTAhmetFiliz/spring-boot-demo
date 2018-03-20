package com.example.cacooApi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FolderResponse {
    private FolderResult [] result;

    public FolderResponse(){
    }

    public FolderResult[] getResult() {
        return result;
    }

    public void setResult(FolderResult[] result) {
        this.result = result;
    }
}
