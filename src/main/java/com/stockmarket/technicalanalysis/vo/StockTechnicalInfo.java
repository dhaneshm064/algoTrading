package com.stockmarket.technicalanalysis.vo;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class StockTechnicalInfo {

    public String Symbol;
    public String Name;
    public Timestamp Date;
    public StockBasicInfo stockBasicInfo;
    public HashMap<Integer,Float> SimpleMovingAverage;

}
