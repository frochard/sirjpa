package domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@DiscriminatorValue("Heater")
public class Heater extends SmartDevice{

	public Heater() {
		super();
	}
	
	public Heater(String name, float avgCons, Home home) {
		super(name, avgCons, home);
	}

	@Override
	public String toString() {
		return "Heater [id=" + super.getId() + ", name=" + name + "]";
	}
}
