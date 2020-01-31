create table "users_groups"(
	user_id   BIGINT NOT NULL,
	group_id  BIGINT NOT NULL,
	FOREIGN KEY (user_id) REFERENCES users(user_id),
	FOREIGN KEY (group_id) REFERENCES users(group_id),
);