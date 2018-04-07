create table if not exists workers
(
	id serial not null
		constraint workers_id_pk
			primary key,
	name varchar(64) not null,
	surname varchar(64) not null,
	birthdate date not null
)
;

create table if not exists departments
(
	id serial not null
		constraint departments_id_pk
			primary key,
	name varchar(64) not null
)
;

create table if not exists salaries
(
	id serial not null
		constraint salaries_id_pk
			primary key,
	quantity integer not null,
	worker_id integer not null
		constraint salaries_workers_id_fk
			references workers
)
;

create table if not exists workplaces
(
	id serial not null
		constraint workplaces_id_pk
			primary key,
	worker_id integer not null
		constraint workplaces_workers_id_fk
			references workers,
	department_id integer not null
		constraint workplaces_departments_id_fk
			references departments
)
;

