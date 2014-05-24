package gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import launcher.Launcher;


@SuppressWarnings("serial")
public class Main extends JFrame{

	
	public Main(){
		
		this.setSize(500, 500);
		this.setTitle("Administration du complexe");
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		
		//Dialog : XML database path
		String s = (String)JOptionPane.showInputDialog(
				   this,
				   "Choose the database path :",
				   "XML database path",
				   JOptionPane.QUESTION_MESSAGE,
				   null,
				   null,
				   "../../XML");

		if(s == null)
			System.exit(0);
		else{
			Launcher.databasePath = s;
			this.setVisible(true);
		}
		
	}
	
}
