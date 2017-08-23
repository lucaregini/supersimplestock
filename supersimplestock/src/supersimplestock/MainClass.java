package supersimplestock;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import supersimplestock.Trade.TradeOperation;

public class MainClass {
	
	public static List<Stock> getStocks()
	{
		List<Stock> stocks=Arrays.asList(
				new Stock("TEA",0,100),
				new Stock("POP",8,100),
				new Stock("ALE",23,60),
				new PreferredStock("GIN",8,0.02f,100),
				new Stock("JOE",13,250)
				);
		
		return stocks;
	}

	public static void main(String[] args)
	{		
		
		List<Stock> stocks=MainClass.getStocks();
		Instant now=Instant.now();
		for(Stock s:stocks)		
		{
			s.recordTrade(now,1+(int)(Math.random()*10),TradeOperation.buy, 1+(int)(Math.random()*10));
		}
		
		try
		{
			System.out.println("GBCE is: "+StockIndexes.computeGBCE(stocks));
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

}
