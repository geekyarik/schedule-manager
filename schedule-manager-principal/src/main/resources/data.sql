INSERT INTO sec_role (id, name) values ('1', 'ROLE_TEACHER') ON CONFLICT DO NOTHING;
INSERT INTO sec_role (id, name) values ('2', 'ROLE_ADMIN') ON CONFLICT DO NOTHING;

INSERT INTO sec_users_roles (user_id, role_id) values ('1', '1') ON CONFLICT DO NOTHING;
INSERT INTO sec_users_roles (user_id, role_id) values ('1', '2') ON CONFLICT DO NOTHING;
