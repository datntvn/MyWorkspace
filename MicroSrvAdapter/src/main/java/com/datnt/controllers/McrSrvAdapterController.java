/**
 * @author DatNT29
 *
 * File       : CCAdapterController.java
 * Created On : 10/09/2018 (dd/MM/YYYY)
 */

package com.datnt.controllers;

import java.text.MessageFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.datnt.models.Device;
import com.datnt.models.Greeting;
import com.datnt.services.HousholdServices;
import com.util.resources.HouseholdApiUrl;
import com.util.resources.Messages;

@Controller
public class McrSrvAdapterController {

    // TODO: Below statement is just an example - remove it
    private static final String template = "Adapter welcome, %s!";
    // TODO: Below statement is just an example - remove it
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    Messages messages;

    @Autowired
    HouseholdApiUrl householdApiUrl;

    @Autowired
    private HousholdServices ccServices;

    private static Logger logger = LogManager.getLogger(McrSrvAdapterController.class);

    @GetMapping("/rolling")
    @ResponseBody
    public Greeting sayHello(@RequestParam(name = "name", required = false, defaultValue = "Stone") String name) {
        logger.info("[INFO] Request /rolling");
        // BEGIN - Note
        // TODO: Below code is an example to load messages from properties file - remove it
        System.out.println("v = ="+ messages.get("default.title"));
        // TODO: Below code is an example to load API URL from properties file - remove it
        System.out.println("device URL = ="+ householdApiUrl.getEmail());
        // TODO: Below code is an example to load API URL from properties file and set dynamic request param - remove it
        System.out.println("getMessage = ="+ householdApiUrl.getMessage());
        String formattedMessage = MessageFormat.format(householdApiUrl.getMessage(), "Kathy", "Jane", new Date(), "1days");
        System.out.println(formattedMessage); // Kathy met Jane in 10/09/2018 / 1days
        // END - Note
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/device")
    @ResponseBody
    public ResponseEntity<Device> getCC(
        @RequestParam(name = "quantity", required = false, defaultValue = "100") String quantity) {
        logger.info("[INFO] Request /device");
        ResponseEntity<Device> result = ccServices.getDevices(quantity, "RECONTRACT");
        return result;
    }
    
    // TODO: Below method is used to verify exception handler for rest
    //  in order to use it, enable within file HouseholdApiUrl.properties at: api.device = http://172.20.60.215:6700
    // Delete it when using in real development
    @GetMapping("/verifyexception")
    @ResponseBody
    public ResponseEntity<Device> getVerifyError(
        @RequestParam(name = "quantity", required = false, defaultValue = "100") String quantity) {
        logger.info("[INFO] Request /verifyexception");
        ResponseEntity<Device> result = ccServices.getDevicesVerifyError(quantity, "RECONTRACT");
        return result;
    }
    // END TODO
}
