package com.kyupid.kshop.order.application;

import com.kyupid.kshop.order.infra.StockAdjustment;
import com.kyupid.kshop.order.presentation.OrderRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * OrderRequest 에 대한 validate 처리
 */
public class OrderRequestValidator {

    private static final String PROPERTY_REQUIRED = "REQUIRED";
    private static final String QUANTITY_NOT_ACCEPTED = "MORE_THAN_ZERO";

    public List<ValidationError> validate(OrderRequest orderRequest) {
        List<ValidationError> errors = new ArrayList<>();
        if (orderRequest == null) {
            errors.add(ValidationError.of(PROPERTY_REQUIRED));
        } else {
            if (orderRequest.getOrdererMemberId() == null)
                errors.add(ValidationError.of("ordererMemberId", PROPERTY_REQUIRED));
            if (orderRequest.getStockAdjustmentList() == null)
                errors.add(ValidationError.of("stockAdjustmentList", PROPERTY_REQUIRED));
            if (orderRequest.getStockAdjustmentList().isEmpty()) {
                errors.add(ValidationError.of("stockAdjustmentList", PROPERTY_REQUIRED));
            } else {
                int count = 0;
                for (StockAdjustment sd : orderRequest.getStockAdjustmentList()) {
                    if (sd.getProductId() == null)
                        errors.add(ValidationError.of("stockAdjustment" + "[" + count + "]." + "getProductId", PROPERTY_REQUIRED));
                    if (sd.getQuantity() == null) {
                        errors.add(ValidationError.of("stockAdjustment" + "[" + count + "]." + "getQuantity", PROPERTY_REQUIRED));
                    } else if (sd.getQuantity() < 1) {
                        errors.add(ValidationError.of("stockAdjustment" + "[" + count + "]." + "getQuantity", QUANTITY_NOT_ACCEPTED));
                    }
                    count++;
                }
            }
            if (orderRequest.getDeliveryInfo() == null) {
                errors.add(ValidationError.of("deliveryInfo", PROPERTY_REQUIRED));
            } else {
                if (orderRequest.getDeliveryInfo().getReceiverName() == null)
                    errors.add(ValidationError.of("deliveryInfo.receiver", PROPERTY_REQUIRED));
                if (orderRequest.getDeliveryInfo().getAddress() == null)
                    errors.add(ValidationError.of("deliveryInfo.address", PROPERTY_REQUIRED));
                if (orderRequest.getDeliveryInfo().getPhone() == null)
                    errors.add(ValidationError.of("deliveryInfo.phone", PROPERTY_REQUIRED));
            }
        }
        return errors;
    }

}
