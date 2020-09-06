/*
 * Engine: MySQL
 * Version: 0.0.1
 * Description: Initial database structure
*/

/*
 * Data - User
 */
INSERT INTO `app_user`(`grantedAuthorities`,`isAccountNonExpired`,`isAccountNonLocked`,`isCredentialsNonExpired`,`isEnabled`,`password`,`username`)VALUES('PERSON',1,1,1,1,'$2y$10$TGK.5Z.gLBOBlXjFlXTCme4PQlGneVhYrhLuzosdL8jd3xvuuuvYe','johndoe');
INSERT INTO `app_user`(`grantedAuthorities`,`isAccountNonExpired`,`isAccountNonLocked`,`isCredentialsNonExpired`,`isEnabled`,`password`,`username`)VALUES('ADMIN',1,1,1,1,'$2y$10$TGK.5Z.gLBOBlXjFlXTCme4PQlGneVhYrhLuzosdL8jd3xvuuuvYe','AdminUser');
INSERT INTO `app_user`(`grantedAuthorities`,`isAccountNonExpired`,`isAccountNonLocked`,`isCredentialsNonExpired`,`isEnabled`,`password`,`username`)VALUES('ADMINTRAINEE',1,1,1,1,'$2y$10$TGK.5Z.gLBOBlXjFlXTCme4PQlGneVhYrhLuzosdL8jd3xvuuuvYe','AdminTraineeUser');

/*
 * Data - Permission
 */
INSERT INTO `permission`(`name`)VALUES('PERSON_CREATE');
INSERT INTO `permission`(`name`)VALUES('PERSON_READ');
INSERT INTO `permission`(`name`)VALUES('PERSON_UPDATE');
INSERT INTO `permission`(`name`)VALUES('PERSON_DELETE');
INSERT INTO `permission`(`name`)VALUES('COURSE_READ');
INSERT INTO `permission`(`name`)VALUES('COURSE_WRITE');

/*
 * Data - Role
 */
INSERT INTO `role`(`name`)VALUES('PERSON');
INSERT INTO `role`(`name`)VALUES('ADMIN');
INSERT INTO `role`(`name`)VALUES('ADMINTRAINEE');

/*
 * Data - Permissions for Roles
 */		
INSERT INTO `permission_role`(`permission_id`,`role_id`)VALUES(1,1); /*PERSON_CREATE - PERSON*/
INSERT INTO `permission_role`(`permission_id`,`role_id`)VALUES(2,1); /*PERSON_READ   - PERSON*/
INSERT INTO `permission_role`(`permission_id`,`role_id`)VALUES(3,1); /*PERSON_UPDATE - PERSON*/
INSERT INTO `permission_role`(`permission_id`,`role_id`)VALUES(4,1); /*PERSON_DELETE - PERSON*/

INSERT INTO `permission_role`(`permission_id`,`role_id`)VALUES(1,2); /*PERSON_CREATE - ADMIN*/ 
INSERT INTO `permission_role`(`permission_id`,`role_id`)VALUES(2,2); /*PERSON_CREATE - ADMIN*/ 
INSERT INTO `permission_role`(`permission_id`,`role_id`)VALUES(3,2); /*PERSON_CREATE - ADMIN*/ 
INSERT INTO `permission_role`(`permission_id`,`role_id`)VALUES(4,2); /*PERSON_CREATE - ADMIN*/ 
INSERT INTO `permission_role`(`permission_id`,`role_id`)VALUES(5,2); /*COURSE_READ   - ADMIN*/ 
INSERT INTO `permission_role`(`permission_id`,`role_id`)VALUES(6,2); /*COURSE_WRITE  - ADMIN*/ 

INSERT INTO `permission_role`(`permission_id`,`role_id`)VALUES(2,3); /*PERSON_READ   - ADMINTRAINEE*/
INSERT INTO `permission_role`(`permission_id`,`role_id`)VALUES(5,3); /*PERSON_READ   - ADMINTRAINEE*/

/*
 * Data - Role of User
 */	 
INSERT INTO `role_user`(`role_id`,`user_id`)VALUES(1,1); /* PERSON       - johndoe          */
INSERT INTO `role_user`(`role_id`,`user_id`)VALUES(2,2); /* ADMIN        - AdminUser        */ 
INSERT INTO `role_user`(`role_id`,`user_id`)VALUES(3,3); /* ADMINTRAINEE - AdminTraineeUser */ 