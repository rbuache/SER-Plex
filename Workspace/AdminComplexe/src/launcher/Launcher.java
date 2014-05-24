package launcher;

import xml.XmlLocation;
import gui.Main;

public class Launcher {

	public static Main gui;
	
	public static String databasePath;
	public static XmlLocation location;
	
	public static void main(String[] args){
		
		gui = new Main();

	}
	
	public static void initXML(){
		location = new XmlLocation(databasePath);
	}
}