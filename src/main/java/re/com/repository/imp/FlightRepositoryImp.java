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
    public List<Flight> findAll() {
        return entityManager.createQuery("from Flight ", Flight.class).getResultList();

    }

    @Transactional
    @Override
    public boolean addFlight(Flight flight) {
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
}
