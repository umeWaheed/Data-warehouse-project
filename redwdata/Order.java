public class Order{
    private int order_id;
    private int account_id;
    private String bank_to;
	private int account_to;
	private int amount;
	private String k_symbol;
	
	
    public Order() {
    }
 
    public String toString() {
        return String.format("%d -%d -%s -%d - %d -%s ",order_id,account_id,bank_to,account_to,amount,k_symbol);
    }
 
    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
	public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }
	
	public String setBank_to() {
        return bank_to;
    }

    public void setBank_to(String bank_to) {
        this.bank_to= bank_to;
    }	
	public int setAccount_to() {
        return account_to;
    }

    public void setAccount_to(int account_to) {
        this.account_to= account_to;
    }
	
	public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount= amount;
    }
	
	
	public String setK_symbol() {
        return k_symbol;
    }

    public void setK_symbol(String k_symbol) {
        this.k_symbol = k_symbol;
    }
}