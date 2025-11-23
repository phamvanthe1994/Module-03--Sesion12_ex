package re.com.repository;

import re.com.model.Flight;

import java.util.List;

public interface FlightRepository {

    List<Flight> findAll();

    boolean addFlight(Flight flight);

    boolean updateFlight(Flight flight);

    Flight findFlightById(int id);


}
