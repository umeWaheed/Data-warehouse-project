public class Card{
    private int card_id;
    private int disp_id;
    private String type;
	private int issued;
 
    public Card() {
    }
 
    public String toString() {
        return String.format("%d - %d - %s -%d",card_id,disp_id, type, issued);
    }
 
    public int getCard_id() {
        return card_id;
    }

    public void setCard_id(int card_id) {
        this.card_id = card_id;
    }
	public int getDisp_id() {
        return disp_id;
    }

    public void setDisp_id(int disp_id) {
        this.disp_id = disp_id;
    }
	public String setType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
	public int getIssued() {
        return issued;
    }

    public void setIssued(int issued) {
        this.issued = issued;
    }
}