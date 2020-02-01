ALTER TABLE  groups  ADD  FOREIGN KEY (moderator_id) REFERENCES users(user_id);
ALTER TABLE  groups  ADD  FOREIGN KEY (last_event_id) REFERENCES events(event_id);
ALTER TABLE  users_groups ADD FOREIGN KEY (user_id) REFERENCES users(user_id);
ALTER TABLE  users_groups ADD FOREIGN KEY (group_id) REFERENCES groups(group_id);
ALTER TABLE  events ADD FOREIGN KEY (voting_id) REFERENCES votings(voting_id);
ALTER TABLE  events	ADD FOREIGN KEY (group_id) REFERENCES groups(group_id);

