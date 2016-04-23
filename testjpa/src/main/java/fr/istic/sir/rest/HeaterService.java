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
import domain.Heater;
import jpa.EntityManagerHelper;

@Path("/heater")
public class HeaterService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Heater> getHeaters() {
		String query = "select s from SmartDevice as s where dtype = 'Heater'";
		List<Heater> result = EntityManagerHelper.getEntityManager().createQuery(query).getResultList();
		EntityManagerHelper.closeEntityManager();
		return result;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes (MediaType.APPLICATION_JSON)
	public Heater create(Heater h) {
		EntityTransaction t = EntityManagerHelper.getEntityManager().getTransaction();
		t.begin();
		EntityManagerHelper.getEntityManager().persist(h);
		t.commit();
		EntityManagerHelper.closeEntityManager();
		return h;
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") String arg0) {
		EntityTransaction t = EntityManagerHelper.getEntityManager().getTransaction();
		t.begin();
		EntityManagerHelper.getEntityManager().remove(EntityManagerHelper.getEntityManager().find(Heater.class,Long.parseLong(arg0)));
		t.commit();
		EntityManagerHelper.closeEntityManager();
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
			Heater heater= result.get(0);
			return heater;			
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
			EntityTransaction t = EntityManagerHelper.getEntityManager().getTransaction();
			t.begin();
			heaterTest = EntityManagerHelper.getEntityManager().merge(heaterTest);
			t.commit();
			return heaterTest;
		}
	}
}