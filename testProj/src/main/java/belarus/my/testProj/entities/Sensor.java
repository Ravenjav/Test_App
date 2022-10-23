package belarus.my.testProj.entities;

import belarus.my.testProj.exceptions.EntityException;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sensors")
@TypeDef(
        name = "pgsql_enum",
        typeClass = EnumTypePostgreSQL.class
)
public class Sensor implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "model")
    private String model;

    @Column(name = "range_from")
    private int from;

    @Column(name = "range_to")
    private int to;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "sensor_type")
    @Type(type = "pgsql_enum")
    private SensorType type;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "sensor_unit")
    @Type(type = "pgsql_enum")
    private SensorUnit unit;
    @Column(name = "location")
    private String location;
    @Column(name = "description")
    private String description;

    public Sensor(){}

    public Sensor(String title, String model, int from, int to, SensorType type, SensorUnit unit, String location, String description) throws EntityException {
        if (from > to)
            throw new EntityException("invalid values");
        this.title = title;
        this.model = model;
        this.from = from;
        this.to = to;
        this.type = type;
        this.unit = unit;
        this.location = location;
        this.description = description;
    }

    public Sensor(Sensor sensor) throws EntityException {
        this.id = sensor.getId();
        this.title = sensor.getTitle();
        this.model = sensor.getModel();
        this.from = sensor.getFrom();
        this.to = sensor.getTo();
        this.type = sensor.getType();
        this.unit = sensor.getUnit();
        this.location = sensor.getLocation();
        this.description = sensor.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public SensorType getType() {
        return type;
    }

    public void setType(SensorType type) {
        this.type = type;
    }

    public SensorUnit getUnit() {
        return unit;
    }

    public void setUnit(SensorUnit unit) {
        this.unit = unit;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", model='" + model + '\'' +
                ", from=" + from +
                ", to=" + to +
                ", type=" + type +
                ", unit=" + unit +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
