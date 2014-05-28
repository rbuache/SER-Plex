package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import xml.XmlParse;
import launcher.Launcher;

@SuppressWarnings("serial")
public class Main extends JFrame{
	public Main() {

		this.setSize(500, 100);
		this.setTitle("Office internationnal des films");

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height
				/ 2 - this.getSize().height / 2);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton notify = new JButton("Notify admin");
		JButton checkXML = new JButton("Check XML");
		JPanel panel = new JPanel();
		panel.add(notify);
		panel.add(checkXML);
		this.add(panel);
		
		notify.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(XmlParse.verify()){
					Launcher.rmi.signalAdmin();
				}
				else{
					JOptionPane.showMessageDialog(Launcher.gui, "Parse error in database!","Info", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		checkXML.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!XmlParse.verify()){
					JOptionPane.showMessageDialog(Launcher.gui, "Parse error!","Info", JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					JOptionPane.showMessageDialog(Launcher.gui, "Files ok!","Info", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		this.setVisible(true);
	}

}
