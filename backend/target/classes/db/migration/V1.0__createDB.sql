CREATE SEQUENCE hibernate_sequence
	INCREMENT BY 1
	MINVALUE 1
	START 1;
-- create table item
CREATE TABLE item (
	item_id int8 NOT NULL,
	description varchar(255) NULL,
	item_cost int8 NULL,
	title varchar(255) NULL,
	CONSTRAINT item_pkey PRIMARY KEY (item_id)
);
-- create table users
CREATE TABLE users (
	userid varchar(255) NOT NULL,
	available_loot_boxes int8 NOT NULL,
	email varchar(255) NULL,
	enabled bool NOT NULL,
	hashed_password varchar(255) NULL,
	in_game_currency int8 NOT NULL,
	last_name varchar(128) NULL,
	name varchar(128) NULL,
	CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email),
	CONSTRAINT users_pkey PRIMARY KEY (userid)
);

create table users_roles
(
    users_userid varchar(255) not null,
    roles        varchar(255)
);
alter table users_roles
    add constraint FKnqgxij5udu4xrsqju9dtbc8pr foreign key (users_userid) references users;

CREATE TABLE copy (
	id int8 NOT NULL,
	buy_date date NOT NULL,
	description varchar(500) NULL,
	item_cost int8 NOT NULL,
	loot_boxes int8 NOT NULL,
	title varchar(200) NULL,
	item_id int8 NULL,
	userid varchar(255) NULL,
	CONSTRAINT copy_pkey PRIMARY KEY (id),
	CONSTRAINT fkc0bn2wfbeeav4iga80fji78qi FOREIGN KEY (userid) REFERENCES users(userid),
	CONSTRAINT fkhdwwcwd5iff37fjek6cgdtxni FOREIGN KEY (item_id) REFERENCES item(item_id)
);
