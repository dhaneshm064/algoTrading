package com.stockmarket.technicalanalysis.controller;

import com.stockmarket.technicalanalysis.intf.InterfaceService;
import com.stockmarket.technicalanalysis.service.StockTechnicalInfoService;
import com.stockmarket.technicalanalysis.vo.StockBasicInfo;
import com.stockmarket.technicalanalysis.vo.StockTechnicalInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Slf4j
@Controller
@RequestMapping("/StockTechnicalInfo")
public class StockTechnicalInfoController {
    @Autowired
    InterfaceService interfaceService;

    @Autowired
    StockTechnicalInfoService stockTechnicalInfoService;

    @GetMapping("/CalculateSMA")
    public void calculateSMA(@RequestParam("stockSymbol") String stockSymbol, @RequestParam("timePeriod") int timePeriod ) throws Exception {
        List<StockBasicInfo> stockBasicInfos = interfaceService.getStockBasicInfo(stockSymbol);

        TreeMap<String, StockTechnicalInfo> stockTechnicalInfos = stockTechnicalInfoService.calculateSMA(stockBasicInfos,null, timePeriod);
        log.debug(stockTechnicalInfos.toString());

    }

}
