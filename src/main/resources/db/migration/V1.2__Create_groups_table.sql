create table "groups"(
	group_id       BIGSERIAL NOT NULL,
	moderator_id   BIGINT    NOT NULL , 
	last_event_id  BIGINT,
	PRIMARY KEY(group_id),
	FOREIGN KEY (moderator_id) REFERENCES users(user_id),
	FOREIGN KEY (last_event_id) REFERENCES events(event_id),
);