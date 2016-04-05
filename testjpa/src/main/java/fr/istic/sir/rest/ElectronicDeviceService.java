package fr.istic.sir.rest;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import domain.ElectronicDevice;
import domain.Person;
import jpa.EntityManagerHelper;

@Path("/electronicdevice")
public class ElectronicDeviceService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<ElectronicDevice> getElectronicDevices() {
		String query = "select e from SmartDevice as e where dtype = 'Electronic Device'";
		List<ElectronicDevice> result = EntityManagerHelper.getEntityManager().createQuery(query).getResultList();
		EntityManagerHelper.closeEntityManager();
		return result;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes (MediaType.APPLICATION_JSON)
	public ElectronicDevice create(ElectronicDevice e) {
		EntityTransaction t = EntityManagerHelper.getEntityManager().getTransaction();
		t.begin();
		EntityManagerHelper.getEntityManager().persist(e);
		t.commit();
		EntityManagerHelper.closeEntityManager();
		return e;
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") String arg0) {
		EntityTransaction t = EntityManagerHelper.getEntityManager().getTransaction();
		t.begin();
		EntityManagerHelper.getEntityManager().remove(EntityManagerHelper.getEntityManager().find(ElectronicDevice.class,Long.parseLong(arg0)));
		t.commit();
		EntityManagerHelper.getEntityManager().close();//On ferme l'em pour corriger bug affichage mais pas bonne pratique car couteux
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ElectronicDevice find(@PathParam("id") String arg0) {
		String query = "select e from SmartDevice as e where e.id="+Integer.parseInt(arg0);
		List<ElectronicDevice> result = EntityManagerHelper.getEntityManager().createQuery(query).getResultList();
		if (result.size()==0){
			return null;
		}else{
			ElectronicDevice electronicDevice= result.get(0);
			return electronicDevice;			
		}
	}

	@POST
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Person update(@PathParam("id") String arg0) {
		String query = "select p from Person as p where p.id="+Integer.parseInt(arg0);
		List<Person> result = EntityManagerHelper.getEntityManager().createQuery(query).getResultList();
		if (result.size()==0){
			return null;
		}else{
			Person personTest= result.get(0);
			personTest.setName("Test update REST");
			EntityTransaction t = EntityManagerHelper.getEntityManager().getTransaction();
			t.begin();
			personTest = EntityManagerHelper.getEntityManager().merge(personTest);
			t.commit();
			return personTest;
		}
	}
}