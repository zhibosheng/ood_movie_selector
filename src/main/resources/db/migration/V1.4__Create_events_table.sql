create table "events"(
	event_id        BIGSERIAL NOT NULL,
	voting_id       BIGINT,
	group_id        BIGINT NOT NULL,
	create_time     TIMESTAMP NOT NULL,     
	show_time       TIMESTAMP NOT NULL,
	movie_decision  VARCHAR(255),
	PRIMARY KEY(event_id),
	FOREIGN KEY (voting_id) REFERENCES votings(voting_id),
	FOREIGN KEY (group_id) REFERENCES groups(group_id),
);