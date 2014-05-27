package launcher;

import java.rmi.RemoteException;

import rmi.RmiServer;
import xml.XmlGenerateMedia;
import xml.XmlLocation;
import xml.XmlPlanification;
import gui.Main;

public class Launcher {

	public static Main gui;
	
	public static String databasePath;
	public static XmlLocation location;
	public static XmlPlanification planification;
	public static XmlGenerateMedia media;
	
	public static void main(String[] args){
		
		gui = new Main();
		try {
			new RmiServer();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void initXML(){
		location = new XmlLocation(databasePath);
		planification = new XmlPlanification(databasePath);
		media = new XmlGenerateMedia();
	}
}
