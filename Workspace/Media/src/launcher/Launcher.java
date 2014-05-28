package launcher;

import gui.Main;
import interfaces.IServerAdmin;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

public class Launcher {
	public static Main gui;
	
	public static void main(String[] args) {
		IServerAdmin remoteService;
		//we connect to server
		try {
			remoteService = (IServerAdmin) Naming.lookup("//localhost:9998/RmiServiceAdmin");
			
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			JOptionPane.showMessageDialog(Launcher.gui, "Error connection remote","Crash", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		try {
			gui = new Main(remoteService.getHoraire());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(Launcher.gui, "Error during analysis of content","Crash", JOptionPane.ERROR_MESSAGE);
		}
	}

}
