public class Account {
    private int account_id;
    private int district_id;
    private String frequency;
	private int date;
 
    public Account() {
    }
 
    public String toString() {
        return String.format("%d - %d - %s -%d",account_id,district_id, frequency, date);
    }
 
    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }
	public int getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(int district_id) {
        this.district_id = district_id;
    }
	public String setFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
	public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
}