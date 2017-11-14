import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class Client {
    public Client() {
    }
 
	public static void createTable(){
	try{
	String q = "create table dim_client (client_id int primary key not null ,gender varchar(255))";
	Connect.run(q);
		}
	catch(Exception ex){
		System.out.println(ex.getMessage());
		}
	}
	
	public static void dropTable()throws SQLException{
		String q = "drop table dim_client";
		Connect.run(q);
	}
	
	public static void addRow(ArrayList<XSSFCell> list){ //only first and last value to be inserted
		String insert="insert into dim_client values("+Read.getCellValue(list.get(0))+","+Read.getCellValue(list.get(5))+")";
		//for (int j=0 ; j<list.size()-1 ; j++){
			//insert = insert+Read.getCellValue(list.get(0))+","+Read.getCellValue(list.get(5))+")";
		//}
		//insert = insert+Read.getCellValue(list.get(5))+")"; //last element
		//System.out.println(insert);
		Connect.run(insert);
	} 
}