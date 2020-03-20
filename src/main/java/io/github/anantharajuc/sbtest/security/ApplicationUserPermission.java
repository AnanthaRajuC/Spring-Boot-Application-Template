package io.github.anantharajuc.sbtest.security;

import lombok.Getter;

@Getter
public enum ApplicationUserPermission 
{
	PERSON_READ("person:read"),
	PERSON_WRITE("person:write");
	
	
	private final String permission;

	ApplicationUserPermission(String permission) 
	{
		this.permission = permission;
	}
}
