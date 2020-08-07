/*package io.github.anantharajuc.sbtest.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "quote")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Quote extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id",referencedColumnName = "id")
    private Person person;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;
	
	@NotBlank
	@Size(min=3, max=150, message="Name must be between 3 and 150 characters.")
	@Column(name="quote", nullable = false)
    private String quotes; 
}
*/