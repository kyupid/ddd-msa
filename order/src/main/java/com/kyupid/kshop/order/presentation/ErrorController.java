package com.kyupid.kshop.order.presentation;

import com.kyupid.kshop.order.presentation.exception.NoTokenException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ErrorController {

    @RequestMapping("/api/error")
    public void handleError(HttpServletRequest request) {
        String message = String.valueOf(request.getAttribute("message"));
        String exception = String.valueOf(request.getAttribute("exception"));

        if (exception.equals("NoTokenException")) {
            throw new NoTokenException();
        } else {
            throw new RuntimeException(message);
        }
    }
}
