/**
 * 
 */
package warframeAccountanceAPI.databaseAccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import warframeAccountanceAPI.domain.Type;

/**
 * @author user
 *
 */
public interface TypeRepo extends JpaRepository<Type, Integer>
{
	@Query("FROM Type WHERE typeName = :target")
	public List<Type> findByName(String target);
}
