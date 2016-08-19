package test;

import java.math.BigDecimal;
import java.util.Date;

public class Stock {
	private String symbol;
	private String exchange;
	private long lotSize;
	private int tickSize;
	private boolean isRestricted;
	private Date settlementDate;
	private BigDecimal price;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((exchange == null) ? 0 : exchange.hashCode());
		result = prime * result + (isRestricted ? 1231 : 1237);
		result = prime * result + (int) (lotSize ^ (lotSize >>> 32));
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((settlementDate == null) ? 0 : settlementDate.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		result = prime * result + tickSize;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		Stock other = (Stock) obj;

		return this.tickSize == other.tickSize && this.lotSize == other.lotSize
				&& this.isRestricted == other.isRestricted
				&& (this.symbol == other.symbol || (this.symbol != null && this.symbol.equals(other.symbol)))
				&& (this.exchange == other.exchange || (this.exchange != null && this.exchange.equals(other.exchange)))
				&& (this.settlementDate == other.settlementDate
						|| (this.settlementDate != null && this.settlementDate.equals(other.settlementDate)))
				&& (this.price == other.price || (this.price != null && this.price.equals(other.price)));

	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
