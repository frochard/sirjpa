package domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@XmlSeeAlso({Home.class})
@XmlRootElement
public class Person {

	private long id;
	private String name;
	private String firstname;
	private String mail;
	@JsonIgnore
	@XmlTransient 
	private List<Home> homes;
	
	public Person(long id, String name, String firstname, String mail, List<Home> homes) {
		super();
		this.id = id;
		this.name = name;
		this.firstname = firstname;
		this.mail = mail;
		this.homes = homes;
	}
	
	public Person() {
		super();
	}

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	@JsonIgnore
	@XmlTransient 
	@ManyToMany
	public List<Home> getHomes() {
		return homes;
	}
	public void setHomes(List<Home> homes) {
		this.homes = homes;
	}
	
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + "]";
	}	
}