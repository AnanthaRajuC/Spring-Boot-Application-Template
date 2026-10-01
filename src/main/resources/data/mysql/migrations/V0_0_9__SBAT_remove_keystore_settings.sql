/*
 * Engine: MySQL
 * Version: 0.0.9
 * Description: The JWT signing key is configured through sbat.security.jwt.* properties, not stored in the database.
 */

DELETE FROM `sbat_settings` WHERE `app_key` IN ('keystoreFileName', 'keystoreAlias', 'keystorePassword');
