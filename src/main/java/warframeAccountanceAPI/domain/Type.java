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
@SequenceGenerator(name = "TypeSeq")
@Data
@Entity
@NoArgsConstructor
public class Type
{
	private @Id @GeneratedValue int typeId;
	
	@NotNull
	private String typeName;

	
	public Type(@NotNull String typeName)
	{
		super();
		this.typeName = typeName;
	}
}
