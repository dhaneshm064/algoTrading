package com.stockmarket.technicalanalysis.service;

import com.stockmarket.technicalanalysis.exception.TechnicalAnalysisException;
import com.stockmarket.technicalanalysis.util.FileUtil;
import com.stockmarket.technicalanalysis.vo.StockBasicInfo;
import com.stockmarket.technicalanalysis.vo.StockTechnicalInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
@Slf4j
public class StockTechnicalInfoService {

    @Autowired
    FileUtil fileUtil;

    public TreeMap<String,StockTechnicalInfo> calculateSMA(List<StockBasicInfo>  stockBasicInfoList, TreeMap<String,StockTechnicalInfo>  stockTechnicalInfoMap, int timePeriod) throws TechnicalAnalysisException {
        log.debug("In StockTechnicalInfoService.CalculateSMA() ");
        if(stockTechnicalInfoMap == null) {
            stockTechnicalInfoMap = new TreeMap<>();
        }
        int n = timePeriod;
        float sum = 0;
        if (stockBasicInfoList != null && stockBasicInfoList.size() > timePeriod) {
            for (int i = 0; i < stockBasicInfoList.size(); i++) {
                if (i < timePeriod) {
                    StockBasicInfo stockBasicInfo = stockBasicInfoList.get(i);
                    //Calculate SMA for first n items i.e -> 0
                    StockTechnicalInfo stockTechnicalInfo = updateSMAForStockTechnicalInfo(n, stockBasicInfo, stockTechnicalInfoMap ,0f);
                    //Updating StockTechnicalInfo List
                    String key = stockBasicInfo.getSymbol()+stockBasicInfo.getDate().toString();
                    stockTechnicalInfoMap.put(key, stockTechnicalInfo);
                    sum=sum+stockBasicInfo.getClose();
                } else {
                    StockBasicInfo stockBasicInfo = stockBasicInfoList.get(i);
                    sum = sum-stockBasicInfoList.get(i-timePeriod).getClose();
                    sum = sum+stockBasicInfo.getClose();
                    float SMA = sum/timePeriod;
                    StockTechnicalInfo stockTechnicalInfo = updateSMAForStockTechnicalInfo(timePeriod, stockBasicInfo,stockTechnicalInfoMap, SMA);
                    String key = stockBasicInfo.getSymbol()+stockBasicInfo.getDate().toString();
                    stockTechnicalInfoMap.put(key, stockTechnicalInfo);                }
            }

        } else {
            throw new TechnicalAnalysisException("Input list provided " + stockBasicInfoList + " is either null or size < timeperiod " + timePeriod);
        }
        return stockTechnicalInfoMap;
    }


    private StockTechnicalInfo updateSMAForStockTechnicalInfo(int timePeriod, StockBasicInfo stockBasicInfo,Map<String, StockTechnicalInfo>  stockTechnicalInfoMap, float SMA) {

        //Fetching data from stockBasicInfo
        Timestamp date = stockBasicInfo.getDate();
        String stockSymbol = stockBasicInfo.getSymbol();
        String stockName = stockBasicInfo.getName();

        //Updating SMA-Map for first 'timeperiod' stocks in the list to 0
        String key = stockSymbol+date.toString();
        StockTechnicalInfo stockTechnicalInfo = stockTechnicalInfoMap.get(key);
        if(stockTechnicalInfo == null)
        {
            stockTechnicalInfo = new StockTechnicalInfo();
        }
        HashMap<Integer,Float> SMA_Map = stockTechnicalInfo.getSimpleMovingAverage();
        if(SMA_Map == null)
        {
            SMA_Map = new HashMap<>();
        }
        SMA_Map.put(timePeriod, SMA);

        stockTechnicalInfo.setDate(date);
        stockTechnicalInfo.setName(stockName);
        stockTechnicalInfo.setSymbol(stockSymbol);
        stockTechnicalInfo.setSimpleMovingAverage(SMA_Map);
        stockTechnicalInfo.setStockBasicInfo(stockBasicInfo);
        return stockTechnicalInfo;
    }
}
