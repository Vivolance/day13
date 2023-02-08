package SSF.day13;

import java.io.File;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Day13Application implements ApplicationRunner{

	public static void main(String[] args) {
		SpringApplication.run(Day13Application.class, args);

	}
	
	//This is to create a file directory to your local machine to store any new employees added
	@Override
	public void run(ApplicationArguments args) { 

		if (args.containsOption("dataDir")) {
			final String dataDir = args.getOptionValues("dataDir").get(0);

			File fileDir = new File(dataDir);

			// key into command line to create a new fileDir: IMPORTANT!!!
			// Specify where you want to create you fileDir /Users/elsonchan/data
			// mvn spring-boot:run -Dspring-boot.run.arguments="--dataDir=/Users/elsonchan/data"

			if (!fileDir.exists()) {
				fileDir.mkdir();
			} else {
				System.out.println(fileDir.getAbsolutePath());
			}

		}

	}

}
