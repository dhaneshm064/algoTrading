package com.stockmarket.technicalanalysis.service;

import com.stockmarket.technicalanalysis.constant.StockMarketConstants;
import com.stockmarket.technicalanalysis.exception.TechnicalAnalysisException;
import com.stockmarket.technicalanalysis.intf.InterfaceService;
import com.stockmarket.technicalanalysis.vo.StockBasicInfo;
import com.stockmarket.technicalanalysis.vo.StockTechnicalInfo;
import com.stockmarket.technicalanalysis.vo.Trade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
@Slf4j
public class AlgoTradingService {


    @Autowired
    InterfaceService interfaceService;

    @Autowired
    StockTechnicalInfoService stockTechnicalInfoService;


    public String goldenCrossOverStrategy(float perTradeAmount, int floorSMA, int ceilSMA, float stoplossPercent, float targetPercent, float trailingStopLoss, float increasingTarget) throws Exception {
        List<Trade> allTrades = new ArrayList<>();
        for(String stockSymbol: StockMarketConstants.stockSymbolList) {
            log.debug("Fetching data for stockSymbol "+stockSymbol);
            List<StockBasicInfo> stockBasicInfos = interfaceService.getStockBasicInfo(stockSymbol);
            log.debug("Fetched data for stockSymbol "+stockSymbol+" of size "+stockBasicInfos.size());
            TreeMap<String, StockTechnicalInfo> stockTechnicalInfoMap = getStockTechnicalInfoTreeMap(stockSymbol, stockBasicInfos, floorSMA, ceilSMA);
            log.debug("Applying golden crossover buying strategy");
            List<Trade> tradeList = applyGoldenCrossOverStrategy(stockTechnicalInfoMap, stockBasicInfos,perTradeAmount,floorSMA, ceilSMA, stoplossPercent, targetPercent, trailingStopLoss, increasingTarget);
            allTrades.addAll(tradeList);
        }

        return processAlgoStrategyOutput(allTrades);

    }

    public String processAlgoStrategyOutput(List<Trade> allTrades){
        float PnL = 0f;
        int successsfulTrades = 0;
        int unsuccessfulTrades = 0;
        float ROE = 0;
        for(int i = 0; i < allTrades.size(); i++)
        {
            Trade trade = allTrades.get(i);
            PnL = PnL + trade.getPnL();
            ROE = ROE +trade.getReturnOnEquity();
            if(trade.getPnL() < 0)
            {
                unsuccessfulTrades++;
            }
            else {
                successsfulTrades++;
            }
        }


        ROE=ROE/allTrades.size();

        String output = "Total PnL: "+PnL+"\n"+
                "Successful Trades "+successsfulTrades+" UnSuccessful Trades "+unsuccessfulTrades+"\n"+
                "Average ROE per trade "+ROE+"\n"+
                allTrades.toString();
        log.info("total PnL "+PnL);
        log.info("Successful Trades "+successsfulTrades+" UnSuccessful Trades "+unsuccessfulTrades);
        log.info("Average ROE "+ROE);
        log.info("Total Successful Trades Percentage: "+successsfulTrades*100/(successsfulTrades+unsuccessfulTrades));

        return output;
    }

    private TreeMap<String, StockTechnicalInfo> getStockTechnicalInfoTreeMap(String stockSymbol, List<StockBasicInfo> stockBasicInfos, int timePeriod1, int timePeriod2) throws TechnicalAnalysisException {
        log.debug("Calculating SMA "+timePeriod1+" for stockSymbol "+stockSymbol);
        TreeMap<String, StockTechnicalInfo> stockTechnicalInfoMap = stockTechnicalInfoService.calculateSMA(stockBasicInfos, null, timePeriod1);
        log.debug("Calculated SMA "+timePeriod1+" for stockSymbol "+stockSymbol);

        log.debug("Calculating SMA "+timePeriod2+" for stockSymbol "+stockSymbol);
        stockTechnicalInfoMap = stockTechnicalInfoService.calculateSMA(stockBasicInfos,stockTechnicalInfoMap, timePeriod2);
        log.debug("Calculated SMA "+timePeriod2+" for stockSymbol "+stockSymbol);
        return stockTechnicalInfoMap;
    }


    private List<Trade> applyGoldenCrossOverStrategy(TreeMap<String, StockTechnicalInfo> stockTechnicalInfoMap, List<StockBasicInfo> stockBasicInfoList, float perTradeAmount, int timePeriod1, int timePeriod2,
                                                     float stoplossPercent, float targetPercent, float trailingStopLoss, float increasingTarget) {

        List<Trade> tradeList = new ArrayList<>();
        Trade trade = new Trade();
        trade.isTradeActive = false;

        for(int i=0;i<stockBasicInfoList.size();i++)
        {
            StockBasicInfo stockBasicInfo = stockBasicInfoList.get(i);
            String stockSymbol = stockBasicInfo.getSymbol();
            Timestamp date = stockBasicInfo.getDate();
            String key = stockSymbol+date.toString();
            StockTechnicalInfo stockTechnicalInfo = stockTechnicalInfoMap.get(key);

            float prevClose = stockBasicInfo.getPrevClose();
            float closePrice = stockBasicInfo.getClose();
            float SMA_50 = stockTechnicalInfo.getSimpleMovingAverage().get(timePeriod1);
            float SMA_200 = stockTechnicalInfo.getSimpleMovingAverage().get(timePeriod2);
            if(closePrice>SMA_50 && closePrice<SMA_200 && prevClose<SMA_50 && !trade.isTradeActive )
            {
                trade.setBuyingDate(date);
                trade.setIsTradeActive(true);
                trade.setBuyingPrice(closePrice);
                float qty = perTradeAmount/closePrice;
                trade.setStockSymbol(stockSymbol);
                trade.setQuantity(qty);
                float stopLoss = closePrice - stoplossPercent*closePrice*0.01f;
                float target = closePrice + targetPercent*closePrice*0.01f;
                trade.setStoploss(stopLoss);
                trade.setTarget(target);
            }
            if(trade.getIsTradeActive())
            {
                float stopLoss = trade.getStoploss();
                float target = trade.getTarget();
                if( closePrice < stopLoss)
                {

                    trade.setSellingDate(date);
                    trade.setSellingPrice(closePrice);

                    float quantity = trade.getQuantity();
                    float buyingPrice = trade.getBuyingPrice();
                    trade.setPnL(closePrice*quantity - buyingPrice*quantity);
                    trade.setIsTradeActive(false);
                    float roe = (closePrice*quantity - buyingPrice*quantity)*100/(buyingPrice*quantity);
                    trade.setReturnOnEquity(roe);
                    tradeList.add(trade);
                    log.info(trade.toString());
                    trade = new Trade();
                    trade.setIsTradeActive(false);
                }
                if(closePrice > target){
                    trade.setTarget((float) (closePrice+0.01*increasingTarget*target));
                    trade.setStoploss((float) (closePrice - 0.01*trailingStopLoss*target));
                }

            }
        }
        if(trade.getIsTradeActive())
        {
            log.debug("OPEN POSITON FOR TRADE "+trade.toString());
        }
        return tradeList;
    }

}
