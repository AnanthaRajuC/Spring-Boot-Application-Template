package io.github.anantharajuc.sbtest.util;

import io.github.anantharajuc.sbtest.backend.persistence.domain.backend.User;

public class UserUtil 
{
	public static User createBasicUser(String username, String email) 
	{
        User user = new User();
        user.setUsername(username);
        user.setPassword("secret");
        user.setEmail(email);
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setPhone("123456789123");
        user.setCountry("GB");
        user.setEnabled(true);
        user.setDescription("A basic user");
        user.setProfileImageUrl("https://blabla.images.com/basicuser");

        return user;
    }
}
