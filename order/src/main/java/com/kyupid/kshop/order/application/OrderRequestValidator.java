package com.kyupid.kshop.order.application;

import com.kyupid.kshop.order.presentation.OrderRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * OrderRequest 에 대한 validate 처리
 */
public class OrderRequestValidator {

    private static final String RETURN_CODE = "REQUIRED";

    public List<ValidationError> validate(OrderRequest orderRequest) {
        List<ValidationError> errors = new ArrayList<>();
        if (orderRequest == null) {
            errors.add(ValidationError.of(RETURN_CODE));
        } else {
            if (orderRequest.getOrdererMemberId() == null)
                errors.add(ValidationError.of("ordererMemberId", RETURN_CODE));
            if (orderRequest.getStockAdjustmentList() == null)
                errors.add(ValidationError.of("stockAdjustmentList", RETURN_CODE));
            if (orderRequest.getStockAdjustmentList().isEmpty())
                errors.add(ValidationError.of("stockAdjustmentList", RETURN_CODE));
            if (orderRequest.getDeliveryInfo() == null) {
                errors.add(ValidationError.of("deliveryInfo", RETURN_CODE));
            } else {
                if (orderRequest.getDeliveryInfo().getReceiverName() == null)
                    errors.add(ValidationError.of("deliveryInfo.receiver", RETURN_CODE));
                if (orderRequest.getDeliveryInfo().getAddress() == null)
                    errors.add(ValidationError.of("deliveryInfo.address", RETURN_CODE));
                if (orderRequest.getDeliveryInfo().getPhone() == null)
                    errors.add(ValidationError.of("deliveryInfo.phone", RETURN_CODE));
            }
        }
        return errors;
    }
}
