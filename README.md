# AlgoTrading

Creating a service to utilize technical indicators to create algortihms to buy and sell stocks and backtest the same on historical data of 10 years of Top 100 Nifty Stocks.

StockInfoLoader in my set of Repositories can be used to populate more data of more Stocks and timeframe to test.

**Fetch Basic Info APIs**

**/StockBasicInfo/getStockDetail**

To Fetch Stock details of a particular date. <br />
**Params :** Stock Symbol, Date ddMMYYYY

![StockDetail](https://user-images.githubusercontent.com/33578985/112749019-3c786680-8fdd-11eb-810e-c3f3c3cd69a5.JPG)

**/StockBasicInfo/getStockDetailList**

To Fetch Stock details for a particular range of dates to analyse. <br />
**Params :** Stock Symbol, Start Date ddmmYYYY, End Date ddMMYYYY

![StockDataList](https://user-images.githubusercontent.com/33578985/112749003-33879500-8fdd-11eb-8678-cf4ec9c369fd.JPG)

**/StockTechnicalInfo/CalculateSMA**

To calculate Simple Moving Average. <br />
**Params :** StockSymbol, timePeriod for eg: 10 day moving Average

![SMA](https://user-images.githubusercontent.com/33578985/112826779-11f4df00-90ab-11eb-8da2-130b2bf539a6.jpg)


# ALGORITHMS: 

## Golden CrossOver Strategy:

Have obtained an accuracy of over 60 and **Average ROE per trade = 7.8 %**
User can add a trailing stoploss once target is hit and also increase the subsequent target value once the target is hit by specifing the increase in target Percent.

User can also enter amount to Invest for each trade.

**Buying Condition:** When the Stock Crosses x day SMA 

**Selling Condition:** When Stock Reaches y day SMA OR StopLoss Triggered OR Target Percent Achieved.

**algoTrading/goldenCrossover.<br />**<br />

**Params:**<br />
**perTradeAmount** = 10000 . <br />
**crossOverBuySMA** = To buy when Stock closes greater than x day Moving Average: specify x.<br />
**crossOverSellSMA** = To sell when a Stock closes greater than y day Moving Average specify y.<br />
**targetPercent** = To sell when Stock reaches the target Percent.<br />
**stopLossPercent** = To sell a stock when stopLoss Percent Triggered.<br />
**increasingTargetPercent** = If target Achieved, user can increase the target by a Percent: specify a.<br />
**trailingStopLossPercent** = If target Achieved , user can update a trailing stoploss by b Percent: specify b.<br />

![GoldenCrossOver](https://user-images.githubusercontent.com/33578985/112748884-75640b80-8fdc-11eb-91d9-16bbba0d81f9.JPG)

## The List of trades are written into AlgoStrategyOutput file as seen below, you can analyse the trades and try to modify the strategy or params.

![TradeList](https://user-images.githubusercontent.com/33578985/112825773-cc83e200-90a9-11eb-8c62-ab84b0c3793e.JPG)



## Will be creating API s to introduce more Technical Indicators and Algorithms to backtest
