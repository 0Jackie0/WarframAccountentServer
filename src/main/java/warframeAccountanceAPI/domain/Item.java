/**
 * 
 */
package warframeAccountanceAPI.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ItemSeq")
	@Column(name="itemid")
	private int itemId;
	
	@NotNull
	@Column(name="name")
	private String name;
	
	@NotNull
	@JoinColumn(name = "typeid")
	@Column(name="type")
	private int type;
	
	@NotNull
	@Column(name="quantity")
	private int quantity;
	
	@NotNull
	@Column(name="eprice")
	private int eprice;
	
	@NotNull
	@Column(name="bprice")
	private int bprice;
	
	@Lob
	@Column(length = 50000, name="imagestring")
	private String imageString;
	
	
	public Item(String name, int type, int quantity, int ePrice, int bPrice, String imageString)
	{
		this.name = name;
		this.type = type;
		this.quantity = quantity;
		this.eprice = ePrice;
		this.bprice = bPrice;
		this.imageString = imageString;
	}
	
}
