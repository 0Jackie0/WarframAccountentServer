/**
 * 
 */
package warframeAccountanceAPI.databaseAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import warframeAccountanceAPI.domain.Item;

/**
 * @author user
 *
 */
public interface ItemRepo extends JpaRepository<Item, Integer>
{
	
}
