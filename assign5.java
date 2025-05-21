Configure pom.xml dependencies (already included if you used Spring Initializr)

<dependency>

<groupId>org.springframework.boot</groupId>

<artifactId>spring-boot-starter-web</artifactId>

</dependency>

<dependency>

<groupId>org.springframework.boot</groupId>

<artifactId>spring-boot-starter-data-jpa</artifactId>

</dependency>
 
<dependency>

<groupId>com.mysql</groupId>

<artifactId>mysql-connector-j</artifactId>

<scope>runtime</scope>

</dependency>




Step 4: Create Model (Entity) Class — Flight.java

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String flightNumber;
    private String source;
    private String destination;
    private String departureTime;
    private String arrivalTime;

    // Getters & Setters
}




Step 5: Create Repository — FlightRepository.java

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
}


Step 6: Create Service Layer — FlightService.java

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepo;

    public List<Flight> getAllFlights() {
        return flightRepo.findAll();
    }

    public Flight addFlight(Flight flight) {
        return flightRepo.save(flight);
    }

    public void deleteFlight(Long id) {
        flightRepo.deleteById(id);
    }
}

Step 7: Create Controller:-  FlightController.java

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping
    public List<Flight> getFlights() {
        return flightService.getAllFlights();
    }



    @PostMapping
    public Flight addFlight(@RequestBody Flight flight) {
        return flightService.addFlight(flight);
    }

    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
    }
}

Step 8: Configure MySQL Connection spring.datasource.url=jdbc:mysql://localhost:3306/airlinedb  
spring.datasource.username=root  
spring.datasource.password=Sakshi@2004
spring.jpa.hibernate.ddl-auto=update  
spring.jpa.show-sql=true  


Create a database busdb in MySQL before running.
