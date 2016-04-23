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
import domain.Home;
import jpa.EntityManagerHelper;

@Path("/homes")
public class HomeService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Home> getHomes() {
		String query = "select h from Home as h";
		List<Home> result = EntityManagerHelper.getEntityManager().createQuery(query).getResultList();
		EntityManagerHelper.closeEntityManager();
		System.out.println(result);
		return result;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes (MediaType.APPLICATION_JSON)
	public Home create(Home h) {
		EntityTransaction t= EntityManagerHelper.getEntityManager().getTransaction();
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
		EntityManagerHelper.getEntityManager().remove(EntityManagerHelper.getEntityManager().find(Home.class,Long.parseLong(arg0)));
		t.commit();
		EntityManagerHelper.closeEntityManager();//On ferme l'em pour corriger bug affichage mais pas bonne pratique car couteux
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Home find(@PathParam("id") String arg0) {
		String query = "select h from Home as h where h.id="+Integer.parseInt(arg0);
		List<Home> result = EntityManagerHelper.getEntityManager().createQuery(query).getResultList();
		if (result.size()==0){
			return null;
		}else{
			Home home= result.get(0);
			return home;			
		}
	}

	@POST
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Home update(@PathParam("id") String arg0) {
		String query = "select h from Home as h where h.id="+Integer.parseInt(arg0);
		List<Home> result = EntityManagerHelper.getEntityManager().createQuery(query).getResultList();
		if (result.size()==0){
			return null;
		}else{
			Home home= result.get(0);
			home.setName("Test update REST");
			EntityTransaction t = EntityManagerHelper.getEntityManager().getTransaction();
			t.begin();
			home = EntityManagerHelper.getEntityManager().merge(home);
			t.commit();
			return home;
		}
	}
}