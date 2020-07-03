create table user_auth (id  serial not null,
    access_token varchar(500) not null,
    expires_at timestamp not null,
    login_at timestamp not null,
    logout_at timestamp,
    user_id int8,
    primary key (id));

alter table user_auth add constraint USER_AUTH_USER_ID_FK foreign key (user_id) references users on delete cascade;