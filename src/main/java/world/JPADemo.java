package world;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import world.domain.City;
import world.domain.Country;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class JPADemo {

    static EntityManagerFactory factory = Persistence.createEntityManagerFactory("world");
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean loop = true;

        while(loop) {

            System.out.print("Country to find: ");
            String country = sc.nextLine();
            findCountry(country);

        }


    }

    public static void findCity(String name) {

        EntityManager em = factory.createEntityManager();

        Query query = em.createQuery("SELECT c from City c where c.name = :name");
        query.setParameter("name", name);

        List<City> cities = query.getResultList();
        System.out.printf("\nFound %d matches for %s\n", cities.size(), name);
        cities.forEach(System.out::println);
        em.close();

    }

    public static void findCountry(String name) {

        EntityManager em = factory.createEntityManager();

        Query query = em.createQuery("SELECT c from Country c where c.name = :name");
        query.setParameter("name", name);

        List<Country> countries = query.getResultList();

        if(countries.size() == 0) {
            System.out.println("No matches for " + name);
        } else {
            DecimalFormat df = new DecimalFormat("###,###,###");
            for (Country country : countries) {
                City capital = country.getCapital();
                Set<City> cities = country.getCities();
                System.out.println(country.getName() +
                        " in " + country.getRegion() +
                        ", population " + df.format(country.getPopulation()) +
                        "\nThe capital city is " +
                        capital.getName() +
                        ", " + capital.getDistrict() +
                        " pop. " + df.format(capital.getPopulation()) +
                        "\nCities in " + country.getName());
                cities.forEach(c -> System.out.println("\t" + c.getName() +
                        ", " + c.getDistrict() +
                        " pop. " + df.format(c.getPopulation())));

            }
        }

        em.close();

    }
}
