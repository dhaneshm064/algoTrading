package com.stockmarket.technicalanalysis.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StockTradeInfo {

    public String stockSymbol;
    public Boolean isTradeActive;
    public Trade trade;
}
