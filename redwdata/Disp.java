public class Disp {
    private int disp_id;
	private int client_id;
	private int account_id;
    private String type;
 
    public Disp() {
    }
 
    public String toString() {
        return String.format("%d - %d - %d -%s",disp_id,client_id,account_id,type);
    }
 	public int getDisp_id() {
        return disp_id;
    }

    public void setDisp_id(int disp_id) {
        this.disp_id = disp_id;
    }
    
	public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }
	public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }
		public String setType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}