package com.example.cacooApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Configuration
@ComponentScan(basePackages = {"com.example.*"})
@PropertySource("classpath:application-prod.properties")
public class ApiUrl {

    private static String url;

    private static String diagrams;

    private static String account;

    private static String folder;

    private static String single_diagram;

    private static String apiKey;

    @Autowired
    private Environment env;

    public ApiUrl() {
    }

    @Bean
    public String setFields() {
        url = env.getProperty("url");
        diagrams = env.getProperty("url.diagrams");
        account = env.getProperty("url.account");
        folder = env.getProperty("url.folder");
        single_diagram = env.getProperty("url.single.diagram");
        apiKey = env.getProperty("apiKey");
        return url;
    }

    public String getDiagramsUrl() {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .fromHttpUrl(url)
                .path(diagrams)
                .path(apiKey)
                .build();
        return uriComponents.toUriString();
    }

    public String getDiagramsUrlWithParamsUrl(MultiValueMap<String, String> params) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url + diagrams + apiKey);
        builder.queryParams(params);
        return builder.toUriString();
    }

    public String getDiagramByDiagramIdUrl(String diagramId) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .fromHttpUrl(url)
                .path(single_diagram)
                .path(diagramId)
                .path(".json")
                .path(apiKey)
                .build();
        return uriComponents.toUriString();
    }

    public String getDiagramToFolderUrl(String diagramId) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .fromHttpUrl(url)
                .path(single_diagram)
                .path(diagramId)
                .path("/copy.json")
                .path(apiKey)
                .build();
        return uriComponents.toUriString();
    }

    public String getDeleteDiagramUrl(String diagramId) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .fromHttpUrl(url)
                .path(single_diagram)
                .path(diagramId)
                .path("/delete.json")
                .path(apiKey)
                .build();
        return uriComponents.toUriString();
    }

    public String getFoldersUrl() {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .fromHttpUrl(url)
                .path(folder)
                .path(apiKey)
                .build();
        return uriComponents.toUriString();
    }

    public String getAccountUrl() {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .fromHttpUrl(url)
                .path(account)
                .path(apiKey)
                .build();
        return uriComponents.toUriString();
    }

    public String getApiKey() {
        return apiKey;
    }
}
