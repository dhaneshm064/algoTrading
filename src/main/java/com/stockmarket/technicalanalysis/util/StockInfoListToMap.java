package com.stockmarket.technicalanalysis.util;

import com.stockmarket.technicalanalysis.vo.StockBasicInfo;
import com.stockmarket.technicalanalysis.vo.StockTechnicalInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class StockInfoListToMap {

    public Map<String,StockBasicInfo> convertListStockBasicInfoToMap(List<StockBasicInfo> stockBasicInfoList)
    {
        Map<String,StockBasicInfo> stringStockBasicInfoMap = new HashMap<>();
        for(int i=0;i<stockBasicInfoList.size();i++)
        {
            StockBasicInfo stockBasicInfo = stockBasicInfoList.get(i);
            String key = stockBasicInfo.getName()+stockBasicInfo.getDate().toString();
            stringStockBasicInfoMap.put(key, stockBasicInfo);
        }
        return stringStockBasicInfoMap;
    }
    public Map<String, StockTechnicalInfo> convertListStockTechnicalInfoToMap(List<StockTechnicalInfo> stockTechnicalInfoList)
    {
        Map<String,StockTechnicalInfo> stringStockTechnicalInfoMap = new HashMap<>();
        for(int i=0;i<stockTechnicalInfoList.size();i++)
        {
            StockTechnicalInfo stockTechnicalInfo = stockTechnicalInfoList.get(i);
            String key = stockTechnicalInfo.getName()+stockTechnicalInfo.getDate().toString();
            stringStockTechnicalInfoMap.put(key, stockTechnicalInfo);
        }
        return stringStockTechnicalInfoMap;
    }

}
