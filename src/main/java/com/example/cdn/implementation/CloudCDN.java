package com.example.cdn.implementation;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.example.cdn.CDN;
import com.google.api.services.compute.Compute;
import com.google.api.services.compute.model.CacheInvalidationRule;
import com.google.api.services.compute.model.Operation;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.ServiceAccountCredentials;

@Component
@ConditionalOnProperty(prefix = "cdn", name = "provider", havingValue = "cloudcdn")
@EnableConfigurationProperties(CloudCDNProperties.class)
public class CloudCDN implements CDN {

    @Autowired
    private CloudCDNProperties properties;
    private Compute compute;

    public CloudCDN() throws GeneralSecurityException, IOException{
        this.compute = new Compute.Builder(GoogleNetHttpTransport.newTrustedTransport(), 
                                    GsonFactory.getDefaultInstance(), 
                                    new HttpCredentialsAdapter(ServiceAccountCredentials.getApplicationDefault()))
                                    .setApplicationName("Cloud CDN Invalidation Utility")
                                    .build();
    }

    @Override
    public void invalidate(String key) {
        String []cacheKeyParts = key.split("/");
        CacheInvalidationRule rule = new CacheInvalidationRule();
        rule.setPath("/"+cacheKeyParts[1]);
        rule.setHost(cacheKeyParts[0]);
        try {
            Operation response = compute.urlMaps().invalidateCache(properties.getProjectId(), properties.getUrlMap(), rule).execute();
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
