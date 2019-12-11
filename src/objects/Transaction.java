package objects;

public class Transaction implements Comparable<Transaction> {
	private String transactionDate;
	private String vin;
	private Person p;

	public Transaction() {
		// Default
	}

	public Transaction(String transactionDate, String vin, Person p) {
		this.transactionDate = transactionDate;
		this.vin = vin;
		this.p = p;
	}

	@Override
	public String toString() {
		return "Transaction [transactionDate=" + transactionDate + ", vin=" + vin + ", p=" + p + "]";
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Person getP() {
		return p;
	}

	public void setP(Person p) {
		this.p = p;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	@Override
	public int compareTo(Transaction o) {
		if (getTransactionDate() == null || o.getTransactionDate() == null) {
			return 0;
		}
		return getTransactionDate().compareTo(o.getTransactionDate());
	}
}
