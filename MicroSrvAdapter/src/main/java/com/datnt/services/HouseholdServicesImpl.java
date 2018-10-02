/**
 * @author DatNT29
 *
 * File       : CCServicesImpl.java
 * Created On : 11/09/2018 (dd/MM/YYYY)
 */

package com.datnt.services;

import java.text.MessageFormat;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.datnt.error.handler.SHResponseErrorHandler;
import com.datnt.models.Device;
import com.util.resources.HouseholdApiUrl;

@Service
public class HouseholdServicesImpl implements HousholdServices {
    @Autowired
    private HouseholdApiUrl HouseholdApiUrl;

    private static final Logger logger = LogManager.getLogger(HouseholdServicesImpl.class);
    
    @Autowired
    private RestTemplate restclient;

    public ResponseEntity<Device> getDevices(String quantity, String contractType) {
        try {
//            RestTemplate restclient = new RestTemplate();
            restclient.setErrorHandler(new SHResponseErrorHandler());
            // BEGIN - produce URL with complete request param
            String formattedUrl = MessageFormat.format(HouseholdApiUrl.getDevice(), quantity, "YOUR SERVICE");
            // END - produce URL with complete request param
            
            // BEGIN setting header
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("user-agent", "datnt/online/web");
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
            // END setting header

            ResponseEntity<Device> result = restclient.exchange(formattedUrl, HttpMethod.GET, entity, Device.class);
            return result;
        } 
        catch (Exception e) {
            if (e instanceof ResourceAccessException){
                // NOTE: Spring has wrapped any exception into ResourceAccessException
                ResourceAccessException exception = (ResourceAccessException) e; // Handle our custom exception
                logger.debug(exception.getMessage());
            } else {
                logger.debug(e.getMessage());
            }
            return new ResponseEntity<Device>( HttpStatus.FORBIDDEN);
        }
    }
    
    // TODO: Below method is used to verify exception handler for rest
    //  in order to use it, enable within file HouseholdApiUrl.properties at: api.device = http://172.20.60.215:6700
    public ResponseEntity<Device> getDevicesVerifyError(String quantity, String contractType) {
        try {
//            RestTemplate restclient = new RestTemplate();
            restclient.setErrorHandler(new SHResponseErrorHandler());
            // BEGIN - produce URL with complete request param
            String formattedUrl = MessageFormat.format(HouseholdApiUrl.getDevice(), quantity, "RECONTRACT");
            // END - produce URL with complete request param
            // BEGIN setting header
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("user-agent", "starhub/online/web");
            // TODO - BEGIN : testing
            headers.add("Content-Type", "application/json");
            headers.add("x-user-agent", "cf8b7c3aaddb32ac68436b104869768b");
            headers.add("x-forwarded-proto", "https");
            // TODO - END : testing
            System.out.println("line 2");
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

            // END setting header
            System.out.println("line 3");
            ResponseEntity<Device> result = restclient.exchange(formattedUrl, HttpMethod.GET, entity, Device.class);
            System.out.println("line 4");
            return result;
        } 
        catch (Exception e) {
            if (e instanceof ResourceAccessException){
                // NOTE: Spring has wrapped any exception into ResourceAccessException
                ResourceAccessException exception = (ResourceAccessException) e; // Handle our custom exception
                logger.debug(exception.getMessage());
            } else {
                logger.debug(e.getMessage());
            }
            return new ResponseEntity<Device>( HttpStatus.FORBIDDEN);
        }
    }

}
