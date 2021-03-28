package com.stockmarket.technicalanalysis.intf;

import com.stockmarket.technicalanalysis.exception.TechnicalAnalysisException;
import com.stockmarket.technicalanalysis.vo.StockBasicInfo;
import com.stockmarket.technicalanalysis.vo.StockTechnicalInfo;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;
import java.util.TreeMap;

@Component
public interface InterfaceService {

    public List<StockBasicInfo> getStockBasicInfo(String stockSymbol) throws Exception;

    public String getStockBasicInfo(String stockSymbol, Timestamp date) throws Exception;

    public List<StockBasicInfo> getStockBasicInfo(String stockSymbol, Timestamp startDate, Timestamp endDate) throws Exception;

    public TreeMap<String, StockTechnicalInfo>  calculateSMA(List<StockBasicInfo>  stockBasicInfoList, TreeMap<String,StockTechnicalInfo>  stockTechnicalInfoMap, int timePeriod) throws TechnicalAnalysisException;
}
