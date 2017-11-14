public class Loan{
    private int loan_id;
    private int account_id;
    private int date;
	private int amount;
	private int duration;
	private int payments;
	private String status;
	
    public Loan() {
    }
 
    public String toString() {
        return String.format("%d -%d -%d -%d - %d - %d -%s ",loan_id,account_id,date,amount,duration,payments,status);
    }
 
    public int getLoan_id() {
        return loan_id;
    }

    public void setLoan_id(int loan_id) {
        this.loan_id = loan_id;
    }
	public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }
	
	public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date= date;
    }
	
	public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount= amount;
    }
	
	public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration= duration;
    }
	public int getPayments() {
        return payments;
    }

    public void setPayments(int payments) {
        this.payments= payments;
    }
	public String setStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}