public class Transaction{
    private int trans_id;
    private int account_id;
	private int date;
	private String type;
    private String operation;
	private int amount;
    private int balance;
	private String k_symbol;
	private String bank;
	private int account;
	
	
    public Transaction() {
    }
 
    public String toString() {
        return String.format("%d -%d -%d -%s - %s -%d - %d - %s -%s -%d",trans_id,account_id,date,operation,account,balance,k_symbol,bank,account);
    }
	
    public int getTrans_id() {
        return trans_id;
    }

    public void setTrans_id(int trans_id) {
        this.trans_id = trans_id;
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
	public String setType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
		public String setOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
	
	public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount= amount;
    }
	
	public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance= balance;
    }
	
	public String setK_symbol() {
        return k_symbol;
    }

    public void setK_symbol(String k_symbol) {
        this.k_symbol = k_symbol;
    }
    public String setBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }
	public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account= account;
    }
}