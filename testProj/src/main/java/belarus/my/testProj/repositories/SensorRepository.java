package belarus.my.testProj.repositories;

import belarus.my.testProj.entities.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
    List<Sensor> findByTitleContainingIgnoreCase(String title);

    List<Sensor> findByModelContainingIgnoreCase(String model);
}
