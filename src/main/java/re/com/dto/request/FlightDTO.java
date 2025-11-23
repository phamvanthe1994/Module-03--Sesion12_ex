package re.com.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FlightDTO {

    private int flightId;

    @NotBlank(message = "Tên chuyến bay không được để trống !")
    private String flightName;

    @NotBlank(message = "Điểm xuất phát không được để trống !")
    private String startingPoint;

    @NotBlank(message = "Điểm đến không được để trống !")
    private String destination;

    @NotBlank(message = "Ngày khởi hành không được để trống !")
    private Date depatureDate;

    @Min(value = 1, message = "Thời gian bay phải lớn hơn 0")
    private int travelTime;

    private String timeUnit;

    private MultipartFile travelImageFile;

    private String travelImageUrl;

    @Min(value = 0)
    @Max(value = 2)
    private int status = 1;
    
}
