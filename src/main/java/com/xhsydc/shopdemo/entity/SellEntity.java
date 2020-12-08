package com.xhsydc.shopdemo.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SellEntity {
    private Long transactionId;
    private Long tradeId;
    private Long version;
    private String securityCode;
    private Long quantity;
    private String opType;
    private String sellBuy;
}
