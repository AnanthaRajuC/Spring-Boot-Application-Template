package io.github.anantharajuc.sbtest.security;

public enum ApplicationUserPermission 
{
	PERSON_CREATE("person:create"),
	PERSON_READ("person:read"),    
	PERSON_UPDATE("person:update"),
	PERSON_DELETE("person:delete"),
	
	COURSE_READ("course:read"),
	COURSE_WRITE("course:write");
	
	private final String permission;
	
	ApplicationUserPermission(String permission) 
	{
        this.permission = permission;
    }

    public String getPermission() 
    {
        return permission;
    }
}
