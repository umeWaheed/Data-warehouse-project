import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.poi.xssf.usermodel.XSSFCell;
public class Card{
 
    public Card() {
    }
 public static void createTable(){
	try{
	String q = "create table card (card_id int primary key not null,disp_id int foreign key references disp(disp_id),type varchar(220),issued int)";
	Connect.run(q);
		}
	catch(Exception ex){
		System.out.println(ex.getMessage());
		}
	}
	
 public static void dropTable()throws SQLException{
		String q = "drop table card";
		Connect.run(q);
	}
	public static void addRow(ArrayList<XSSFCell> list){
		String insert="insert into card values(";
		for (int j=0 ; j<list.size()-1 ; j++){
			insert = insert+Read.getCellValue(list.get(j))+",";
		}
		insert = insert+Read.getCellValue(list.get(list.size()-1))+")"; //last element
		//System.out.println(insert);
		Connect.run(insert);
	} 
}