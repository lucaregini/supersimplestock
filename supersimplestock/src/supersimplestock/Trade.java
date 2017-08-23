package supersimplestock;


import java.time.Instant;

public class Trade implements Comparable<Trade>
{

	@Override
	public int compareTo(Trade trade) {
		// TODO Auto-generated method stub
		return -timestamp.compareTo(trade.timestamp);
	}
	
	public Trade(Instant timestamp,int nshares,TradeOperation op,int price)
	{
		this.timestamp=timestamp;
		this.nshares=nshares;
		this.operation=op;
		this.price=price;
		
	}
	
	public Instant getTimestamp() {
		return timestamp;
	}
	
	public int getNshares() {
		return nshares;
	}

	
	public TradeOperation getOperation() {
		return operation;
	}

	
	public int getPrice() {
		return price;
	}

	
	public enum TradeOperation {buy,sell};
	
	private Instant timestamp;
	private int nshares;
	private TradeOperation operation;
	private int price;
	

}