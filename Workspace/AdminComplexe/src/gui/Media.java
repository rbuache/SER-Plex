package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import jdom.util.IO;

import org.jdom2.Document;

import launcher.Launcher;

@SuppressWarnings("serial")
public class Media extends JPanel{

	JButton generer = new JButton("Generer media file");
	JButton office = new JButton("Recupère office xml");
	public Media(){
		add(generer);
		add(office);
		
		generer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!Launcher.media.transform()){
					JOptionPane.showMessageDialog(Launcher.gui, "Error during transform","Crash", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		office.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Document[] doc = null;
				try {
					doc = (Document[])Launcher.remote.getXml();
				} catch (RemoteException e1) {
					JOptionPane.showMessageDialog(Launcher.gui, "Error remote method","Crash", JOptionPane.ERROR_MESSAGE);
				}
				if(doc == null){
					JOptionPane.showMessageDialog(Launcher.gui, "Error on office side","Crash", JOptionPane.ERROR_MESSAGE);
				}
				else{
					try {
						IO.save(doc[0], Launcher.databasePath + "/actor.xml");
						IO.save(doc[1], Launcher.databasePath + "/critic.xml");
						IO.save(doc[2], Launcher.databasePath + "/genre.xml");
						IO.save(doc[3], Launcher.databasePath + "/movie.xml");
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(Launcher.gui, "Error while saving","Crash", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}
}
