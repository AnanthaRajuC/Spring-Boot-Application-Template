/*
 * Engine: H2
 * Version: 0.0.9
 * Description: The JWT signing key is configured through sbat.security.jwt.* properties, not stored in the database.
 *              Also points sbat_auth_role_user.user_id at the user table (it referenced sbat_auth_permission).
 */

DELETE FROM `sbat_settings` WHERE `app_key` IN ('keystoreFileName', 'keystoreAlias', 'keystorePassword');

CREATE TABLE sbat_auth_role_user_fixed (
  user_id bigint NOT NULL,
  role_id bigint NOT NULL,
  FOREIGN KEY (user_id) REFERENCES `user`(id),
  FOREIGN KEY (role_id) REFERENCES sbat_auth_role(id)
);

INSERT INTO sbat_auth_role_user_fixed (user_id, role_id) SELECT user_id, role_id FROM sbat_auth_role_user;

DROP TABLE sbat_auth_role_user;

ALTER TABLE sbat_auth_role_user_fixed RENAME TO sbat_auth_role_user;
