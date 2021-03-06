package xml;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import jdom.util.IO;
import launcher.Launcher;

import org.jdom2.Document;
import org.jdom2.Element;

public class XmlLocation {

	private Document doc;
	private String file;
	
	public XmlLocation(String path){
		
		try {
			file = path + "/location.xml";
			doc = IO.getDocument(file);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(Launcher.gui, "Path to XML Location not valid\n" + file,"Crash", JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		}
		
	}

	private void save(){
		try {
			IO.save(doc, file);
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(Launcher.gui, "Error during update file","Crash", JOptionPane.ERROR_MESSAGE);
		}
	}

	public Object[][] getLocations(){
		int count = 0;
		
		Element root = doc.getRootElement();
		List<Element> loc = root.getChildren("location");
		Iterator<Element> i = loc.iterator();
		
		Object[][] result = new Object[loc.size()][];
		while(i.hasNext()){
			Element e = (Element)i.next();
			result[count] = new Object[]{
						e.getChild("name").getText(),
						e.getChild("address").getText(),
						e.getChild("tel").getText(),
						e
						};
			count++;
		}
		return result;
	}
	public void updateLocation(Element e,String[] arg){
		e.getChild("name").setText(arg[0]);
		e.getChild("address").setText(arg[1]);
		e.getChild("tel").setText(arg[2]);
		save();
	}
	public void createLocation(){
		Element e = new Element("location");
		e.addContent(new Element("name"));
		e.addContent(new Element("address"));
		e.addContent(new Element("tel"));
		doc.getRootElement().addContent(e);
		save();
	}
	public void deleteLocation(Element e){
		e.getParent().removeContent(e);
		save();
	}
	
	public Object[][] getRooms(Element e){
		int count = 0;
		List<Element> room = e.getChildren("rooms");
		Iterator<Element> i = room.iterator();
		
		Object[][] result = new Object[room.size()][];
		while(i.hasNext()){
			Element tmp = (Element)i.next();
			result[count] = new Object[]{
					tmp.getChild("name").getText(),
					tmp.getChild("nbSeat").getText(),
					tmp
						};
			count++;
		}
		return result;
	}
	public void updateRoom(Element e,String[] arg){
		e.getChild("name").setText(arg[0]);
		e.getChild("nbSeat").setText(arg[1]);
		save();
	}
	public void createRoom(Element location){
		Element e = new Element("rooms");
		e.addContent(new Element("name"));
		e.addContent(new Element("nbSeat"));
		location.addContent(e);
		save();
	}
	public void deleteRoom(Element room){
		room.getParent().removeContent(room);
		save();
	}
}
