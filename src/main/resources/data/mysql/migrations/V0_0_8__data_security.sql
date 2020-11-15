/*
 * Engine: MySQL
 * Version: 0.0.1
 * Description: Initial database structure
*/

/*
 * Data - User
 */
INSERT INTO `user`(`email`,`isAccountNonExpired`,`isAccountNonLocked`,`isCredentialsNonExpired`,`isEnabled`,`password`,`username`,`created_by`,`last_modified_by`)VALUES('johndoe@example.com',1,1,1,1,'$2y$10$TGK.5Z.gLBOBlXjFlXTCme4PQlGneVhYrhLuzosdL8jd3xvuuuvYe','johndoe','Installation','Installation');
INSERT INTO `user`(`email`,`isAccountNonExpired`,`isAccountNonLocked`,`isCredentialsNonExpired`,`isEnabled`,`password`,`username`,`created_by`,`last_modified_by`)VALUES('adminuser@example.com',1,1,1,1,'$2y$10$TGK.5Z.gLBOBlXjFlXTCme4PQlGneVhYrhLuzosdL8jd3xvuuuvYe','AdminUser','Installation','Installation');
INSERT INTO `user`(`email`,`isAccountNonExpired`,`isAccountNonLocked`,`isCredentialsNonExpired`,`isEnabled`,`password`,`username`,`created_by`,`last_modified_by`)VALUES('admintraineeuser@example.com',1,1,1,1,'$2y$10$TGK.5Z.gLBOBlXjFlXTCme4PQlGneVhYrhLuzosdL8jd3xvuuuvYe','AdminTraineeUser','Installation','Installation');

/*
 * Data - Permission
 */
INSERT INTO `permission`(`name`,`created_by`,`last_modified_by`)VALUES('PERSON_CREATE','Installation','Installation');
INSERT INTO `permission`(`name`,`created_by`,`last_modified_by`)VALUES('PERSON_READ','Installation','Installation');
INSERT INTO `permission`(`name`,`created_by`,`last_modified_by`)VALUES('PERSON_UPDATE','Installation','Installation');
INSERT INTO `permission`(`name`,`created_by`,`last_modified_by`)VALUES('PERSON_DELETE','Installation','Installation');
INSERT INTO `permission`(`name`,`created_by`,`last_modified_by`)VALUES('COURSE_READ','Installation','Installation');
INSERT INTO `permission`(`name`,`created_by`,`last_modified_by`)VALUES('COURSE_WRITE','Installation','Installation');
INSERT INTO `permission`(`name`,`created_by`,`last_modified_by`)VALUES('RBAC_USER_CREATE','Installation','Installation');
INSERT INTO `permission`(`name`,`created_by`,`last_modified_by`)VALUES('RBAC_USER_READ','Installation','Installation');
INSERT INTO `permission`(`name`,`created_by`,`last_modified_by`)VALUES('RBAC_USER_UPDATE','Installation','Installation');
INSERT INTO `permission`(`name`,`created_by`,`last_modified_by`)VALUES('RBAC_USER_DELETE','Installation','Installation');

/*
 * Data - Role
 */
INSERT INTO `role`(`name`,`created_by`,`last_modified_by`)VALUES('ROLE_PERSON','Installation','Installation');
INSERT INTO `role`(`name`,`created_by`,`last_modified_by`)VALUES('ROLE_ADMIN','Installation','Installation');
INSERT INTO `role`(`name`,`created_by`,`last_modified_by`)VALUES('ROLE_ADMINTRAINEE','Installation','Installation');

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