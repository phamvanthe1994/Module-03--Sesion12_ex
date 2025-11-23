package re.com.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import re.com.model.Flight;
import re.com.repository.FlightRepository;
import re.com.service.FlightService;

import java.util.List;

@Service
public class FlightServiceImp implements FlightService {
    @Autowired
    private FlightRepository flightRepository;

    @Override
    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    @Override
    public boolean addFlight(Flight flight) {
        return false;
    }

    @Override
    public boolean updateFlight(Flight flight) {
        return false;
    }

    @Override
    public Flight findFlightById(int id) {
        return null;
    }
}
