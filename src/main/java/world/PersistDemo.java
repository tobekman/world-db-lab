package world;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import world.domain.City;
import world.domain.Country;


public class PersistDemo {

    public static void main(String[] args) {

        Country java = new Country("JavaLand",
                "South America",
                1000000,
                "Eastern",
                "JAV");


        City espresso = new City();
        espresso.setName("Espresso");
        espresso.setDistrict("Beans");
        espresso.setPopulation(20000);
        espresso.setCountry(java);

        java.setCapital(espresso);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("world");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(java);
        em.getTransaction().commit();

        System.out.println("After saving city id is " + espresso.getId());

        em.close();
        emf.close();

    }
}
