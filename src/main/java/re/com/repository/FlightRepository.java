package re.com.repository;

import re.com.model.Flight;

import java.util.List;

public interface FlightRepository {

    List<Flight> findAll(int pageNumber, int pageSize);

    long countAll();

    boolean save(Flight flight);

    boolean updateFlight(Flight flight);

    Flight findFlightById(int id);

    List<Flight> findFlightByFlightName(String flightName);

    List<Flight> findFlightByRoute(String from, String to);

    boolean deleteFlightById(int flightId);

}
