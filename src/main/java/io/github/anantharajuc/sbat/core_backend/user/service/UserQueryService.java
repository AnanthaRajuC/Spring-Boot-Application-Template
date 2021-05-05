package io.github.anantharajuc.sbat.core_backend.user.service;

import io.github.anantharajuc.sbat.core_backend.user.model.dto.UserDTO;

/**
 * User Query Service.
 * 
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 */
public interface UserQueryService 
{
	UserDTO getUserByUsername(String username);
}
