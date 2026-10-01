## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See [Deployment](DEPLOYMENT.md) for notes on how to deploy the project on a live system.

<details open="open">
	<ul>
		<li><a href="#prerequisites">Prerequisites</a></li>
		<li><a href="#configuration">Configuration</a></li>
		<li><a href="#smtp-for-account-verification-e-mails">SMTP for account verification e-mails</a></li>
		<li><a href="#mysql-for-the-dev-qa-staging-and-production-profiles">MySQL for the dev, qa, staging and production profiles</a></li>
		<li><a href="#jwt-signing-key">JWT signing key</a></li>
	</ul>
</details>

### Prerequisites

*	**Java 21** (for example [Eclipse Temurin](https://adoptium.net/)). Maven does not need to be installed, the project ships the Maven wrapper (`./mvnw`, `mvnw.cmd` on Windows).
*	Nothing else is needed for the default **`test`** profile: it uses an in-memory **H2** database that Flyway creates and seeds on startup. See [Installation](INSTALLATION.MD).
*	Optional: **MySQL 8** for the other profiles, an **SMTP** server for sign-up e-mails, **Docker** to run the containerised setup.

### Configuration

Shared settings live in `src/main/resources/application.properties`. Each profile file (`application-<profile>.properties`) only overrides what differs.

Everything environment specific (passwords, hosts, keys) is read from **environment variables**. For local development you can instead put them in a **`.env`** file in the project root; it is git-ignored and loaded automatically. Copy [`.env.example`](../.env.example) to get started:

```shell
cp .env.example .env
```

| Variable | Purpose |
|----------|---------|
| `SPRING_PROFILES_ACTIVE` | `test` (default, H2), `dev`, `qa`, `staging`, `production` |
| `DB_HOST`, `DB_PORT`, `DB_DATABASE`, `DB_USERNAME`, `DB_PASSWORD` | MySQL connection |
| `JWT_KEY_STORE`, `JWT_KEY_STORE_PASSWORD`, `JWT_KEY_ALIAS` | RSA key used to sign access tokens, see [JWT signing key](#jwt-signing-key) |
| `REMEMBER_ME_KEY` | Secret for remember-me cookies, random per start when unset |
| `MAIL_PROTOCOL`, `MAIL_HOST`, `MAIL_PORT`, `MAIL_USERNAME`, `MAIL_PASSWORD` | SMTP server for sign-up verification e-mails |
| `BASE_URL` | Public URL used in verification links, defaults to `http://localhost:8080` |
| `SITE_LOGO`, `SITE_INITIALS`, `SITE_TITLE`, `SITE_DESCRIPTION` | Home page branding |
| `FORWARD_HEADERS_STRATEGY` | Set to `native` or `framework` when running behind a reverse proxy, so client IPs (used for login throttling) and the `https` scheme are detected correctly |
| `SSL_ENABLED`, `SSL_KEY_STORE`, `SSL_KEY_STORE_PASSWORD` | Serve HTTPS directly from the application |

### SMTP for account verification e-mails

To activate the accounts of registered users, an e-mail with an activation link is sent to the address given at sign-up. [Mailtrap](https://mailtrap.io/) provides a free SMTP sandbox; any other SMTP server works too.

```properties
MAIL_PROTOCOL=smtp
MAIL_HOST=smtp.mailtrap.io
MAIL_PORT=2525
MAIL_USERNAME=
MAIL_PASSWORD=
```

Without an SMTP server the application still starts; sending the verification e-mail fails and is logged.

### MySQL for the dev, qa, staging and production profiles

Create a database and a user, for example with the MySQL client:

```sql
CREATE DATABASE sbat;
CREATE USER 'sbat'@'%' IDENTIFIED BY 'choose-a-password';
GRANT ALL PRIVILEGES ON sbat.* TO 'sbat'@'%';
```

Then point the application at it:

```properties
DB_HOST=localhost
DB_PORT=3306
DB_DATABASE=sbat
DB_USERNAME=sbat
DB_PASSWORD=choose-a-password
```

When `DB_DATABASE` and `DB_USERNAME` are not set, each profile has its own default (`sbat_dev`, `sbat_qa`, `sbat_staging`; `sbat` for production). Flyway creates the tables and seed data from `src/main/resources/data/mysql/migrations` on first start.

### JWT signing key

Access tokens for the REST API are signed with an RSA key pair (RS256).

*	**Local development:** nothing to do. When no key store is configured, a temporary key pair is generated at startup and a warning is logged. Tokens stop working whenever the application restarts.
*	**Production:** create a key store, keep it out of the repository, and point the application at it:

```shell
mkdir -p secrets
keytool -genkeypair -alias sbat -keyalg RSA -keysize 2048 -validity 3650 \
        -storetype PKCS12 -keystore secrets/jwt.p12 -dname "CN=sbat"
```

```properties
JWT_KEY_STORE=file:secrets/jwt.p12
JWT_KEY_STORE_PASSWORD=the-password-you-chose
JWT_KEY_ALIAS=sbat
```

PKCS12 and JKS key stores are both supported. If the key store cannot be read or has no entry with the given alias, the application fails at startup with a message saying so.
