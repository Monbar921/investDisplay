create table if not exists bank_account (
                                            id bigserial primary key ,
                                            interest numeric not null,
                                            end_date date not null ,
                                            bank varchar(32) not null,
                                            foreign key (id) references product(id)
)

create table if not exists share (
                                     id bigserial primary key ,
                                     code varchar(128) not null,
                                     country varchar(10) not null,
                                     sector varchar(32) not null,
                                     broker varchar(32) not null,
                                     foreign key (id) references product(id)
)

create table if not exists product (
                                       id bigserial primary key ,
                                       name varchar(128) not null,
                                       price numeric not null,
                                       quantity numeric not null,
                                       start_date date not null ,
                                       type varchar(10) not null,
                                       user_id bigserial references users(id)
)


alter table product alter column type TYPE varchar(15);