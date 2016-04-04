package domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@DiscriminatorValue("Electronic Device")
public class ElectronicDevice extends SmartDevice{

	public ElectronicDevice() {
		super();
	}
	
	public ElectronicDevice(String name, float avgCons, Home home) {
		super(name, avgCons, home);
	}


	@Override
	public String toString() {
		return "Electronic device : [id=" + super.getId() + ", name=" + name + "]";
	}
}