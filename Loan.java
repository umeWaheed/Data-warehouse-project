import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.poi.xssf.usermodel.XSSFCell;
public class Loan{
	
    public Loan() {
    }
	 public static void createTable(){
	try{
	String q = "create table loan (loan_id int primary key not null,account_id int foreign key references account(account_id),date int,amount int,duration int, payments int,status varchar(220))";
	Connect.run(q);
		}
	catch(Exception ex){
		System.out.println(ex.getMessage());
		}
	}
	
 public static void dropTable()throws SQLException{
		String q = "drop table loan";
		Connect.run(q);
	}
	public static void addRow(ArrayList<XSSFCell> list){
		String insert="insert into loan values(";
		for (int j=0 ; j<list.size()-1 ; j++){
			insert = insert+Read.getCellValue(list.get(j))+",";
		}
		insert = insert+Read.getCellValue(list.get(list.size()-1))+")"; //last element
		//System.out.println(insert);
		Connect.run(insert);
	}
}