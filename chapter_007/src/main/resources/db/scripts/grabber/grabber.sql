CREATE SEQUENCE IF NOT EXISTS joboffers_id_seq;
CREATE TABLE IF NOT EXISTS joboffers
(
    id integer NOT NULL DEFAULT nextval('joboffers_id_seq'::regclass),
    date timestamp without time zone,
    name text,
    author text,
    link text,
    text text,
    CONSTRAINT joboffers_pkey PRIMARY KEY (id)
);