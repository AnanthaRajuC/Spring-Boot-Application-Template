/*
 * Engine: MySQL
 * Version: 0.0.1
 * Description: Initial database structure
*/

/*
 * Data - User
 */
INSERT INTO `user`(`email`,`is_account_non_expired`,`is_account_non_locked`,`is_credentials_non_expired`,`is_enabled`,`password`,`username`,`created_by`,`last_modified_by`)VALUES('johndoe@example.com',1,1,1,1,'$2y$10$TGK.5Z.gLBOBlXjFlXTCme4PQlGneVhYrhLuzosdL8jd3xvuuuvYe','johndoe','Installation','Installation');
INSERT INTO `user`(`email`,`is_account_non_expired`,`is_account_non_locked`,`is_credentials_non_expired`,`is_enabled`,`password`,`username`,`created_by`,`last_modified_by`)VALUES('janedoe@example.com',1,1,1,1,'$2y$10$TGK.5Z.gLBOBlXjFlXTCme4PQlGneVhYrhLuzosdL8jd3xvuuuvYe','janedoe','Installation','Installation');
INSERT INTO `user`(`email`,`is_account_non_expired`,`is_account_non_locked`,`is_credentials_non_expired`,`is_enabled`,`password`,`username`,`created_by`,`last_modified_by`)VALUES('admin1@example.com',1,1,1,1,'$2y$10$TGK.5Z.gLBOBlXjFlXTCme4PQlGneVhYrhLuzosdL8jd3xvuuuvYe','Admin1','Installation','Installation');
INSERT INTO `user`(`email`,`is_account_non_expired`,`is_account_non_locked`,`is_credentials_non_expired`,`is_enabled`,`password`,`username`,`created_by`,`last_modified_by`)VALUES('admin2@example.com',1,1,1,1,'$2y$10$TGK.5Z.gLBOBlXjFlXTCme4PQlGneVhYrhLuzosdL8jd3xvuuuvYe','Admin2','Installation','Installation');
INSERT INTO `user`(`email`,`is_account_non_expired`,`is_account_non_locked`,`is_credentials_non_expired`,`is_enabled`,`password`,`username`,`created_by`,`last_modified_by`)VALUES('admintrainee1@example.com',1,1,1,1,'$2y$10$TGK.5Z.gLBOBlXjFlXTCme4PQlGneVhYrhLuzosdL8jd3xvuuuvYe','AdminTrainee1','Installation','Installation');
INSERT INTO `user`(`email`,`is_account_non_expired`,`is_account_non_locked`,`is_credentials_non_expired`,`is_enabled`,`password`,`username`,`created_by`,`last_modified_by`)VALUES('admintrainee2@example.com',1,1,1,1,'$2y$10$TGK.5Z.gLBOBlXjFlXTCme4PQlGneVhYrhLuzosdL8jd3xvuuuvYe','AdminTrainee2','Installation','Installation');

/*
 * Data - Permission
 */
INSERT INTO `sbat_auth_permission`(`name`,`created_by`,`last_modified_by`)VALUES('PERSON_CREATE','Installation','Installation');
INSERT INTO `sbat_auth_permission`(`name`,`created_by`,`last_modified_by`)VALUES('PERSON_READ','Installation','Installation');
INSERT INTO `sbat_auth_permission`(`name`,`created_by`,`last_modified_by`)VALUES('PERSON_UPDATE','Installation','Installation');
INSERT INTO `sbat_auth_permission`(`name`,`created_by`,`last_modified_by`)VALUES('PERSON_DELETE','Installation','Installation');

INSERT INTO `sbat_auth_permission`(`name`,`created_by`,`last_modified_by`)VALUES('RBAC_USER_CREATE','Installation','Installation');
INSERT INTO `sbat_auth_permission`(`name`,`created_by`,`last_modified_by`)VALUES('RBAC_USER_READ','Installation','Installation');
INSERT INTO `sbat_auth_permission`(`name`,`created_by`,`last_modified_by`)VALUES('RBAC_USER_UPDATE','Installation','Installation');
INSERT INTO `sbat_auth_permission`(`name`,`created_by`,`last_modified_by`)VALUES('RBAC_USER_DELETE','Installation','Installation');

/*
 * Data - Role
 */
INSERT INTO `sbat_auth_role`(`name`,`created_by`,`last_modified_by`)VALUES('ROLE_PERSON','Installation','Installation');
INSERT INTO `sbat_auth_role`(`name`,`created_by`,`last_modified_by`)VALUES('ROLE_ADMIN','Installation','Installation');
INSERT INTO `sbat_auth_role`(`name`,`created_by`,`last_modified_by`)VALUES('ROLE_ADMINTRAINEE','Installation','Installation');

/*
 * Data - Permissions for Roles
 */		
INSERT INTO `sbat_auth_permission_role`(`permission_id`,`role_id`)VALUES(1,1); /*PERSON_CREATE - PERSON*/
INSERT INTO `sbat_auth_permission_role`(`permission_id`,`role_id`)VALUES(2,1); /*PERSON_READ   - PERSON*/
INSERT INTO `sbat_auth_permission_role`(`permission_id`,`role_id`)VALUES(3,1); /*PERSON_UPDATE - PERSON*/
INSERT INTO `sbat_auth_permission_role`(`permission_id`,`role_id`)VALUES(4,1); /*PERSON_DELETE - PERSON*/

INSERT INTO `sbat_auth_permission_role`(`permission_id`,`role_id`)VALUES(1,2); /*PERSON_CREATE - ADMIN*/ 
INSERT INTO `sbat_auth_permission_role`(`permission_id`,`role_id`)VALUES(2,2); /*PERSON_CREATE - ADMIN*/ 
INSERT INTO `sbat_auth_permission_role`(`permission_id`,`role_id`)VALUES(3,2); /*PERSON_CREATE - ADMIN*/ 
INSERT INTO `sbat_auth_permission_role`(`permission_id`,`role_id`)VALUES(4,2); /*PERSON_CREATE - ADMIN*/ 
INSERT INTO `sbat_auth_permission_role`(`permission_id`,`role_id`)VALUES(5,2); /*COURSE_READ   - ADMIN*/ 
INSERT INTO `sbat_auth_permission_role`(`permission_id`,`role_id`)VALUES(6,2); /*COURSE_WRITE  - ADMIN*/ 

INSERT INTO `sbat_auth_permission_role`(`permission_id`,`role_id`)VALUES(2,3); /*PERSON_READ   - ADMINTRAINEE*/
INSERT INTO `sbat_auth_permission_role`(`permission_id`,`role_id`)VALUES(5,3); /*PERSON_READ   - ADMINTRAINEE*/

/*
 * Data - Role of User
 */	 
INSERT INTO `sbat_auth_role_user`(`role_id`,`user_id`)VALUES(1,1); /* PERSON       - johndoe           */
INSERT INTO `sbat_auth_role_user`(`role_id`,`user_id`)VALUES(1,2); /* PERSON       - janedoe           */
INSERT INTO `sbat_auth_role_user`(`role_id`,`user_id`)VALUES(2,3); /* ADMIN        - Admin1            */ 
INSERT INTO `sbat_auth_role_user`(`role_id`,`user_id`)VALUES(2,4); /* ADMIN        - Admin2            */
INSERT INTO `sbat_auth_role_user`(`role_id`,`user_id`)VALUES(3,5); /* ADMINTRAINEE - AdminTrainee1     */ 
INSERT INTO `sbat_auth_role_user`(`role_id`,`user_id`)VALUES(3,6); /* ADMINTRAINEE - AdminTrainee2     */ 