/**
 * 
 */
package warframeAccountanceAPI.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author user
 *
 */
@SequenceGenerator(name = "TypeSeq")
@Data
@Entity
@NoArgsConstructor
public class Type
{	
	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TypeSeq")
	@Column(name="typeid")
	private int typeId;
	
	@NotNull
	@Column(name="typename")
	private String typeName;

	
	public Type(@NotNull String typeName)
	{
		super();
		this.typeName = typeName;
	}
}
