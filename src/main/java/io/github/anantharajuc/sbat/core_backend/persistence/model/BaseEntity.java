package io.github.anantharajuc.sbat.core_backend.persistence.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * Simple JavaBean domain object with an id property. Used as a base class for objects
 * needing this property.
 *
 * @author Anantha Raju C
 */
@MappedSuperclass
@Getter
@Setter
@FieldDefaults(level=AccessLevel.PRIVATE)
public class BaseEntity implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
}
