package re.com.service;

import re.com.model.Flight;

import java.util.List;

public interface FlightService {
    public List<Flight> findAll();

    boolean addFlight(Flight flight);

    boolean updateFlight(Flight flight);

    Flight findFlightById(int id);
}
