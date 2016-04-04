package fr.istic.sir.rest;

import java.util.Collection;
import java.util.List;

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
		List result = EntityManagerHelper.getEntityManager().createQuery(query).getResultList();
		return result;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes (MediaType.APPLICATION_JSON)
	public ElectronicDevice create(ElectronicDevice e) {
		EntityManagerHelper.getEntityManager().getTransaction().begin();
		EntityManagerHelper.getEntityManager().persist(e);
		EntityManagerHelper.getEntityManager().getTransaction().commit();
		return e;
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") String arg0) {
		String query = "select e from SmartDevice as e where e.id="+Integer.parseInt(arg0);
		List<ElectronicDevice> result = EntityManagerHelper.getEntityManager().createQuery(query).getResultList();
		if (result.size()!=0){
			ElectronicDevice electronicDeviceTest= result.get(0);
			EntityManagerHelper.getEntityManager().getTransaction().begin();
			electronicDeviceTest = EntityManagerHelper.getEntityManager().merge(electronicDeviceTest);
			EntityManagerHelper.getEntityManager().remove(electronicDeviceTest);
			EntityManagerHelper.getEntityManager().getTransaction().commit();
		}
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
			ElectronicDevice electronicDeviceTest= result.get(0);
			return electronicDeviceTest;			
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
			EntityManagerHelper.getEntityManager().getTransaction().begin();
			personTest = EntityManagerHelper.getEntityManager().merge(personTest);
			EntityManagerHelper.getEntityManager().getTransaction().commit();
			return personTest;
		}
	}
}