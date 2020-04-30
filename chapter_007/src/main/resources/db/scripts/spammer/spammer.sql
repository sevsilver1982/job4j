CREATE SEQUENCE IF NOT EXISTS users_id_seq;
CREATE TABLE IF NOT EXISTS users
(
    id integer NOT NULL DEFAULT nextval('users_id_seq'::regclass),
    name text,
    email text,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);