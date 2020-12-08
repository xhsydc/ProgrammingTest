package com.xhsydc.shopdemo.service;

import com.alibaba.fastjson.JSON;
import com.xhsydc.shopdemo.constant.CodeType;
import com.xhsydc.shopdemo.entity.SellEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ReportService {

    public Map<String,Long> trackReport(List<SellEntity> data) {
        List<SellEntity> relList = new ArrayList<>();
        List<SellEntity> itcList = new ArrayList<>();
        List<SellEntity> infList = new ArrayList<>();
        for(SellEntity sellEntity :data){
            if(CodeType.REL.equalsIgnoreCase(sellEntity.getSecurityCode())){
                relList.add(sellEntity);
            }else if(CodeType.ITC.equalsIgnoreCase(sellEntity.getSecurityCode())){
                itcList.add(sellEntity);
            }else if(CodeType.INF.equalsIgnoreCase(sellEntity.getSecurityCode())){
                infList.add(sellEntity);
            }
        }
        relList = relList.stream().sorted(Comparator.comparingLong(SellEntity::getVersion)).collect(Collectors.toList());
        itcList = itcList.stream().sorted(Comparator.comparingLong(SellEntity::getVersion)).collect(Collectors.toList());
        infList = infList.stream().sorted(Comparator.comparingLong(SellEntity::getVersion)).collect(Collectors.toList());
        return packageData(relList,itcList,infList);
    }

    private Map<String,Long> packageData(List<SellEntity> relList, List<SellEntity> itcList, List<SellEntity> infList) {
        long relcount = getCount(relList);
        long itccount = getCount(itcList);
        long infcount = getCount(infList);
        Map<String,Long>res = new HashMap<>();
        res.put(CodeType.REL,relcount);
        res.put(CodeType.ITC,itccount);
        res.put(CodeType.INF,infcount);
        String result = JSON.toJSONString(res);
        log.info("返回参数{}",result);
        return res;
    }

    private Long getCount(List<SellEntity> relList) {
        Long count = 0L;
        String lastSell = "";
        String lastOp = "";
        for(SellEntity sellEntity :relList){
            if(CodeType.op_cancel.equalsIgnoreCase(sellEntity.getOpType())){
                count = 0L;
                break;
            }
            if(lastOp.equalsIgnoreCase(sellEntity.getOpType())&&lastSell.equalsIgnoreCase(sellEntity.getSellBuy())){
                count = sellEntity.getQuantity();
            }
            if(CodeType.op_insert.equalsIgnoreCase(sellEntity.getOpType())){
                if(CodeType.buy.equalsIgnoreCase(sellEntity.getSellBuy())){
                    count+=sellEntity.getQuantity();
                }else if(CodeType.sell.equalsIgnoreCase(sellEntity.getSellBuy())){
                    count-=sellEntity.getQuantity();
                }
                lastSell = sellEntity.getSellBuy();
                lastOp = sellEntity.getOpType();
                continue;
            }
            if(CodeType.op_update.equalsIgnoreCase(sellEntity.getOpType())){
                lastOp = CodeType.op_update;
                if(lastSell.equalsIgnoreCase(sellEntity.getSellBuy())){
                    count = sellEntity.getQuantity();
                    lastSell  = sellEntity.getSellBuy();
                    continue;
                }
                if(CodeType.buy.equalsIgnoreCase(sellEntity.getSellBuy())){
                    count+=sellEntity.getQuantity();
                }else if(CodeType.sell.equalsIgnoreCase(sellEntity.getSellBuy())){
                    count-=sellEntity.getQuantity();
                }
                lastSell  = sellEntity.getSellBuy();
            }
        }
        return count;
    }
}
