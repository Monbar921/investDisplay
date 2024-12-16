create table if not exists users (
                                     id bigserial primary key ,
                                     username varchar(128) not null
);

create table if not exists product (
                                       id bigserial primary key ,
                                       name varchar(128) not null,
                                       buy_price numeric not null,
                                       current_price numeric not null,
                                       quantity numeric not null,
                                       start_date date not null ,
                                       type varchar(16) not null,
                                       user_id bigserial references users(id)
);

create table if not exists refill (
                                      id bigserial primary key ,
                                      amount numeric not null,
                                      date date not null,
                                      product_id bigserial not null,
                                      foreign key (product_id) references product(id)
);

create table if not exists bank_account (
                                            id bigserial primary key ,
                                            interest numeric not null,
                                            end_date date,
                                            bank varchar(32) not null,
                                            foreign key (id) references product(id)
);

create table if not exists share (
                                     id bigserial primary key ,
                                     code varchar(128) not null,
                                     country varchar(10) not null,
                                     sector varchar(32) not null,
                                     broker varchar(32) not null,
                                     foreign key (id) references product(id)
);

create table if not exists crypto (
                                     id bigserial primary key ,
                                     code varchar(128) not null,
                                     broker varchar(32) not null,
                                     foreign key (id) references product(id)
);

create table if not exists realty (
                                      id bigserial primary key ,
                                      is_commercial bool not null,
                                      rent numeric,
                                      foreign key (id) references product(id)
);

--alter table refill add foreign key (product_id) references product(id);

-- drop table house cascade;
--alter table product drop constraint product_refill_id_fkey

--ALTER TABLE bank_account ALTER COLUMN end_date DROP  NOT NULL;