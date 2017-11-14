import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class District{
    
    public District() {
    }
 
	public static void createTable(){
	try{
	String q = "create table dim_dist (A1 int not null primary key,A2 varchar(255),A3 varchar(255),A4 int,A5 int,A6 int,A7 int,A8 int,A9 int,A10 float,A11 int,A12 float,A13 float,A14 int,A15 int,A16 int)";
	Connect.run(q);
		}
	catch(Exception ex){
		System.out.println(ex.getMessage());
		}
	}
	
	public static void dropTable()throws SQLException{
		String q = "drop table dim_dist";
		Connect.run(q);
	}
	
	public static void addRow(ArrayList<XSSFCell> list){
		String insert="insert into dim_dist values(";
		for (int j=0 ; j<list.size()-1 ; j++){
			insert = insert+Read.getCellValue(list.get(j))+",";
		}
		insert = insert+Read.getCellValue(list.get(list.size()-1))+")"; //last element
		//System.out.println(insert);
		Connect.run(insert);
	} 
   
}