package launcher;

import interfaces.IServerOffice;

import java.rmi.Naming;
import java.rmi.RemoteException;

import rmi.RmiClient;
import xml.XmlGenerateMedia;
import xml.XmlLocation;
import xml.XmlPlanification;
import gui.Main;
import gui.SignalOffice;

public class Launcher {

	public static Main gui;
	
	public static String databasePath;
	public static XmlLocation location;
	public static XmlPlanification planification;
	public static XmlGenerateMedia media;
	
	public Launcher(){


	}
	public static void main(String[] args){
		
		gui = new Main();

//		try {
//			new RmiClient(new SignalOffice());
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		try {
			//we connect to server
			IServerOffice remoteService = (IServerOffice) Naming.lookup("//localhost:9999/RmiService");

			remoteService.addObserver(new SignalOffice());
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
//		try {
//		new RmiServer();
//	} catch (RemoteException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
	}
	
	public static void initXML(){
		location = new XmlLocation(databasePath);
		planification = new XmlPlanification(databasePath);
		media = new XmlGenerateMedia();
	}
	

}
