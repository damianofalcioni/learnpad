package eu.learnpad.verification.plugin.utils;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class ElementID {
	@XmlAttribute(name = "refProcessName")
    private String refprocessname;
	
	@XmlAttribute(name = "refProcessID")
    private String refprocessid;

    @XmlValue
    private String value;
    
    ElementID(){
    
    }
    

	public ElementID(String name, String value, String refprocessid) {
		
		this.refprocessname = name;
		this.value = value;
		this.refprocessid  = refprocessid;
	}


    
}
