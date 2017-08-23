package supersimplestock;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;

import supersimplestock.Trade.TradeOperation;

/**
 * 
 * @author luca.regini
 * Base class for representing a stock. It covers all the needs of a Common Stock
 *
 */

public class Stock 
{	
	protected String symbol;
	protected int lastDividend;
	protected int parValue;
	protected ArrayList<Trade> trades;
	
	
	public String getStockSymbol()
	{
		return symbol;
	}
	public Stock(String symbol,int lastDividend,int parValue)
	
	{
		this.symbol=symbol;	
		this.lastDividend=lastDividend;
		this.parValue=parValue;	
		trades=new ArrayList<Trade>();
	}
	

	public void recordTrade(Instant timestamp,int nshares,TradeOperation op,int price)
	{
		trades.add(new Trade(timestamp,nshares,op,price));
	}
	
	
	/**
	 * Assuming prices are in pennies we return a price in pennies rounding arithmetically 
	 * @return rounded price in pennies derived from the trades of the last 15 minutes from now
	 * 
	 * @throws NoStockPriceException if there are no trades in the last 15 minutes.
	 */
	public int getStockPrice(Instant referenceTime) throws NoStockPriceException
	{
		Collections.sort(trades);
		long denominator=0;
		long numerator=0;
		
		long delta;
		for(int i=0;i<trades.size();i++)
		{
			Trade trade=trades.get(i);
			delta=ChronoUnit.MINUTES.between(trade.getTimestamp(),referenceTime);
			assert(delta>=0);
			if(delta>15)
				break;
			
			numerator+=trade.getPrice()*trade.getNshares();
			denominator+=trade.getNshares();
		}
				
		if(denominator==0)
			throw new NoStockPriceException(this);
		
		float ratio=(float)numerator/(float)denominator;		
		return Math.round(ratio);
				
	}
	
	public float getDividendYeld(int tickerPrice)
	{
		return (float)lastDividend/(float)tickerPrice;		
	}

	public float getPERatio(float tickerPrice)
	{
		return (float)tickerPrice/(float)lastDividend;
	}
}

