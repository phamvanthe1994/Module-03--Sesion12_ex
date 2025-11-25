package re.com.repository.imp;

import org.springframework.stereotype.Repository;
import re.com.model.Flight;
import re.com.repository.FlightRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class FlightRepositoryImp implements FlightRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Flight> findAll(int pageNumber, int pageSize) {
        return entityManager.createQuery("from Flight ", Flight.class)
                .setFirstResult((pageNumber - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

    @Override
    public long countAll() {
        return entityManager.createQuery("select count(f) from Flight f", Long.class).getSingleResult();
    }

    @Transactional
    @Override
    public boolean save(Flight flight) {
        try {
            entityManager.persist(flight);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    @Transactional
    @Override
    public boolean updateFlight(Flight flight) {
        try {
            entityManager.merge(flight);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Flight findFlightById(int id) {
        return entityManager.createQuery("from Flight where id = :id", Flight.class).setParameter("id", id).getSingleResult();
    }

    @Override
    public List<Flight> findFlightByFlightName(String flightName) {
        return entityManager.createQuery("from Flight f where lower(f.flightName) LIKE lower(:flightName) ", Flight.class)
                .setParameter("flightName", "%" + flightName + "%")
                .getResultList();
    }

    @Override
    public List<Flight> findFlightByRoute(String from, String to) {
        return entityManager.createQuery("from Flight f where f.startingPoint =: from AND f.destination=: to", Flight.class)
                .setParameter("from", from)
                .setParameter("to", to)
                .getResultList();
    }

    @Transactional
    @Override
    public boolean deleteFlightById(int flightId) {
        try {
            Flight flight = findFlightById(flightId);
            entityManager.remove(flight);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
