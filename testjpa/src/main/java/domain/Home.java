package domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@XmlSeeAlso({Person.class})
@XmlRootElement
public class Home {

	private long id;
	private String name;
	private int size;
	private int nbRoom;
	private List<SmartDevice> heaters;
	private List<SmartDevice> electronicDevices;
	@JsonIgnore
	@XmlTransient 
	private List<Person> people;	

	public Home() {
		super();
		this.heaters = new ArrayList<SmartDevice>();
		this.electronicDevices = new ArrayList<SmartDevice>();
	}

	public Home( String name, int size, int nbRoom, List<SmartDevice> heaters,
			List<SmartDevice> electronicDevices, List<Person> people) {
		super();
		this.name = name;
		this.size = size;
		this.nbRoom = nbRoom;
		this.heaters = heaters;
		this.electronicDevices = electronicDevices;
		this.people = people;
	}

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getNbRoom() {
		return nbRoom;
	}
	public void setNbRoom(int nbRoom) {
		this.nbRoom = nbRoom;
	}

	@OneToMany(mappedBy="home",cascade=CascadeType.PERSIST)
	public List<SmartDevice> getHeaters() {
		return heaters;
	}
	public void setHeaters(List<SmartDevice> heaters) {
		this.heaters = heaters;
	}

	@OneToMany(mappedBy="home",cascade=CascadeType.PERSIST)
	public List<SmartDevice> getElectronicDevices() {
		return electronicDevices;
	}
	public void setElectronicDevices(List<SmartDevice> electronicDevices) {
		this.electronicDevices = electronicDevices;
	}
	
	public void addDevice(SmartDevice device){
		//Test s'il s'agit d'un chauffage
		if (device instanceof Heater){
			heaters.add(device);
		}else if (device instanceof ElectronicDevice){
			electronicDevices.add(device);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@JsonIgnore
	@XmlTransient 
	@ManyToMany(mappedBy="homes")
	public List<Person> getPeople() {
		return people;
	}

	public void setPeople(List<Person> people) {
		this.people = people;
	}

	@Override
	public String toString() {
		return "Home [id=" + id + ", name=" + name + "]";
	}
}