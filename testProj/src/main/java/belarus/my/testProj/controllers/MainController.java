package belarus.my.testProj.controllers;

import belarus.my.testProj.entities.Sensor;
import belarus.my.testProj.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@Controller
@SessionAttributes("search")
public class MainController {
    private SensorService sensorService;

    @Autowired
    public void setSensorService(SensorService sensorService){
        this.sensorService = sensorService;
    }

    @GetMapping("/")
    public String mainPage(Model model){
        List<Sensor> sensors;
        if (model.getAttribute("search") == null) {
            sensors = sensorService.getAllSensors();
        }
        else{
            sensors = sensorService.setSearch((String)model.getAttribute("search"));
        }
        model.addAttribute("sensors", sensors);
        return "main";
    }

    @PostMapping("/find")
    public String mainPageWithSearch(@RequestParam String search, Model model){
        model.addAttribute("search", search);
        return "redirect:/";
    }

    @PostMapping("/clear")
    public String mainPageClearSearch(SessionStatus sessionStatus){
        sessionStatus.setComplete();
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/sensor/delete/{id}")
    @Secured("ADMIN")
    public String deleteSensorById(@PathVariable("id") Long id) {
        sensorService.deleteSensorById(id);
        return "redirect:/";
    }

    @GetMapping("/sensor/form/{id}")
    @Secured("ADMIN")
    public String changeSensor(@PathVariable("id") Long id, Model model) {
        Sensor sensor = sensorService.getSensorById(id);
        model.addAttribute("sensor", sensor);
        return "sensorForm";
    }

    @GetMapping("/sensor/form")
    @Secured("ADMIN")
    public String addSensor(Model model){
        Sensor sensor = new Sensor();
        model.addAttribute("sensor", sensor);
        return "sensorForm";
    }

    @PostMapping("/sensor/add")
    @Secured({"ADMIN", "MANAGER"})
    public String addSensor(@ModelAttribute Sensor sensor){
        if (sensor.getId() != null) {
            sensorService.changeSensor(sensor);
            return "redirect:/";
        }
        sensorService.addSensor(sensor);
        return "redirect:/sensor/form";
    }
}
