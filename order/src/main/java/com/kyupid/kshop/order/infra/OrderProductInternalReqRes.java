package com.kyupid.kshop.order.infra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductInternalReqRes {
    private List<OrderProductDto> orderProductDtoList;
}
