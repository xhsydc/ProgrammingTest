package com.xhsydc.shopdemo.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.xhsydc.shopdemo.entity.SellEntity;
import com.xhsydc.shopdemo.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping("/report")
    public Map<String,Long> sellReport(@RequestBody List<SellEntity> data){
        String reqParam = JSON.toJSONString(data);
        log.info("请求参数{}",reqParam);
        return reportService.trackReport(data);
    }
}
