package gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Main extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Main(Document doc){
		
		this.setSize(500, 500);
		this.setTitle("Media : horaire du jour");
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JEditorPane edit = new JEditorPane();
		edit.setEditable(false);
		
		Format format = Format.getPrettyFormat();
        XMLOutputter outputter = new XMLOutputter(format);
       
		edit.setText(outputter.outputString(doc));
		
		
		JScrollPane pane = new JScrollPane(edit);
		add(pane);
		
		this.setVisible(true);
	}
}
