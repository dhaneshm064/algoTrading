package com.stockmarket.technicalanalysis.service;

import com.stockmarket.technicalanalysis.dao.StockBasicInfoRepository;
import com.stockmarket.technicalanalysis.vo.StockBasicInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@Slf4j
public class StockBasicInfoService {

    @Autowired
    StockBasicInfoRepository stockBasicInfoRepository;

    public List<StockBasicInfo> getStockBasicInfo(String stockSymbol) throws Exception {
        try{
            List <StockBasicInfo> stockBasicInfoList = stockBasicInfoRepository.getStockBasicInfo(stockSymbol);
            return stockBasicInfoList;
        }
        catch (Exception e){
            log.error("Unable to fetch stockBasicInfo List for stockSymbol "+stockSymbol,e);
            throw new Exception("Unable to fetch stockBasicInfo List for stockSymbol "+stockSymbol,e);
        }

    }

    public String getStockBasicInfo(String stockSymbol, Timestamp date) throws Exception {
        try {
            String stockBasicInfo = stockBasicInfoRepository.getStockBasicInfo(stockSymbol, date);
            return stockBasicInfo;
        }
        catch (Exception e){
            log.error("Unable to fetch stockBasicInfo for stockSymbol "+stockSymbol+" and date "+date, e);
            throw new Exception("Unable to fetch stockBasicInfo for stockSymbol "+stockSymbol+" and date "+date, e);
        }
    }

    public List<StockBasicInfo> getStockBasicInfo(String stockSymbol, Timestamp startDate, Timestamp endDate) throws Exception {
        try {
            List <StockBasicInfo> stockBasicInfoList = stockBasicInfoRepository.getStockBasicInfo(stockSymbol, startDate, endDate);
            return stockBasicInfoList;
        }
        catch (Exception e){
            log.error("Unable to fetch stockBasicInfo for stockSymbol "+stockSymbol+" from startDate "+startDate+" and endDate "+endDate, e);
            throw new Exception("Unable to fetch stockBasicInfo for stockSymbol "+stockSymbol+" from startDate "+startDate+" and endDate "+endDate, e);
        }
    }

}
