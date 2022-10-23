package belarus.my.testProj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static belarus.my.testProj.PrepareDataApp.forcePrepareData;

@SpringBootApplication
public class TestProjApplication {

	public static void main(String[] args) {
		forcePrepareData();
		SpringApplication.run(TestProjApplication.class, args);
	}
}
