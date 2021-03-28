package com.stockmarket.technicalanalysis.controller;


import com.stockmarket.technicalanalysis.constant.StockMarketConstants;
import com.stockmarket.technicalanalysis.service.StockBasicInfoService;
import com.stockmarket.technicalanalysis.util.DateUtil;
import com.stockmarket.technicalanalysis.vo.StockBasicInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;
import java.util.TreeMap;

@RestController
@RequestMapping("/StockBasicInfo")
@Slf4j
public class StockDailyInfoController {


    @Autowired
    StockBasicInfoService stockBasicInfoService;

    @GetMapping("/getStockDetailList")
    public List<StockBasicInfo> getStockInfo(@RequestParam("stockSymbol") String stockSymbol) throws Exception {
        List<StockBasicInfo> stockList = stockBasicInfoService.getStockBasicInfo(stockSymbol);
        return stockList;
    }

    @GetMapping("/updateMissingData")
    public List<StockBasicInfo> getStockInfo() throws Exception {
        List<StockBasicInfo> stockList = null;
        TreeMap<String,Boolean> hashMap =new TreeMap<>();
        StringBuffer stringBuffer = new StringBuffer();
        List<StockBasicInfo> list = stockBasicInfoService.getStockBasicInfo("ACC");
        for (StockBasicInfo stockBasicInfo: list)
        {
            hashMap.put(stockBasicInfo.getDate().toString(), true);
        }
        for(String timestamp: hashMap.keySet())
        {
            hashMap.put(timestamp,false);
        }

        for(String stockSymbol: StockMarketConstants.stockSymbolList) {
            stockList = stockBasicInfoService.getStockBasicInfo(stockSymbol);

            log.info(stockSymbol+" stockList "+stockList.size());
            StockBasicInfo stockBasicInfo = stockList.get(0);
            for(int i=0; i<stockList.size(); i++)
            {
                Timestamp key = stockList.get(i).getDate();
                hashMap.put(key.toString(),true);
            }
            for(String timestamp: hashMap.keySet())
            {
                if(!hashMap.get(timestamp))
                {
                    stringBuffer.append("Unavailable data for "+stockBasicInfo.getName()+" "+timestamp+"\n");
                   // log.info("Unavailable data for "+stockBasicInfo.getName()+" "+timestamp);
                }
            }

            for(String timestamp: hashMap.keySet())
            {
                hashMap.put(timestamp,false);
            }

        }
        return stockList;
    }
    @GetMapping("/getStockDetail")
    public String getStockInfo(@RequestParam("stockSymbol") String stockSymbol, @RequestParam("date")String dateString) throws Exception {
        Timestamp timeStampDate = DateUtil.getTimeStampFromString(dateString, StockMarketConstants.DATE_FORMAT);
        String stockBasicInfo = stockBasicInfoService.getStockBasicInfo(stockSymbol,timeStampDate);
        return stockBasicInfo;
    }

    @GetMapping("/getStockDetailRange")
    public List<StockBasicInfo>  getStockInfo(@RequestParam("stockSymbol") String stockSymbol, @RequestParam("startDate")String startDateString, @RequestParam("endDate") String endDateString) throws Exception {
        Timestamp startDate = DateUtil.getTimeStampFromString(startDateString,StockMarketConstants.DATE_FORMAT);
        Timestamp endDate = DateUtil.getTimeStampFromString(endDateString,StockMarketConstants.DATE_FORMAT);
        List<StockBasicInfo> stockList = stockBasicInfoService.getStockBasicInfo(stockSymbol,startDate, endDate);
        return stockList;
    }

}
