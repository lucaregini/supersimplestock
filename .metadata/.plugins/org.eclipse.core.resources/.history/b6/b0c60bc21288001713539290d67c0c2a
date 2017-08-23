package supersimplestock;

/**
 * 
 * @author luca.regini
 * Preferred stock differs from common stock for one additional field and a different way of 
 * computing the dividend
 */
public class PreferredStock  extends Stock{

private float fixedDividend;
public PreferredStock(String symbol,int lastDividend,float fixedDividend,int parValue)
	
	{
	super(symbol,lastDividend,parValue);
	this.fixedDividend=fixedDividend;
		
	}

	public float getDividendYeld(int tickerPrice)
	{
		return (fixedDividend*parValue)/tickerPrice;				
	}
	
	
	

}
