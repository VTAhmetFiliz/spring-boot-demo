package com.example.cacooApi.controller;

import com.example.cacooApi.domain.Account;
import com.example.cacooApi.domain.DiagramsResponse;
import com.example.cacooApi.domain.FolderResponse;
import com.example.cacooApi.domain.SingleDiagramResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
public class CacooApiServices {
    private final RestTemplate restTemplate;
    private ApiUrl apiUrl = new ApiUrl();

    public CacooApiServices(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
        List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();
        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                ((MappingJackson2HttpMessageConverter) converter).setSupportedMediaTypes(Arrays.asList(MediaType.parseMediaType("text/javascript; charset=utf-8")));
            }
        }
    }

    public DiagramsResponse getDiagrams() {
        ResponseEntity<DiagramsResponse> diagramsResponse = restTemplate.getForEntity(
                apiUrl.getDiagramsUrl(), DiagramsResponse.class);
        return diagramsResponse.getBody();
    }

    public DiagramsResponse getDiagramsWithParam(MultiValueMap<String, String> params) {
        ResponseEntity<DiagramsResponse> diagramsResponse = restTemplate.exchange(
                apiUrl.getDiagramsUrlWithParamsUrl(params), HttpMethod.GET, getHttpEntity(), DiagramsResponse.class);
        return diagramsResponse.getBody();
    }

    public Account getAccount() {
        ResponseEntity<Account> accountResponse = restTemplate.getForEntity(
                apiUrl.getAccountUrl(), Account.class);
        return accountResponse.getBody();
    }

    public FolderResponse getFolders() {
        ResponseEntity<FolderResponse> folderResponse = restTemplate.getForEntity(
                apiUrl.getFoldersUrl(), FolderResponse.class);
        return folderResponse.getBody();
    }

    public SingleDiagramResponse getDiagramByDiagramId(String diagramId) {
        ResponseEntity<SingleDiagramResponse> diagramResponseResponseEntity = restTemplate.getForEntity(
                apiUrl.getDiagramByDiagramIdUrl(diagramId), SingleDiagramResponse.class);
        return diagramResponseResponseEntity.getBody();
    }

    public SingleDiagramResponse copyDiagramToFolder(MultiValueMap<String, String> params, String diagramId) {
        ResponseEntity<SingleDiagramResponse> diagramResponseResponseEntity = restTemplate.postForEntity(
                apiUrl.getDiagramToFolderUrl(diagramId), getMultiValueMapHttpEntity(params), SingleDiagramResponse.class);
        return diagramResponseResponseEntity.getBody();
    }

    private HttpEntity<MultiValueMap<String, String>> getMultiValueMapHttpEntity(MultiValueMap<String, String> params) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return new HttpEntity<>(params, headers);
    }

    public void deleteDiagram(String diagramId) {
        restTemplate.exchange(apiUrl.getDeleteDiagramUrl(diagramId), HttpMethod.GET, getHttpEntity(), Object.class);
    }

    private HttpEntity getHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        return new HttpEntity(headers);
    }

    public String getApiKey(){
        return apiUrl.getApiKey();
    }

}
