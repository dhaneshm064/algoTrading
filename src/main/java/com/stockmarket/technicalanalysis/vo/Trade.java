package com.stockmarket.technicalanalysis.vo;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class Trade {
    public String stockSymbol;
    public float buyingPrice;
    public Timestamp buyingDate;
    public float sellingPrice;
    public Timestamp sellingDate;
    public float quantity;
    public float PnL;
    public float ReturnOnEquity;
    public Boolean isTradeActive;
    public float stoploss;
    public float target;


    @Override
    public String toString() {
        return "Trade{" +
                "stockSymbol='" + stockSymbol + '\'' +
                ", buyingPrice=" + buyingPrice +
                ", buyingDate=" + buyingDate +
                ", sellingPrice=" + sellingPrice +
                ", sellingDate=" + sellingDate +
                ", quantity=" + quantity +
                ", PnL=" + PnL +
                ", ReturnOnEquity ="+ReturnOnEquity+
                ", isTradeActive=" + isTradeActive +
                '}';
    }
     public String toCSVWrite() {
        return   stockSymbol  +
                 "," + buyingPrice +
                 "," + buyingDate +
                 "," + sellingPrice +
                 "," + sellingDate +
                 "," + quantity +
                 "," + PnL +
                 "," + ReturnOnEquity +
                 "," + isTradeActive ;
     }

     public String getCSVHeader(){
        return  "stockSymbol"+
                ", buyingPrice"+
                ", buyingDate" +
                ", sellingPrice"  +
                ", sellingDate" +
                ", quantity" +
                ", PnL" +
                ", ReturnOnEquity"+
                ", isTradeActive" ;
     }
}
