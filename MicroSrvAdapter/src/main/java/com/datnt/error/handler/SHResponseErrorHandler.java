/**
 * @author DatNT29
 *
 * File       : SHResponseErrorHandler.java
 * Created On : 11/09/2018 (dd/MM/YYYY)
 */

package com.datnt.error.handler;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import com.datnt.error.exception.SHAPIAccessException;

import static com.util.resources.SHAPIErrorMessages.*;


public class SHResponseErrorHandler implements ResponseErrorHandler {
    private static final Logger logger = LogManager.getLogger(SHResponseErrorHandler.class);

    @Override
    public void handleError(ClientHttpResponse clienthttpresponse) throws IOException {
        logger.debug("Processing Request error");
        if (clienthttpresponse.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            logger.debug(HttpStatus.UNAUTHORIZED + " response.");
            throw new SHAPIAccessException(UNAUTHORIZED);
        }
    }

    @Override
    public boolean hasError(ClientHttpResponse clienthttpresponse) throws IOException {
        logger.debug("Request has error");
        switch (clienthttpresponse.getStatusCode()) {
        case OK:
            return false;
        case FORBIDDEN:
            logger.debug("Call returned a error 403 forbidden resposne ");
            return true;
        case UNAUTHORIZED:
            logger.debug("Unauthorized ");
            return true;
        default:
            return false;
        }
    }
}
