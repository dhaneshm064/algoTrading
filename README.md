# AlgoTrading

Creating a service to utilize technical indictors create algortihms to buy and sell stocks and backtest the same on historical data.

Have created several API's to fetch data.
You can take a look at StockInfoLoader for populating the db.

Fetch Basic Info APIs


![StockDetail](https://user-images.githubusercontent.com/33578985/112749019-3c786680-8fdd-11eb-810e-c3f3c3cd69a5.JPG)


![StockDataList](https://user-images.githubusercontent.com/33578985/112749003-33879500-8fdd-11eb-8678-cf4ec9c369fd.JPG)


# Golden CrossOver Strategy:

Have obtained an accuracy of over 60 and Average ROE per trade = 7.8 %
User can add a trailing stoploss once target is hit and also increase the subsequent target value once the target is hit by specifing the increase in target Percent.

User can also enter amount to Invest for each trade.

Buying Condition: When the Stock Crosses x day SMA 

Selling Condition: When Stock Reaches y day SMA OR StopLoss Triggered OR Target Percent Achieved.

![GoldenCrossOver](https://user-images.githubusercontent.com/33578985/112748884-75640b80-8fdc-11eb-91d9-16bbba0d81f9.JPG)
