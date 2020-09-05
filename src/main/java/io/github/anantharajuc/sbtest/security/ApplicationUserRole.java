package io.github.anantharajuc.sbtest.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static io.github.anantharajuc.sbtest.security.ApplicationUserPermission.*;

@AllArgsConstructor
@Getter
public enum ApplicationUserRole 
{
	PERSON(Sets.newHashSet()),
	ADMIN(Sets.newHashSet(PERSON_CREATE,PERSON_READ,PERSON_UPDATE,PERSON_DELETE,COURSE_READ,COURSE_WRITE)),
	ADMINTRAINEE(Sets.newHashSet(PERSON_READ,COURSE_READ));
	
	private final Set<ApplicationUserPermission> permissions;
	
	public Set<SimpleGrantedAuthority> getGrantedAuthorities() 
	{
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
										                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
										                .collect(Collectors.toSet());
        
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        
        return permissions;
    }
}
