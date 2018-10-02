/**
 * @author DatNT29
 *
 * File       : Greeting.java
 * Created On : 10/09/2018 (dd/MM/YYYY)
 */

package com.util.resources;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:HouseholdApiUrl.properties")
@ConfigurationProperties(prefix = "api")
public class HouseholdApiUrl {

    private String email;
    private String device;
    private String message;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
    
    public String getMessage() {
        return message;
    }

    public void setmessage(String message) {
        this.message = message;
    }
}

