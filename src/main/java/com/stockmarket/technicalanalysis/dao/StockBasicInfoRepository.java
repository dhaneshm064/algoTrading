package com.stockmarket.technicalanalysis.dao;


import com.stockmarket.technicalanalysis.vo.StockBasicInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class StockBasicInfoRepository{

    @Autowired
    DataSource datasource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<StockBasicInfo> getStockBasicInfo(String stockSymbol) throws SQLException {

        String query = "SELECT * FROM STOCKBASICINFO WHERE SYMBOL = '"+stockSymbol+"';";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(StockBasicInfo.class));

    }

    public String getStockBasicInfo(String stockSymbol, Timestamp date) {

        String query = "SELECT * FROM STOCKBASICINFO WHERE SYMBOL = '"+stockSymbol+"' and date = '"+date+"';";
        List <StockBasicInfo> stockBasicInfos =jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(StockBasicInfo.class));
        if(stockBasicInfos == null || stockBasicInfos.size() == 0)
        {
            return "Could not find Data for Stock Info for Symbol "+stockSymbol+" and date "+date;
        }
        return stockBasicInfos.get(0).toString();
    }

    public List<StockBasicInfo> getStockBasicInfo(String stockSymbol, Timestamp startDate, Timestamp endDate){

        String query =  "SELECT * FROM STOCKBASICINFO WHERE SYMBOL = '"+stockSymbol+"' and date >= '"+startDate+"' and " +
                "date<='"+endDate+"';";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(StockBasicInfo.class));
    }


}
