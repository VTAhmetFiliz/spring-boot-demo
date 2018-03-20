package com.example.cacooApi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DiagramsResponse {
    private DiagramsResult[] result;
    private int count;

    public DiagramsResponse() {
    }

    public DiagramsResult[] getResult() {
        return result;
    }

    public void setResult(DiagramsResult[] diagramsResult) {
        this.result = diagramsResult;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}

