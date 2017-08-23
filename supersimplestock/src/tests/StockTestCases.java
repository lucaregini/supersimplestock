package tests;

import static org.junit.Assert.*;
import  java.time.Instant;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import supersimplestock.NoStockPriceException;
import supersimplestock.PreferredStock;
import supersimplestock.Stock;
import supersimplestock.StockIndexes;
import supersimplestock.Trade.TradeOperation;

public class StockTestCases {

	
	@Test 
	public void testDividend() 
	{
		Stock stock = new Stock("commonDividend", 5,  100);		
		float dividend=stock.getDividendYeld(50);
		float delta=dividend-0.1f;
		assertTrue(Math.abs(delta)<0.0001f);
	}

	@Test
	public void testPreferredDividend() 
	{
		Stock stock = new PreferredStock("preferredDividend", 5, 0.02f, 100);
		float dividend=stock.getDividendYeld(50);
		float delta=dividend-0.04f;
		assertTrue(Math.abs(delta)<0.0001f);
	}
	
	@Test
	public void testPERatio() 
	{
		Stock stock = new PreferredStock("PERatio", 5, 0.02f, 100);
		float ratio=stock.getPERatio(20);
		float delta=ratio-4;
		assertTrue(Math.abs(delta)<0.0001f);
	}
	
	
	@Test
	public void testPrice() throws NoStockPriceException 
	{
		Stock stock = new Stock("testPrice", 5,  100);
		Instant now=Instant.now();
		
		stock.recordTrade(now, 5, TradeOperation.buy, 10);
		
		Instant tooOld=now.minusSeconds(20*60);
		stock.recordTrade(tooOld, 20, TradeOperation.buy, 10000);
		
		stock.recordTrade(now, 30, TradeOperation.buy, 60);

		int price=stock.getStockPrice(now);
		assertTrue(price==53);
				
	}
	
	

	@Test
	public void testGBCE() throws NoStockPriceException 
	{
		Instant now=Instant.now();
		Stock stock1 = new Stock("stock1", 5,  100);
		Stock stock2 = new Stock("stock2", 5,  100);
		Stock stock3 = new Stock("stock3", 5,  100);
		
		stock1.recordTrade(now, 1, TradeOperation.buy, 1);
		stock2.recordTrade(now, 1, TradeOperation.buy, 2);
		stock3.recordTrade(now, 1, TradeOperation.buy, 3);
		
		List<Stock> stocks=Arrays.asList(stock1,stock2,stock3);
				
		double gbce=StockIndexes.computeGBCE(stocks);
				
		double delta=gbce-1.81712;
		assertTrue(Math.abs(delta)<0.00001);
		
	}
	
	
	
}
