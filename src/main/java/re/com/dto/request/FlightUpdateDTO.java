package re.com.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FlightUpdateDTO {
    private int flightId;

    @NotBlank(message = "Tên chuyến bay không được để trống !")
    private String flightName;

    @NotBlank(message = "Điểm xuất phát không được để trống !")
    private String startingPoint;

    @NotBlank(message = "Điểm đến không được để trống !")
    private String destination;

    @NotNull(message = "Ngày khởi hành không được để trống !")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date departureDate;

    @Min(value = 1, message = "Thời gian bay phải lớn hơn 0")
    private int travelTime;

    private String timeUnit;

    private MultipartFile travelImageFile;

    private String travelImage;

    @Min(value = 0)
    @Max(value = 2)
    private int status = 1;
}
