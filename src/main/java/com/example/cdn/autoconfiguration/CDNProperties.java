package com.example.cdn.autoconfiguration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cdn")
public class CDNProperties {

    private CDNProvider provider;

    public CDNProvider getProvider(){
        return provider;
    }

    public void setProvider(CDNProvider provider){
        this.provider = provider;
    }

    @Override
    public String toString() {
        return "CDNProperties [provider=" + provider + "]";
    }


    

}
