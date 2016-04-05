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
import domain.Person;
import jpa.EntityManagerHelper;

@Path("/person")
public class PersonService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Person> getPeople() {
		String query = "select p from Person as p";
		List<Person> result = EntityManagerHelper.getEntityManager().createQuery(query).getResultList();
		EntityManagerHelper.closeEntityManager();//On ferme l'em pour corriger bug affichage mais pas bonne pratique car couteux
		return result;
	}

	@POST
	@Consumes (MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Person create(Person p) {
		EntityTransaction t = EntityManagerHelper.getEntityManager().getTransaction();
		t.begin();
		EntityManagerHelper.getEntityManager().persist(p);
		t.commit();
		EntityManagerHelper.closeEntityManager();//On ferme l'em pour corriger bug affichage mais pas bonne pratique car couteux
		return p;
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") String arg0) {
		EntityTransaction t = EntityManagerHelper.getEntityManager().getTransaction();
		t.begin();
		EntityManagerHelper.getEntityManager().remove(EntityManagerHelper.getEntityManager().find(Person.class,Long.parseLong(arg0)));
		t.commit();
		EntityManagerHelper.getEntityManager().close();//On ferme l'em pour corriger bug affichage mais pas bonne pratique car couteux
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Person find(@PathParam("id") String arg0) {
		String query = "select p from Person as p where p.id="+Integer.parseInt(arg0);
		List<Person> result = EntityManagerHelper.getEntityManager().createQuery(query).getResultList();
		if (result.size()==0){
			return null;
		}else{
			Person person= result.get(0);
			return person;			
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
			Person person= result.get(0);
			person.setName("Test update REST");
			EntityTransaction t = EntityManagerHelper.getEntityManager().getTransaction();
			t.begin();
			person = EntityManagerHelper.getEntityManager().merge(person);
			t.commit();
			return person;
		}
	}
}