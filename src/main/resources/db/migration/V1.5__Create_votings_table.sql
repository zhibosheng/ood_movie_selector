create table "votings"(
	voting_id      BIGSERIAL NOT NULL,
	start_time     TIMESTAMP NOT NULL,
	end_time       TIMESTAMP NOT NULL,
	voting_result  TEXT,
	PRIMARY KEY(voting_id)
);