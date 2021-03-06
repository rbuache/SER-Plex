package xml;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import jdom.util.IO;
import launcher.Launcher;

import org.jdom2.Document;
import org.jdom2.Element;

public class XmlPlanification {
	private Document doc;
	private Document docMovie;
	private String file;
	
	public XmlPlanification(String path){
		
		try {
			file = path + "/planification.xml";
			doc = IO.getDocument(file);
			docMovie = IO.getDocument(path + "/movie.xml");
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
	public String[] getPlanification(){
		int count = 0;
		
		Element root = doc.getRootElement();
		List<Element> loc = root.getChildren("movie");
		Iterator<Element> i = loc.iterator();
		
		String[] result = new String[loc.size()];
		while(i.hasNext()){
			Element e = (Element)i.next();
			result[count] = e.getChild("name").getText();
			count++;
		}
		return result;
	}
	public void addPlanification(String movie){
		Element root = doc.getRootElement();
		Element e = new Element("movie");
		e.addContent(new Element("name").setText(movie));
		root.addContent(e);
		save();
	}
	public void suppPlanification(String movie){
		Element el = getElementMovie(movie);
		if(el != null){
			doc.getRootElement().removeContent(el);
		}
		save();
	}
	private Element getElementMovie(String name){
		Element root = doc.getRootElement();

		List<Element> list = root.getChildren("movie");
		Iterator<Element> i = list.iterator();
		while (i.hasNext ())
		{
		    Element el = (Element) i.next ();
		    if(el.getChild("name").getText().equals(name)){
		    	return el;
		    }
		}
		return null;
	}
	public Object[][] getSchedule(String movie){
		int count = 0;
		Element find = getElementMovie(movie);
		if(find != null){
			List<Element> loc = find.getChildren("schedule");
			Iterator<Element> it = loc.iterator();
			
			Object[][] result = new Object[loc.size()][];
			while(it.hasNext()){
				Element e = (Element)it.next();
				result[count] = new Object[]{
							e.getChild("location").getText(),
							e.getChild("location").getAttributeValue("room"),
							e.getChild("date").getText(),
							e.getChild("hour").getText(),
							e
							};
				count++;
			}
			return result;
		}
		return null;
	}
	public String[] getMovies(){
		int count = 0;
		
		Element root = docMovie.getRootElement();
		List<Element> loc = root.getChildren("movie");
		Iterator<Element> i = loc.iterator();
		
		String[] result = new String[loc.size()];
		while(i.hasNext()){
			Element e = (Element)i.next();
			result[count] = e.getChild("name").getText();
			count++;
		}
		return result;
	}
	public void addSchedule(String movie){
		Element e = getElementMovie(movie);
		if(e != null){
			Element nouveau = new Element("schedule");
			Element loc = new Element("location");
			loc.setAttribute("room","");
			nouveau.addContent(new Element("location").setAttribute("room", ""));
			nouveau.addContent(new Element("date"));
			nouveau.addContent(new Element("hour"));
			e.addContent(nouveau);
		}
		save();
	}
	public void updateSchedule(Element e,String[] arg){
		e.getChild("location").setText(arg[0]);
		e.getChild("location").setAttribute("room", arg[1]);
		e.getChild("date").setText(arg[2]);
		e.getChild("hour").setText(arg[3]);
		save();
	}
	public void delSchedule(Element e){
		e.getParent().removeContent(e);
		save();
	}
}