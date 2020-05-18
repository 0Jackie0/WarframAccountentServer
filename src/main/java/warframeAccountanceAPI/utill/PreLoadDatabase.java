/**
 * 
 */
package warframeAccountanceAPI.utill;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import warframeAccountanceAPI.databaseAccess.ItemRepo;
import warframeAccountanceAPI.databaseAccess.TypeRepo;
import warframeAccountanceAPI.domain.Item;
import warframeAccountanceAPI.domain.Type;

/**
 * @author user
 *
 *
 */
@Configuration
@Slf4j
public class PreLoadDatabase
{
	@Bean
	CommandLineRunner initDatabase(ItemRepo itemRepository, TypeRepo typeRepository)
	{
		return args ->
		{
			log.info("Preloading " + typeRepository.save(new Type("Warframe - Blueprint")));
			log.info("Preloading " + typeRepository.save(new Type("Warframe - Parts")));
			log.info("Preloading " + typeRepository.save(new Type("Primary - Blueprint")));
			log.info("Preloading " + typeRepository.save(new Type("Primary - Parts")));
			log.info("Preloading " + typeRepository.save(new Type("Secondary - Blueprint")));
			log.info("Preloading " + typeRepository.save(new Type("Secondary - Parts")));
			log.info("Preloading " + typeRepository.save(new Type("Melee - Blueprint")));
			log.info("Preloading " + typeRepository.save(new Type("Melee - Parts")));
			
			
//			Type blueprint = new Type("Warframe - Blueprint");
			int blueprintId = typeRepository.findByName("Warframe - Blueprint").get(0).getTypeId();
//			
//			Type system = new Type("Warframe - Parts");
			int systemId = typeRepository.findByName("Warframe - Parts").get(0).getTypeId();
			
			log.info("Preloading " + itemRepository.save(new Item("Frost Blueprint", blueprintId, 5, 80, 0)));
			log.info("Preloading " + itemRepository.save(new Item("Frost system", systemId, 2, 60, 0)));
			
			log.info("Preloading " + itemRepository.save(new Item("Ember Blueprint", blueprintId, 10, 45, 0)));
			log.info("Preloading " + itemRepository.save(new Item("Ember system", systemId, 8, 35, 0)));
			
			log.info("Preloading " + itemRepository.save(new Item("Ash Blueprint", blueprintId, 4, 60, 50)));
			log.info("Preloading " + itemRepository.save(new Item("Ash system", systemId, 8, 50, 20)));
			
			log.info("Preloading " + itemRepository.save(new Item("Limbo Blueprint", blueprintId, 10, 50, 25)));
			log.info("Preloading " + itemRepository.save(new Item("Limbo system", systemId, 4, 10, 0)));
			
		};
	}
}
