import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

//javac -cp ".;C:\Users\ACER\Documents\semester6\data warehousing\project\poi-3.15\*;C:\Users\ACER\Documents\semester6\data warehousing\project\poi-3.15\ooxml-lib\*;C:\Users\ACER\Documents\semester6\data warehousing\project\poi-3.15\lib\*;C:\Program Files\Microsoft JDBC Driver 6.0 for SQL Server\sqljdbc_6.0\enu\jre8\sqljdbc42.jar" Gui.java

//java -cp ".;C:\Users\ACER\Documents\semester6\data warehousing\project\poi-3.15\*;C:\Users\ACER\Documents\semester6\data warehousing\project\poi-3.15\ooxml-lib\*;C:\Users\ACER\Documents\semester6\data warehousing\project\poi-3.15\lib\*;C:\Program Files\Microsoft JDBC Driver 6.0 for SQL Server\sqljdbc_6.0\enu\jre8\sqljdbc42.jar" Gui
public class Gui implements ActionListener
{
	JButton b1,b2,b3;
public void ingui(){
	JFrame f=new JFrame();
	f.setLayout(new GridLayout(3,3,4,5));
	ImageIcon icon=new ImageIcon("images/images.png");
	JLabel lbl=new JLabel("",JLabel.CENTER); 
    b1=new JButton("");
   b1.setIcon(new ImageIcon("images/a.png"));
   b1.setBounds(100,100,90, 50);  
   b2=new JButton("");
   b2.setIcon(new ImageIcon("images/b.png"));
    b2.setSize(3,2);
	lbl.setIcon(icon);
	f.add(lbl);
	f.add(b1);
	f.add(b2);
	b1.addActionListener(this);
	b2.addActionListener(this);
	
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f.setSize(700,700);
	f.setLocationRelativeTo(null);
	f.setVisible(true);
	
}

public void actionPerformed(ActionEvent e){
	try{
	if (e.getSource()==b1){
	//String files[] ={"district.xlsx","account.xlsx","client.xlsx","trans1.xlsx","trans2.xlsx","trans3.xlsx","trans4.xlsx","loan.xlsx","disp.xlsx","card.xlsx","order.xlsx"};
	String files[]= {"district.xlsx","client.xlsx","dimAcc.xlsx","trans1.xlsx","trans2.xlsx","trans3.xlsx","trans4.xlsx"};
	for (int i=0 ; i<files.length ; i++){
		Read.readFile(files[i],i);
	}
	}
	if (e.getSource()==b2){ //dont change the sequence
		/*Order.dropTable();
		Card.dropTable();
		Disp.dropTable();
		Loan.dropTable();*/
		Transaction.dropTable();
		//Client.dropTable();
		//System.out.println("deleted client");
		//Account.dropTable();
		//District.dropTable();
		System.out.println("deleted district");
	}
	}
	catch(Exception ex){
		System.out.print(ex.getMessage());
	}
	
}
public static void main(String[] args){
	Connect.makeConnection();
	Gui g=new Gui();
	g.ingui();
}
}