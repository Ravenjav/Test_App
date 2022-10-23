package belarus.my.testProj.services;

import belarus.my.testProj.entities.Sensor;
import belarus.my.testProj.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorService {
    private SensorRepository sensorRepository;

    @Autowired
    public void setSensorRepository(SensorRepository sensorRepository){
        this.sensorRepository = sensorRepository;
    }

    public Sensor getSensorById(Long id){
        return sensorRepository.findById(id).get();
    }
    public List<Sensor> getAllSensors(){
        return sensorRepository.findAll();
    }

    public void deleteSensorById(Long id){
        sensorRepository.deleteById(id);
    }

    public void addSensor(Sensor sensor){
        sensorRepository.save(sensor);
    }

    public void changeSensor(Sensor sensor){
        Sensor bdSensor = getSensorById(sensor.getId());
        bdSensor.setTitle(sensor.getTitle());
        bdSensor.setModel(sensor.getModel());
        bdSensor.setFrom(sensor.getFrom());
        bdSensor.setTo(sensor.getTo());
        bdSensor.setFrom(sensor.getFrom());
        bdSensor.setUnit(sensor.getUnit());
        bdSensor.setLocation(sensor.getLocation());
        bdSensor.setDescription(sensor.getDescription());
        sensorRepository.save(bdSensor);
    }

    public List<Sensor> setSearch(String search){
        List<Sensor> sensors= sensorRepository.findByTitleContainingIgnoreCase(search);
        sensors.addAll(sensorRepository.findByModelContainingIgnoreCase(search));
        return sensors;
    }
}
