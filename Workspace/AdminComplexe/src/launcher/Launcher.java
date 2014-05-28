package launcher;

import interfaces.IServerAdmin;
import interfaces.IServerOffice;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import rmi.RmiServer;
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
	public static IServerOffice remote;
	public static RmiServer rmi;
	
	public Launcher(){


	}
	public static void main(String[] args){
		
		gui = new Main();

		try {
			//we connect to server
			IServerOffice remoteService = (IServerOffice) Naming.lookup("//localhost:9999/RmiService");

			remoteService.addObserver(new SignalOffice());
			
			remote = remoteService;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		rmi = new RmiServer();
		
        try {
            Registry rmiRegistry = LocateRegistry.createRegistry(9998);
            IServerAdmin rmiService = (IServerAdmin) UnicastRemoteObject.exportObject(rmi, 9998);
            rmiRegistry.bind("RmiServiceAdmin", rmiService);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
	
	public static void initXML(){
		location = new XmlLocation(databasePath);
		planification = new XmlPlanification(databasePath);
		media = new XmlGenerateMedia();
	}
	

}
