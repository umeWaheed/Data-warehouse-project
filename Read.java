import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.ArrayList;


//***files to download***
//apache poi-3.15 

// **********to compile in cmd************
//javac -cp ".;C:\Users\ACER\Documents\semester6\data warehousing\project\poi-3.15\*;C:\Users\ACER\Documents\semester6\data warehousing\project\poi-3.15\ooxml-lib\*;C:\Users\ACER\Documents\semester6\data warehousing\project\poi-3.15\lib\*" Read.java

//***********TO RUN IN JAVA***************
//java -cp ".;C:\Users\ACER\Documents\semester6\data warehousing\project\poi-3.15\*;C:\Users\ACER\Documents\semester6\data warehousing\project\poi-3.15\ooxml-lib\*;C:\Users\ACER\Documents\semester6\data warehousing\project\poi-3.15\lib\*" Read

//works for excel 2003/2007 format
//import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
/*
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
*/
public class Read{
	 static ArrayList<XSSFCell> rowData;
	 
	public static Object getCellValue(XSSFCell cell) {
			switch (cell.getCellType()) {
			case XSSFCell.CELL_TYPE_STRING:
				return "'"+cell.getStringCellValue()+"'";
		 
			case XSSFCell.CELL_TYPE_BOOLEAN:
				return cell.getBooleanCellValue();
		 
			case XSSFCell.CELL_TYPE_NUMERIC:
				return cell.getNumericCellValue();
				
			case XSSFCell.CELL_TYPE_BLANK:
				return ' ';
		
			case XSSFCell.CELL_TYPE_FORMULA:
               switch(cell.getCachedFormulaResultType()) {
					 case XSSFCell.CELL_TYPE_NUMERIC:
					    return cell.getNumericCellValue();
					  
					  case XSSFCell.CELL_TYPE_STRING:
						return "'"+cell.getRichStringCellValue()+"'";
						 
				}
			}
			return null;
	}

	 public static void readFile(String name,int index){
		 //find the class that is in use
		 
		 switch(index){
			 /*case 0:
			 District.createTable();
			 break;
			 
			 case 1:
			 Client.createTable();
			 break;
			 
			 case 2:
			 Account.createTable();*/
			 
			 case 3:
			 Transaction.createTable();
			 break;
		 }
		 /*switch(index){	
			 case 0:
			 District.createTable();
			 break;
			 
			 case 1:
			 Account.createTable();
			 break;
			  case 2:
			 Client.createTable();
			 break;
			 
			 case 3:
			 Transaction.createTable();
			 break;
			 
			 case 7:
			 Loan.createTable();
			 break;
			 
			 case 8:
			 Disp.createTable();
			 break;
			 
			 case 9:
			 Card.createTable();
			 break;
			 
			 case 10:
			 Order.createTable();
			 break;
			 
			 default:
			 break;
			}*/
		 
		 try{
		
		System.out.println(name);
        String excelFilePath = "C:\\Users\\ACER\\Documents\\semester6\\data warehousing\\project\\excel\\"+name;
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet firstSheet = workbook.getSheetAt(0);
        Iterator iterator = firstSheet.iterator();
         
        while (iterator.hasNext()) {
			rowData = new ArrayList<XSSFCell>();
            XSSFRow nextRow = (XSSFRow)iterator.next();
		
            Iterator cellIterator = nextRow.cellIterator();
             
            while (cellIterator.hasNext()) {
                XSSFCell cell = (XSSFCell)cellIterator.next();
				rowData.add(cell);
                //System.out.print(" - ");
            }
			
			// add records except the first row
			if (nextRow.getRowNum()!=0){
				switch(index){
					/*case 0:
					District.addRow(rowData);
				    break;
					
					case 1:
					Client.addRow(rowData);
				    break;
					
					case 2:
					Account.addRow(rowData);
				    break;*/
					
					case 3:
					case 4:
					case 5:
					case 6:
					Transaction.addRow(rowData);
					break;

				}
			/*switch(index){
				case 0:
				District.addRow(rowData);
				break;
				
				case 1:
				Account.addRow(rowData);
				break;
				case 2:
				Client.addRow(rowData);
				break;
				
				case 3:
				case 4:
				case 5:
				case 6:
				Transaction.addRow(rowData);
				break;
				
				case 7:
				Loan.addRow(rowData);
				break;
				
				
				case 8:
				Disp.addRow(rowData);
				break;
				
				case 9:
				Card.addRow(rowData);
				break;
				
				case 10:
				Order.addRow(rowData);
				break;
			}*/
			}
            //System.out.println();
        }
		System.out.println("done");
        workbook.close();
        inputStream.close();
    }
		 catch(Exception e){
			 System.out.println(e.getMessage());
		 }
	}
	 }