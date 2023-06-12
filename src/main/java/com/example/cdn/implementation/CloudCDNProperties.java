package com.example.cdn.implementation;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cloudcdn")
public class CloudCDNProperties {

    private String projectId;
    private String urlMap;

    public String getProjectId(){
        return projectId;
    }

    public void setProjectId(String projectId){
        this.projectId = projectId;
    }

    public String getUrlMap() {
        return urlMap;
    }

    public void setUrlMap(String urlMap) {
        this.urlMap = urlMap;
    }

    @Override
    public String toString() {
        return "CloudCDNProperties [projectId=" + projectId + ", urlMap=" + urlMap + "]";
    }

}
