import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class Account {
 
    public Account() {
    }
	public static void createTable(){
	try{
	String q = "create table dim_account (account_id int primary key not null,district_id int , frequency varchar(220),open_date int,disp_id int, client_id int, disp_type varchar(255), card_id int, card_type varchar(255), card_date int)";
	Connect.run(q);
		}
	catch(Exception ex){
		System.out.println(ex.getMessage());
		}
	}
	
 public static void dropTable()throws SQLException{
		String q = "drop table dim_account";
		Connect.run(q);
	}
	public static void addRow(ArrayList<XSSFCell> list){
		String insert="insert into dim_account values(";
		for (int j=0 ; j<list.size()-1 ; j++){
			insert = insert+Read.getCellValue(list.get(j))+",";
		}
		insert = insert+Read.getCellValue(list.get(list.size()-1))+")"; //last element
		//System.out.println(insert);
		Connect.run(insert);
	} 
}