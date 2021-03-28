# AlgoTrading

Creating a service to utilize technical indictors create algortihms to buy and sell stocks and backtest the same on historical data.

Have created several API's to fetch data.
You can take a look at StockInfoLoader for populating the db.

Fetch Basic Info APIs

![image](https://user-images.githubusercontent.com/33578985/112748710-32556880-8fdb-11eb-883b-85304cb224d5.png)


![image](https://user-images.githubusercontent.com/33578985/112748720-4c8f4680-8fdb-11eb-9241-be8db32da7f9.png)


Golden CrossOver Strategy:

Have obtained an accuracy of over 60 and Average ROE per trade = 7.8 %
Have given user the option to add trailing stoploss once target is hit and also increase the subsequent target.

User can also enter amount to Invest for each trade.

Buying Condition: When the Stock Crosses x day SMA 
Selling Condition: When Stock Reaches y day SMA OR StopLoss Triggered OR Target Percent Achieved.

![image](https://user-images.githubusercontent.com/33578985/112748803-f2db4c00-8fdb-11eb-96df-dbdd5bddb973.png)
