package com.kyupid.kshop.order.infra;

import lombok.Getter;
import org.json.JSONObject;

@Getter
public class ExternalApiServerException extends RuntimeException {
    private final JSONObject result;

    public ExternalApiServerException(JSONObject result) {
        this.result = result;
    }
}
