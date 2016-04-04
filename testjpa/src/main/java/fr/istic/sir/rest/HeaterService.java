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

import domain.Heater;
import jpa.EntityManagerHelper;

@Path("/heater")
public class HeaterService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Heater> getHeaters() {
		String query = "select s from SmartDevice as s where dtype = 'Heater'";
		List result = EntityManagerHelper.getEntityManager().createQuery(query).getResultList();
		return result;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes (MediaType.APPLICATION_JSON)
	public Heater create(Heater h) {
		EntityManagerHelper.getEntityManager().getTransaction().begin();
		EntityManagerHelper.getEntityManager().persist(h);
		EntityManagerHelper.getEntityManager().getTransaction().commit();
		return h;
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") String arg0) {
		String query = "select h from SmartDevice as h where h.id="+Integer.parseInt(arg0);
		List<Heater> result = EntityManagerHelper.getEntityManager().createQuery(query).getResultList();
		if (result.size()!=0){
			Heater heaterTest= result.get(0);
			EntityManagerHelper.getEntityManager().getTransaction().begin();
			heaterTest = EntityManagerHelper.getEntityManager().merge(heaterTest);
			EntityManagerHelper.getEntityManager().remove(heaterTest);
			EntityManagerHelper.getEntityManager().getTransaction().commit();
		}
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Heater find(@PathParam("id") String arg0) {
		String query = "select h from SmartDevice as h where h.id="+Integer.parseInt(arg0);
		List<Heater> result = EntityManagerHelper.getEntityManager().createQuery(query).getResultList();
		if (result.size()==0){
			return null;
		}else{
			Heater heaterTest= result.get(0);
			return heaterTest;			
		}
	}

	@POST
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Heater update(@PathParam("id") String arg0) {
		String query = "select h from SmartDevice as h where h.id="+Integer.parseInt(arg0);
		List<Heater> result = EntityManagerHelper.getEntityManager().createQuery(query).getResultList();
		if (result.size()==0){
			return null;
		}else{
			Heater heaterTest= result.get(0);
			heaterTest.setName("Test update REST");
			EntityManagerHelper.getEntityManager().getTransaction().begin();
			heaterTest = EntityManagerHelper.getEntityManager().merge(heaterTest);
			EntityManagerHelper.getEntityManager().getTransaction().commit();
			return heaterTest;
		}
	}
}