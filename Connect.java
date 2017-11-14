import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import org.apache.poi.hssf.usermodel.*;
import java.util.ArrayList;


//javac -cp ".;C:\Program Files\Microsoft JDBC Driver 6.0 for SQL Server\sqljdbc_6.0\enu\jre8\sqljdbc42.jar" Connect.java

//download sql sever with tools(comes with ssms)
//download sql jdbc.jar 
//add sqljdbc_auth.dll into sys32 folder and add sqljdbc.jar to env var

public class Connect{
	public static Connection conn;
    public static void makeConnection() {
 
        conn = null;
 
        try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbURL = "jdbc:sqlserver://localhost:1433;integratedsecurity=true;databaseName=star";
            String user = "";
            String pass = "";
            conn = DriverManager.getConnection(dbURL,user,pass);
            if (conn != null) {
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
            }
 
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
    }
	
	public static void run(String q){
		try{
		Statement stmt = conn.createStatement();
		Boolean r = stmt.execute(q);
		//conn.close();
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
