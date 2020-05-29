/**
 * 
 */
package warframeAccountanceAPI.databaseAccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import warframeAccountanceAPI.domain.Item;

/**
 * @author user
 *
 */
public interface ItemRepo extends JpaRepository<Item, Integer>
{
	public List<Item> findAllByTypeOrderByNameAsc(int type);
	
	public List<Item> findAllByTypeOrderByQuantityAsc(int type);
	
	@Query("From Item Order By Name, Quantity Asc")
	public List<Item> findAllOrderByNameAsc();
	@Query("From Item Order By Quantity, Name Asc")
	public List<Item> findAllOrderByQuantityAsc();
}
