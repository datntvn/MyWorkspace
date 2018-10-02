/**
 * @author DatNT29
 *
 * File       : SHAPIAccessException.java
 * Created On : 11/09/2018 (dd/MM/YYYY)
 */

package com.datnt.error.exception;

import java.io.IOException;

import org.springframework.http.HttpStatus;

public class SHAPIAccessException extends IOException {
    private static final long serialVersionUID = -82772113193094738L;
    private HttpStatus statusCode;
    private String body;

    public SHAPIAccessException(String msg) {
        super(msg);
        // Guideline - You can add your custom attribute initialization here 
    }
    public SHAPIAccessException(HttpStatus statusCode, String body, String msg) {
        super(msg);
        this.statusCode = statusCode;
        this.body = body;
    }
    public HttpStatus getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
}
