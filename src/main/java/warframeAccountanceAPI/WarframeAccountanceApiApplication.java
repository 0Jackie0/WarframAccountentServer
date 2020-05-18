package warframeAccountanceAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;


@SpringBootApplication
@Slf4j
public class WarframeAccountanceApiApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(WarframeAccountanceApiApplication.class, args);
		log.info("Ready");
	}

}
