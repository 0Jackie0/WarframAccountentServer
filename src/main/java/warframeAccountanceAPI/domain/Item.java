/**
 * 
 */
package warframeAccountanceAPI.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author user
 *
 */
@SequenceGenerator(name = "ItemSeq")
@Data
@Entity
@NoArgsConstructor
public class Item
{
	private @Id @GeneratedValue int itemId;
	
	@NotNull
	private String name;
	
	@NotNull
	private int type;
	
	@NotNull
	private int quantity;
	
	@NotNull
	private int ePrice;
	
	@NotNull
	private int bPrice;
	
	
	public Item(String name, int type, int quantity, int ePrice, int bPrice)
	{
		this.name = name;
		this.type = type;
		this.quantity = quantity;
		this.ePrice = ePrice;
		this.bPrice = bPrice;
	}
	
}
