package com.kyupid.kshop.order.infra;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@Slf4j
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
        return (httpResponse.getStatusCode().series() == CLIENT_ERROR
                || httpResponse.getStatusCode().series() == SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {
        if (httpResponse.getStatusCode().series() == SERVER_ERROR) {
            Map<String, String> exception = new HashMap<>();
            exception.put("status", "연동 API 에러");
            throw new ExternalApiServerException(new JSONObject(exception));
        } else if (httpResponse.getStatusCode().series() == CLIENT_ERROR) {
            JSONObject resultData = getJsonObject(httpResponse);
            throw new ExternalApiServerException(resultData);
        }
    }

    private JSONObject getJsonObject(ClientHttpResponse httpResponse) throws IOException {
        InputStream inputStreamObject = httpResponse.getBody();
        BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStreamObject, "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();
        String inputStr;
        while ((inputStr = streamReader.readLine()) != null)
            responseStrBuilder.append(inputStr);
        JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());
        return jsonObject;
    }
}
