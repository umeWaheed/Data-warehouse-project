import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.poi.xssf.usermodel.XSSFCell;
public class Order{
	
	 public static void createTable(){
	try{
	String q = "create table orders (order_id int primary key not null,account_id int foreign key references account(account_id),bank_to varchar(220),account_to int,amount int,k_symbol varchar(220))";
	Connect.run(q);
		}
	catch(Exception ex){
		System.out.println(ex.getMessage());
		}
	}
	
 public static void dropTable()throws SQLException{
		String q = "drop table orders";
		Connect.run(q);
	}
	public static void addRow(ArrayList<XSSFCell> list){
		String insert="insert into orders values(";
		for (int j=0 ; j<list.size()-1 ; j++){
			insert = insert+Read.getCellValue(list.get(j))+",";
		}
		insert = insert+Read.getCellValue(list.get(list.size()-1))+")"; //last element
		//System.out.println(insert);
		Connect.run(insert);
	}
}