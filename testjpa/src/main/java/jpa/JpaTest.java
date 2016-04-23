package jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import domain.ElectronicDevice;
import domain.Heater;
import domain.Home;
import domain.Person;
import domain.SmartDevice;

public class JpaTest {

	public JpaTest(EntityManager entityManager) {
	}

	public static void main(String[] args) {

		EntityTransaction tx = EntityManagerHelper.getEntityManager().getTransaction();
		tx.begin();
		try {
			//Peuplement de la base
			//createPerson();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		//Requete sur la base
		//listPerson();
		EntityManagerHelper.getEntityManager().close();
		EntityManagerHelper.getEmf().close();
	}

	private  static void createPerson() {
		int numOfPeople = EntityManagerHelper.getEntityManager().createQuery("Select p From Person p", Person.class).getResultList().size();
		if (numOfPeople == 0) {
			//Creation des maisons
			Home home0 = new Home("résidence principale",90, 6, null,null,null);
			List<Home> homes = new ArrayList<Home>();
			homes.add(home0);
			//Creation des chauffages
			Heater heater0=new Heater("Chauffage Salon", 10, home0);
			Heater heater1=new Heater("Chauffage Salle à manger", 10, home0);
			Heater heater2=new Heater("Chauffage Salle de bain", 10, home0);
			Heater heater3=new Heater("Chauffage Chambre 1", 10, home0);
			Heater heater4=new Heater("Chauffage Chambre 2", 10, home0);
			Heater heater5=new Heater("Chauffage Chambre 3", 10, home0);
			//Creation d'un tableau de chauffages
			List<SmartDevice> heaters = new ArrayList<SmartDevice>();
			heaters.add(heater0);
			heaters.add(heater1);
			heaters.add(heater2);
			heaters.add(heater3);
			heaters.add(heater4);
			heaters.add(heater5);
			//Creation des appareils électriques
			ElectronicDevice ed0=new ElectronicDevice("TV", 4, home0);
			ElectronicDevice ed1=new ElectronicDevice("Lecteur Blue-Ray", 1, home0);
			ElectronicDevice ed2=new ElectronicDevice("Téléphone", 1, home0);
			//Creation d'un tableau d'appareils électriques
			List<SmartDevice> electronicDevices = new ArrayList<SmartDevice>();
			electronicDevices.add(ed0);
			electronicDevices.add(ed1);
			electronicDevices.add(ed2);
			//Affectation des chauffages et des appareils electriques à la maison
			home0.setElectronicDevices(electronicDevices);
			home0.setHeaters(heaters);
			//Création des personnes
			Person person1 = new Person(0,"Toto","Jean","jean.toto@tpsir.org",homes);
			Person person2 = new Person(0,"Titi","Jacques","jacques.titi@tpsir.org",homes);
			//Objets rendus persistants
			EntityManagerHelper.getEntityManager().persist(home0);
			EntityManagerHelper.getEntityManager().persist(ed0);
			EntityManagerHelper.getEntityManager().persist(ed1);
			EntityManagerHelper.getEntityManager().persist(ed2);
			EntityManagerHelper.getEntityManager().persist(heater0);
			EntityManagerHelper.getEntityManager().persist(heater1);
			EntityManagerHelper.getEntityManager().persist(heater2);
			EntityManagerHelper.getEntityManager().persist(heater3);
			EntityManagerHelper.getEntityManager().persist(heater4);
			EntityManagerHelper.getEntityManager().persist(heater5);
			EntityManagerHelper.getEntityManager().persist(person1);
			EntityManagerHelper.getEntityManager().persist(person2);
		}
	}

	private static void listPerson() {
		List<Person> resultList = EntityManagerHelper.getEntityManager().createQuery("Select p From Person p", Person.class).getResultList();
		System.out.println("Liste des personnes");
		System.out.println("num of person:" + resultList.size());
		for (Person next : resultList) {
			System.out.println("next person: " + next);
		}
		System.out.println("---------------------");
		List<SmartDevice> resultList2 = EntityManagerHelper.getEntityManager().createQuery("Select s From SmartDevice s", SmartDevice.class).getResultList();
		System.out.println("Liste des smart devices");
		System.out.println("num of smart devices:" + resultList2.size());
		for (SmartDevice next : resultList2) {
			System.out.println("next smart device : " + next);
		}
		System.out.println("---------------------");
		CriteriaBuilder cb = EntityManagerHelper.getEntityManager().getCriteriaBuilder();
		CriteriaQuery query = cb.createQuery(Person.class);
		Root from = query.from(Person.class);
		query.select(from).where(cb.equal(from.get("name"), "Toto"));
		List<Person> result = EntityManagerHelper.getEntityManager().createQuery(query).getResultList();
		for (Object enregistrement : result) {
			System.out.println("enregistrement : " + enregistrement);
		}
		System.out.println("---------------------");
	}
}