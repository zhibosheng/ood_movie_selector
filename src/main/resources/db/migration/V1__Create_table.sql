create table "users"(
	user_id    BIGSERIAL    NOT NULL,
	user_name  VARCHAR(255) NOT NULL UNIQUE,
	password   VARCHAR(255) NOT NULL,
	email      VARCHAR(255) NOT NULL UNIQUE,
	phone      VARCHAR(255) NOT NULL UNIQUE,
	PRIMARY KEY(user_id)
);

create table "groups"(
	group_id       BIGSERIAL NOT NULL,
	group_name     VARCHAR(255) NOT NULL UNIQUE,
	group_description  VARCHAR(255) NOT NULL,
	moderator_id   BIGINT    NOT NULL , 
	last_event_id  BIGINT,
	PRIMARY KEY(group_id)
);

create table "users_groups"(
	user_id   BIGINT NOT NULL,
	group_id  BIGINT NOT NULL
);

create table "events"(
	event_id        BIGSERIAL NOT NULL,
	voting_id       BIGINT,
	group_id        BIGINT NOT NULL,
	selected_movies TEXT,
	create_time     TIMESTAMP NOT NULL,     
	show_time       TIMESTAMP NOT NULL,
	movie_decision  VARCHAR(255),
	PRIMARY KEY(event_id)
);

create table "votings"(
	voting_id      BIGSERIAL NOT NULL,
	start_time     TIMESTAMP NOT NULL,
	end_time       TIMESTAMP NOT NULL,
	voting_result  TEXT,
	PRIMARY KEY(voting_id)
);