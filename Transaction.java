import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class Transaction{
	static int id=1;
    public Transaction() {
    }
	 public static void createTable(){
	try{
	String q = "create table fact_trans (trans_key int primary key identity(1,1), trans_id int ,account_id int foreign key references dim_account(account_id),date int foreign key references date_dim(datekey),client_id int foreign key references dim_client(client_id), dist_id int foreign key references dim_dist(A1),type varchar(255),amount int,balance int)";
	//for erd
	//String q = "create table trans (trans_id int primary key not null, account_id int foreign key references account(account_id),date int, client_id int , dist_id int, type varchar(255), operation varchar(255),amount int, balance int, k_symbol varchar(255), bank varchar(255),account int)";
	Connect.run(q);
		}
	catch(Exception ex){
		System.out.println(ex.getMessage());
		}
	}
	
 public static void dropTable()throws SQLException{
		String q = "drop table fact_trans";
		Connect.run(q);
	}
	public static void addRow(ArrayList<XSSFCell> list){
		
		String insert="insert into fact_trans(trans_id,account_id,date,client_id,dist_id,type,amount,balance) values (";
		//insert = insert + " values ("+id+","+acc+","+date+","+client+","+dist+","+Read.getCellValue(list.get(7))+","+Read.getCellValue(list.get(8))+")";
        //insert = insert+Read.getCellValue(list.get(0))+","+Read.getCellValue(list.get(7))+","+Read.getCellValue(list.get(8));
		/*insert = insert+ " from db.dbo.trans t left join star.dbo.dim_account a on t.account_id=a.account_id";
		insert = insert + " left join star.dbo.date_dim d on t.date=d.datekey";
		insert = insert + " left join star.dbo.dim_client c on t.client_id=c.client_id";
		insert = insert + " left join star.dbo.dim_dist ds on t.dist_id=ds.A1";*/
		
		//String insert = "insert into trans values (";
		for (int j=0 ; j<=5 ; j++){
			if (j!=2)
			insert = insert+Read.getCellValue(list.get(j))+",";
			else
				insert = insert+Read.getCellValue(list.get(j))+",";
		}
		insert = insert+ Read.getCellValue(list.get(7))+","+Read.getCellValue(list.get(8))+")";
		//insert = insert+Read.getCellValue(list.get(list.size()-1))+")"; //last element*/
		
		//id++;
		//System.out.println(insert);
		Connect.run(insert); 
	}
}