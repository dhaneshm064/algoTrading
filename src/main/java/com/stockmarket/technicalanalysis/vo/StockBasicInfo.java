package com.stockmarket.technicalanalysis.vo;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Getter
@Setter
public class StockBasicInfo {

    public String Symbol;

    @Override
    public String toString() {
        return "StockBasicInfo{" +
                "Symbol='" + Symbol + '\'' +
                ", Name='" + Name + '\'' +
                ", Low=" + Low +
                ", High=" + High +
                ", Volume=" + Volume +
                ", DeliveryPercent=" + DeliveryPercent +
                ", Date=" + Date +
                ", PrevClose=" + PrevClose +
                ", Open=" + Open +
                ", Close=" + Close +
                ", VWAP=" + VWAP +
                '}';
    }

    public String Name;
    public float Low;
    public float High;
    public long Volume;
    public float DeliveryPercent;
    public Timestamp Date;
    public float PrevClose;
    public float Open;
    public float Close;
    public float VWAP;

    public StockBasicInfo(){

    }


    public StockBasicInfo(String symbol, String name, float low, float high, long volume, float deliveryPercent, Timestamp date, float prevClose, float open, float close, float VWAP) {
        Symbol = symbol;
        Name = name;
        Low = low;
        High = high;
        Volume = volume;
        DeliveryPercent = deliveryPercent;
        Date = date;
        PrevClose = prevClose;
        Open = open;
        Close = close;
        this.VWAP = VWAP;
    }

}
