package supersimplestock;

import java.util.List;
import java.time.Instant;

public class StockIndexes 
{

	public static double computeGBCE(List<Stock> stocks) throws NoStockPriceException
	{
		
		long prod=1;
		Instant now=Instant.now();
		for(Stock stock:stocks)
			prod*=stock.getStockPrice(now);
		
		int n=stocks.size();		
		return Math.pow(prod, 1.0/n);
	}

}
