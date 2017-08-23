package supersimplestock;

public class NoStockPriceException extends Exception
{

	public NoStockPriceException(Stock stock)
	{
		super("Price for stock "+stock.getStockSymbol()+" cannot be computed because no trades have been recorded in the last 15 minutes");
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -9202170239770648270L;
	
	
}
