create table drivers (id  serial not null,
    available boolean not null,
    cab_number varchar(255),
    contact_number varchar(255) not null,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    user_name varchar(255) not null,
    current_location_id int4,
    primary key (id));

create table locations (id  serial not null,
    latitude float8 not null,
    longitude float8 not null,
    name varchar(255) not null,
    primary key (id));

create table trips (id  serial not null,
    amount float8 not null,
    booked_on timestamp,
    completed_on timestamp,
    distance int8 not null,
    started_on timestamp,
    status varchar(255),
    driver_id int4,
    from_location_id int4,
    to_location_id int4,
    user_id int8,
    primary key (id));

create table users (id  bigserial not null,
    contact_number varchar(30),
    country varchar(30),
    dob varchar(30),
    email varchar(50) not null,
    first_name varchar(30) not null,
    last_name varchar(30) not null,
    password varchar(255) not null,
    role varchar(255),
    salt varchar(200) not null,
    username varchar(30) not null,
    primary key (id));

alter table drivers add constraint DRIVERS_CURRENT_LOCATION_ID_FK foreign key (current_location_id) references locations;

alter table trips add constraint TRIPS_DRIVER_ID_FK foreign key (driver_id) references drivers;
alter table trips add constraint TRIPS_FROM_LOCATION_ID_FK foreign key (from_location_id) references locations;
alter table trips add constraint TRIPS_TO_LOCATION_ID_FK foreign key (to_location_id) references locations;
alter table trips add constraint TRIPS_USER_ID_FK foreign key (user_id) references users;
