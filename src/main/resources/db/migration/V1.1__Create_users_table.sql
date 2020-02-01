create table "users"(
	user_id    BIGSERIAL    NOT NULL,
	user_name  VARCHAR(255) NOT NULL UNIQUE,
	password   VARCHAR(255) NOT NULL,
	email      VARCHAR(255) NOT NULL UNIQUE,
	phone      VARCHAR(255) NOT NULL UNIQUE,
	PRIMARY KEY(user_id)
);