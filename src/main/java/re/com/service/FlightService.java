package re.com.service;

import re.com.dto.request.FlightDTO;
import re.com.dto.request.FlightUpdateDTO;
import re.com.model.Flight;

import java.util.List;

public interface FlightService {

    public List<Flight> findAll(int pageNumber, int pageSize);

    long countAll();

    boolean saveFlight(FlightDTO flightDTO);

    boolean updateFlight(FlightUpdateDTO flightUpdateDTO);

    FlightUpdateDTO findFlightById(int flightId);

    boolean updateFlightStatus(int flightId, int status);

    List<Flight> findFlightByFlightName(String flightName);

    List<Flight> findFlightByRoute(String from, String to);

    boolean deleteFlightByFlightId(int flightId);
}
