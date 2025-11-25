package re.com.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import re.com.dto.request.FlightDTO;
import re.com.dto.request.FlightUpdateDTO;
import re.com.model.Flight;
import re.com.repository.FlightRepository;
import re.com.service.FlightService;
import re.com.service.UploadFileService;

import java.util.List;

@Service
public class FlightServiceImp implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private UploadFileService uploadFileService;

    @Override
    public List<Flight> findAll(int pageNumber, int pageSize) {
        return flightRepository.findAll(pageNumber, pageSize);
    }

    @Override
    public long countAll() {
        return flightRepository.countAll();
    }

    @Override
    public boolean saveFlight(FlightDTO flightDTO) {

        //1.upload ảnh lên cloudiary và lấy đường dẫn ảnh

        String imageURL = uploadFileService.uploadFile(flightDTO.getTravelImageFile());

        // 2. chuyển flightDTO -> flight
        Flight flight = new Flight();
        flight.setFlightId(flightDTO.getFlightId());
        flight.setFlightName(flightDTO.getFlightName());
        flight.setStartingPoint(flightDTO.getStartingPoint());
        flight.setDestination(flightDTO.getDestination());
        flight.setDepatureDate(flightDTO.getDepartureDate());
        flight.setTravelTime(flightDTO.getTravelTime());
        flight.setTimeUnit(flightDTO.getTimeUnit());
        flight.setTravelImage(imageURL);
        flight.setStatus(flightDTO.getStatus());
        //3. Gọi repository để thêm mới sản phẩm
        System.out.println("Đang lưu flight:" + flight);
        return flightRepository.save(flight);


    }

    @Override
    public FlightUpdateDTO findFlightById(int flightId) {
        // Tìm thanh niên flight
        Flight flight = flightRepository.findFlightById(flightId);
        if (flight == null) {
            throw new RuntimeException("Không tìm thấy chuyến bay bạn ơi!");
        }
        FlightUpdateDTO flightUpdateDTO = new FlightUpdateDTO();
        // chuyển  model sang DTO
        flightUpdateDTO.setFlightId(flight.getFlightId());
        flightUpdateDTO.setFlightName(flight.getFlightName());
        flightUpdateDTO.setStartingPoint(flight.getStartingPoint());
        flightUpdateDTO.setDestination(flight.getDestination());
        flightUpdateDTO.setDepartureDate(flight.getDepatureDate());
        flightUpdateDTO.setTravelTime(flight.getTravelTime());
        flightUpdateDTO.setTimeUnit(flight.getTimeUnit());
        flightUpdateDTO.setTravelImage(flight.getTravelImage());
        flightUpdateDTO.setStatus(flight.getStatus());
        return flightUpdateDTO;
    }

    @Override
    public boolean updateFlightStatus(int flightId, int status) {
        Flight flight = flightRepository.findFlightById(flightId);
        if (flight == null) {
            return false;
        }
        if (flight.getStatus() == 2) {// Nếu status == 2 (Đã hoàn thành ) -> không đổi
            return false;
        }
        flight.setStatus(status);
        flightRepository.updateFlight(flight);
        return true;
    }

    @Override
    public List<Flight> findFlightByFlightName(String flightName) {
        return flightRepository.findFlightByFlightName(flightName);
    }

    @Override
    public List<Flight> findFlightByRoute(String from, String to) {
        return flightRepository.findFlightByRoute(from, to);
    }

    @Override
    public boolean deleteFlightByFlightId(int flightId) {
        return flightRepository.deleteFlightById(flightId);
    }


    @Override
    public boolean updateFlight(FlightUpdateDTO flightUpdateDTO) {
        // Tìm thanh niên flight
        Flight flight = flightRepository.findFlightById(flightUpdateDTO.getFlightId());
        if (flight == null) {
            throw new RuntimeException("Không tìm thấy chuyến bay bạn ơi !");
        }

        String imageURL = flight.getTravelImage();// giữ ảnh cũ

        // Nếu có ảnh mới , upload và thay thế
        if (flightUpdateDTO.getTravelImageFile() != null && !flightUpdateDTO.getTravelImageFile().isEmpty()) {
            imageURL = uploadFileService.uploadFile(flightUpdateDTO.getTravelImageFile());
        }

        // lại chuyển DTO về model
        flight.setFlightName(flightUpdateDTO.getFlightName());
        flight.setStartingPoint(flightUpdateDTO.getStartingPoint());
        flight.setDestination(flightUpdateDTO.getDestination());
        flight.setDepatureDate(flightUpdateDTO.getDepartureDate());
        flight.setTravelTime(flightUpdateDTO.getTravelTime());
        flight.setTimeUnit(flightUpdateDTO.getTimeUnit());
        flight.setTravelImage(imageURL);
        flight.setStatus(flightUpdateDTO.getStatus());

        return flightRepository.updateFlight(flight);
    }


}
