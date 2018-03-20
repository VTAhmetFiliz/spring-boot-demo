package com.example.cacooApi.controller;

import com.example.cacooApi.domain.DiagramsResponse;
import com.example.cacooApi.domain.FolderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    private static final String FOLDER_ID = "folderId";
    private static final String DIAGRAM_ID = "diagramId";
    private static final String DIAGRAM = "diagram";
    private static final String DIAGRAMS = "diagrams";
    private static final String API_KEY = "apiKey";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String ACCOUNT = "account";
    private static final String FOLDERS = "folders";

    public CacooApiServices getCacooApiServices() {
        return cacooApiServices;
    }

    @Autowired
    public void setCacooApiServices(CacooApiServices cacooApiServices) {
        this.cacooApiServices = cacooApiServices;
    }

    private CacooApiServices cacooApiServices;

    @RequestMapping("/folder/{folderId}")
    public String folder(@PathVariable(FOLDER_ID) int folderId, ModelMap model) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        model.addAttribute(FOLDER_ID, folderId);
        map.add(FOLDER_ID, String.valueOf(folderId));
        DiagramsResponse diagramsResponse = cacooApiServices.getDiagramsWithParam(map);
        if (diagramsResponse.getCount() != 0) {
            model.addAttribute(DIAGRAMS, diagramsResponse.getResult());
        }
        return "folder";
    }

    @RequestMapping("/diagram/{diagramId}")
    public String diagram(@PathVariable(DIAGRAM_ID) String diagramId, ModelMap model) {
        model.addAttribute(DIAGRAM_ID, diagramId);
        model.addAttribute(DIAGRAM, cacooApiServices.getDiagramByDiagramId(diagramId));
        model.addAttribute(API_KEY, cacooApiServices.getApiKey());
        return DIAGRAM;
    }

    @RequestMapping(value = "/copy/copyDiagram", method = RequestMethod.POST)
    public String copyDiagram(@RequestParam String diagramId, @RequestParam String title, @RequestParam String description, ModelMap model) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add(DIAGRAM_ID, diagramId);
        map.add(TITLE, title);
        map.add(DESCRIPTION, description);
        model.addAttribute(DIAGRAM, cacooApiServices.copyDiagramToFolder(map, diagramId));
        return "copyDiagram";
    }

    @RequestMapping("/copy/{diagramId}")
    public String copy(@PathVariable(DIAGRAM_ID) String diagramId, ModelMap model) {
        model.put(DIAGRAM_ID, diagramId);
        return "copy";
    }

    @RequestMapping("/delete/{diagramId}")
    public String delete(@PathVariable(DIAGRAM_ID) String diagramId, ModelMap model) {
        model.addAttribute(DIAGRAM_ID, diagramId);
        cacooApiServices.deleteDiagram(diagramId);
        return "delete";
    }

    @RequestMapping("/")
    public String cacoo(ModelMap model) {
        model.addAttribute(ACCOUNT, cacooApiServices.getAccount());
        model.addAttribute(API_KEY, cacooApiServices.getApiKey());
        FolderResponse folderResponse = cacooApiServices.getFolders();
        if (folderResponse.getResult().length != 0) {
            model.addAttribute(FOLDERS, cacooApiServices.getFolders().getResult());
        }
        DiagramsResponse diagramsResponse = cacooApiServices.getDiagrams();
        if (diagramsResponse.getCount() != 0) {
            model.addAttribute(DIAGRAMS, cacooApiServices.getDiagrams().getResult());
        }
        return "cacoo";
    }

}