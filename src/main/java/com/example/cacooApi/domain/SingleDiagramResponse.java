package com.example.cacooApi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SingleDiagramResponse extends DiagramsResult{
    private Sheet[] sheets;
    private Comment[] comments;

    public SingleDiagramResponse() {
    }

    public Sheet[] getSheets() {
        return sheets;
    }

    public void setSheets(Sheet[] sheets) {
        this.sheets = sheets;
    }

    public Comment[] getComments() {
        return comments;
    }

    public void setComments(Comment[] comments) {
        this.comments = comments;
    }
}
