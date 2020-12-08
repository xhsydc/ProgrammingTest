package com.xhsydc.shopdemo.controller;

import com.alibaba.fastjson.JSON;
import com.xhsydc.shopdemo.constant.CodeType;
import com.xhsydc.shopdemo.entity.SellEntity;
import com.xhsydc.shopdemo.service.ReportService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ReportControllerTest {

    @InjectMocks
    ReportController reportController;

    @Mock
    ReportService reportService;

    List<SellEntity> mock;

    @BeforeEach
    public void Initialization() throws Exception{
        MockitoAnnotations.initMocks(this);
        mock = new ArrayList<>();
        SellEntity s1 = new SellEntity();
        s1.setTransactionId(1L);
        s1.setTradeId(1L);
        s1.setVersion(1L);
        s1.setSecurityCode(CodeType.REL);
        s1.setQuantity(50L);
        s1.setOpType(CodeType.op_insert);
        s1.setSellBuy(CodeType.buy);
        SellEntity s2 = new SellEntity();
        s2.setTransactionId(2L);
        s2.setTradeId(2L);
        s2.setVersion(1L);
        s2.setSecurityCode(CodeType.ITC);
        s2.setQuantity(40L);
        s2.setOpType(CodeType.op_insert);
        s2.setSellBuy(CodeType.sell);
        SellEntity s3 = new SellEntity();
        s3.setTransactionId(3L);
        s3.setTradeId(3L);
        s3.setVersion(1L);
        s3.setSecurityCode(CodeType.INF);
        s3.setQuantity(70L);
        s3.setOpType(CodeType.op_insert);
        s3.setSellBuy(CodeType.buy);
        SellEntity s4 = new SellEntity();
        s4.setTransactionId(4L);
        s4.setTradeId(1L);
        s4.setVersion(2L);
        s4.setSecurityCode(CodeType.REL);
        s4.setQuantity(60L);
        s4.setOpType(CodeType.op_update);
        s4.setSellBuy(CodeType.buy);
        SellEntity s5 = new SellEntity();
        s5.setTransactionId(5L);
        s5.setTradeId(2L);
        s5.setVersion(2L);
        s5.setSecurityCode(CodeType.ITC);
        s5.setQuantity(30L);
        s5.setOpType(CodeType.op_cancel);
        s5.setSellBuy(CodeType.buy);
        SellEntity s6 = new SellEntity();
        s6.setTransactionId(6L);
        s6.setTradeId(4L);
        s6.setVersion(1L);
        s6.setSecurityCode(CodeType.INF);
        s6.setQuantity(20L);
        s6.setOpType(CodeType.op_insert);
        s6.setSellBuy(CodeType.sell);

        mock.add(s1);
        mock.add(s2);
        mock.add(s3);
        mock.add(s4);
        mock.add(s5);
        mock.add(s6);
        System.out.println("111");
    }

    @Test
    void sellReport() {
        when(reportService.trackReport((any()))).thenReturn(new HashMap<>());
        reportController.sellReport(mock);
    }
}