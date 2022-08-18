package com.kyupid.kshop.order.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
        return (httpResponse.getStatusCode().series() == CLIENT_ERROR
                || httpResponse.getStatusCode().series() == SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {
        if (httpResponse.getStatusCode().series() == SERVER_ERROR) {
            throw new ExternalApiServerException();
        } else if (httpResponse.getStatusCode().series() == CLIENT_ERROR) {
            if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new GeneralNotFoundException(); //TODO: 어떤 품목들이 없는지 체크해서 response해야함
            } else if (httpResponse.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new GeneralNotFoundException();
            }
        }
    }
}
