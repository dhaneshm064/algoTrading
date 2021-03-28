package com.stockmarket.technicalanalysis.controller;

import com.stockmarket.technicalanalysis.service.AlgoTradingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/algoTrading")
@Slf4j
public class AlgoTradingController {

    @Autowired
    AlgoTradingService algoTradingService;

    @GetMapping("/goldenCrossover")
    public String goldenCrossover(@RequestParam("perTradeAmount") Float perTradeAmount, @RequestParam("stopLossPercent") Float stopLossPercent, @RequestParam("targetPercent") Float targetPercent,@RequestParam("crossOverBuySMA") int buySMA, @RequestParam("crossOverSellSMA") int sellSMA, @RequestParam("trailingStopLossPercent") float trailingStopLoss , @RequestParam("increasingTargetPercent") float increasingTarget) throws Exception {
        return algoTradingService.goldenCrossOverStrategy(perTradeAmount, buySMA,sellSMA, stopLossPercent, targetPercent, trailingStopLoss, increasingTarget);
    }
}
