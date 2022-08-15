package com.kyupid.kshop.order.application;

import com.kyupid.kshop.order.presentation.OrderRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * OrderRequest 에 대한 validate 처리
 */
public class OrderRequestValidator {

    private static final String CODE = "REQUIRED";

    public List<ValidationError> validate(OrderRequest orderRequest) {
        List<ValidationError> errors = new ArrayList<>();
        if (orderRequest == null) {
            errors.add(ValidationError.of(CODE));
        } else {
            if (orderRequest.getOrdererId() == null)
                errors.add(ValidationError.of("ordererId", CODE));
            if (orderRequest.getOrderProductList() == null)
                errors.add(ValidationError.of("orderProductList", CODE));
            if (orderRequest.getOrderProductList().isEmpty())
                errors.add(ValidationError.of("orderProductList", CODE));
            if (orderRequest.getDeliveryInfo() == null) {
                errors.add(ValidationError.of("deliveryInfo", CODE));
            } else {
                if (orderRequest.getDeliveryInfo().getReceiverName() == null)
                    errors.add(ValidationError.of("deliveryInfo.receiver", CODE));
                if (orderRequest.getDeliveryInfo().getAddress() == null)
                    errors.add(ValidationError.of("deliveryInfo.address", CODE));
                if (orderRequest.getDeliveryInfo().getPhone() == null)
                    errors.add(ValidationError.of("deliveryInfo.phone", CODE));
            }
        }
        return errors;
    }
}
