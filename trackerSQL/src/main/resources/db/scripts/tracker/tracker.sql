CREATE TABLE IF NOT EXISTS items
(
    id character varying(36) NOT NULL,
    name character varying,
    CONSTRAINT items_pkey PRIMARY KEY (id)
);