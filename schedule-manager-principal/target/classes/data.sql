INSERT INTO sec_role (id, name) values ('1', 'ROLE_TEACHER') ON CONFLICT DO NOTHING;
INSERT INTO sec_role (id, name) values ('2', 'ROLE_ADMIN') ON CONFLICT DO NOTHING;
