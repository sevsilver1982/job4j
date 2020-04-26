DROP TABLE IF EXISTS public.product;
DROP TABLE IF EXISTS public.type;

DROP SEQUENCE IF EXISTS type_id_seq;
DROP SEQUENCE IF EXISTS product_id_seq;

CREATE SEQUENCE type_id_seq;
CREATE SEQUENCE product_id_seq;

CREATE TABLE type
(
    id integer NOT NULL DEFAULT nextval('type_id_seq'::regclass),
    name text,
	CONSTRAINT type_pkey PRIMARY KEY (id)
);

CREATE TABLE public.product
(
    id integer NOT NULL DEFAULT nextval('product_id_seq'::regclass),
    name text,
    type_id integer REFERENCES type (id),
    expired_date date,
	quantity numeric,
    price numeric,
    CONSTRAINT product_pkey PRIMARY KEY (id)
);

INSERT INTO public.type(name) VALUES ('Сыр');
INSERT INTO public.type(name) VALUES ('Мороженое');
INSERT INTO public.type(name) VALUES ('Молоко');

DO $$
DECLARE ptype INT;
BEGIN
	ptype = (SELECT id FROM type WHERE name = 'Сыр');
	INSERT INTO product(name, type_id, expired_date, quantity, price) VALUES ('Голландский', ptype, '20221201', 10, 4000);
	INSERT INTO product(name, type_id, expired_date, quantity, price) VALUES ('Брынза', ptype, '20221001', 2, 3000);
	INSERT INTO product(name, type_id, expired_date, quantity, price) VALUES ('Российский', ptype, '20220501', 7, 2000);
	INSERT INTO product(name, type_id, expired_date, quantity, price) VALUES ('Домашний', ptype, '20220101', 130, 1000);
	
	ptype = (SELECT id FROM type WHERE name = 'Мороженое');
	INSERT INTO product(name, type_id, expired_date, quantity, price) VALUES ('Фруктовый лёд', ptype, '20201201', 100, 100);
	INSERT INTO product(name, type_id, expired_date, quantity, price) VALUES ('Пломбир', ptype, '20201101', 9, 200);
	INSERT INTO product(name, type_id, expired_date, quantity, price) VALUES ('Эскимо', ptype, '20201001', 120, 300);
	INSERT INTO product(name, type_id, expired_date, quantity, price) VALUES ('Российское мороженое', ptype, '20201001', 130, 150);
	
	ptype = (SELECT id FROM type WHERE name = 'Молоко');
	INSERT INTO product(name, type_id, expired_date, quantity, price) VALUES ('Кедровое', ptype, '20200501', 100, 400);
	INSERT INTO product(name, type_id, expired_date, quantity, price) VALUES ('Кокосовое', ptype, '20200510', 110, 300);
	INSERT INTO product(name, type_id, expired_date, quantity, price) VALUES ('Кунжутное', ptype, '20200515', 120, 200);
	INSERT INTO product(name, type_id, expired_date, quantity, price) VALUES ('Овсяное', ptype, '20200530', 130, 100);
END $$;


--1. Написать запрос получение всех продуктов с типом "СЫР"
SELECT type.*, product.* FROM product JOIN type ON type.id = product.type_id WHERE lower(type.name) = 'сыр';

--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженое"
SELECT * FROM product WHERE lower(name) LIKE '%мороженое%';
		
--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
SELECT * FROM product WHERE expired_date BETWEEN '20200515' AND '20200530';

--4. Написать запрос, который выводит самый дорогой продукт.
SELECT * FROM product ORDER BY price DESC LIMIT 1;

--5. Написать запрос, который выводит количество всех продуктов определенного типа.
SELECT COUNT(*) FROM product JOIN type ON type.id = product.type_id WHERE lower(type.name) = 'сыр';

--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
SELECT product.* FROM product JOIN type ON type.id = product.type_id WHERE lower(type.name) = 'сыр'
UNION ALL
SELECT product.* FROM product JOIN type ON type.id = product.type_id WHERE lower(type.name) = 'молоко'
ORDER BY name ASC;

--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.  
SELECT type.name FROM product JOIN type ON type.id = product.type_id WHERE product.quantity < 10 GROUP BY type.name;

--8. Вывести все продукты и их тип.
SELECT type.*, product.* FROM product JOIN type ON type.id = product.type_id;