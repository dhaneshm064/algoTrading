package com.stockmarket.technicalanalysis.intf;

import com.stockmarket.technicalanalysis.exception.TechnicalAnalysisException;
import com.stockmarket.technicalanalysis.service.StockBasicInfoService;
import com.stockmarket.technicalanalysis.service.StockTechnicalInfoService;
import com.stockmarket.technicalanalysis.vo.StockBasicInfo;
import com.stockmarket.technicalanalysis.vo.StockTechnicalInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;
import java.util.TreeMap;

@Component
@Slf4j
public class InterfaceServiceImpl implements InterfaceService{

    @Autowired
    StockBasicInfoService stockBasicInfoService;

    @Override
    public List<StockBasicInfo> getStockBasicInfo(String stockSymbol) throws Exception {
        log.debug("In InterfaceServiceImpl.getStockBasicInfo() for stockSymbol "+stockSymbol);
        return stockBasicInfoService.getStockBasicInfo(stockSymbol);
    }

    @Override
    public String getStockBasicInfo(String stockSymbol, Timestamp date) throws Exception {
        log.debug("In InterfaceServiceImpl.getStockBasicInfo() for stockSymbol "+stockSymbol+" date "+date);
        return stockBasicInfoService.getStockBasicInfo(stockSymbol, date);
    }

    @Override
    public List<StockBasicInfo> getStockBasicInfo(String stockSymbol, Timestamp startDate, Timestamp endDate) throws Exception {
        log.debug("In InterfaceServiceImpl.getStockBasicInfo() for stockSymbol "+stockSymbol+" startDate "+startDate+" endDate "+endDate);
        return stockBasicInfoService.getStockBasicInfo(stockSymbol, startDate, endDate);
    }

    @Autowired
    StockTechnicalInfoService stockTechnicalInfoService;

    @Override
    public TreeMap<String, StockTechnicalInfo> calculateSMA(List<StockBasicInfo> stockBasicInfoList, TreeMap<String, StockTechnicalInfo> stockTechnicalInfoMap, int timePeriod) throws TechnicalAnalysisException {
        log.debug("In InterfaceServiceImpl.calculateSMA() for timeperiod "+timePeriod);
        return stockTechnicalInfoService.calculateSMA(stockBasicInfoList, stockTechnicalInfoMap, timePeriod);
    }
}
