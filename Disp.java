import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.poi.xssf.usermodel.XSSFCell;
public class Disp {
    public Disp() {
    }
 public static void createTable(){
	try{
	String q = "create table disp (disp_id int primary key not null,client_id int foreign key references client (client_id),account_id int foreign key references account (account_id),type  varchar(220))";
	Connect.run(q);
		}
	catch(Exception ex){
		System.out.println(ex.getMessage());
		}
	}
	
 public static void dropTable()throws SQLException{
		String q = "drop table disp";
		Connect.run(q);
	}
	public static void addRow(ArrayList<XSSFCell> list){
		String insert="insert into disp values(";
		for (int j=0 ; j<list.size()-1 ; j++){
			insert = insert+Read.getCellValue(list.get(j))+",";
		}
		insert = insert+Read.getCellValue(list.get(list.size()-1))+")"; //last element
		//System.out.println(insert);
		Connect.run(insert);
	} 
}