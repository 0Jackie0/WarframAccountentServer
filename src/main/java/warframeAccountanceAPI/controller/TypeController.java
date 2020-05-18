/**
 * 
 */
package warframeAccountanceAPI.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import warframeAccountanceAPI.databaseAccess.TypeRepo;
import warframeAccountanceAPI.domain.Type;

/**
 * @author user
 *
 */
@Slf4j
@RestController
@CrossOrigin
public class TypeController
{
	private final TypeRepo typeRepo;
	
	public TypeController(TypeRepo typeRepo)
	{
		this.typeRepo = typeRepo;
	}
	
	
	@GetMapping("api/type")
	public List<Type> getAllType()
	{
		log.info("Get all type");
		return typeRepo.findAll();
	}
}
