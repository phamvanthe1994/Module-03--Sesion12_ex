package re.com.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "flight_list")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id", columnDefinition = "int(10)")
    private int flightId;

    @Column(name = "flight_name", columnDefinition = "varchar(100)", nullable = false)
    private String flightName;

    @Column(name = "starting_point", columnDefinition = "varchar(255)", nullable = false)
    private String startingPoint;

    @Column(name = "destination", columnDefinition = "varchar(255)", nullable = false)
    private String destination;

    @Column(name = "departure_date", columnDefinition = "date", nullable = false)
    private Date depatureDate;

    @Column(name = "travel_time", columnDefinition = "int(10)", nullable = false)
    private int travelTime;

    @Column(name = "time_unit", columnDefinition = "varchar(10)")
    private String timeUnit;

    @Column(name = "travel_image", columnDefinition = "varchar(255)", nullable = false)
    private String travelImage;

    @Column(name = "status", columnDefinition = "tinyint default 1")
    private int status;

}
