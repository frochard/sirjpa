package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@XmlRootElement
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class SmartDevice {

	private long id;
	protected String name;
	private float avgCons;
	@JsonIgnore
	@XmlTransient 
	private Home home;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getAvgCons() {
		return avgCons;
	}

	public void setAvgCons(float avgCons) {
		this.avgCons = avgCons;
	}

	@ManyToOne
	@JsonIgnore
	@XmlTransient 
	public Home getHome() {
		return home;
	}

	public SmartDevice(String name, float avgCons, Home home) {
		super();
		this.name = name;
		this.avgCons = avgCons;
		this.home = home;
	}

	public SmartDevice() {
	}

	public void setHome(Home home) {
		this.home = home;
	}

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}