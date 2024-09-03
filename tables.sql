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

create table if not exists crypto (
                                     id bigserial primary key ,
                                     code varchar(128) not null,
                                     broker varchar(32) not null,
                                     foreign key (id) references product(id)
)

create table if not exists house (
                                      id bigserial primary key ,
                                      is_commercial bool not null,
                                      rent numeric,
                                      foreign key (id) references product(id)
)

create table if not exists refill (
                                      id bigserial primary key ,
                                      amount numeric not null,
                                      date date not null,
                                      product_id bigserial not null ,
                                      foreign key (product_id) references product(id)
)

create table if not exists product (
                                       id bigserial primary key ,
                                       name varchar(128) not null,
                                       buy_price numeric not null,
                                       current_price numeric not null,
                                       quantity numeric not null,
                                       start_date date not null ,
                                       type varchar(10) not null,
                                       user_id bigserial references users(id),
                                       refill_id bigserial references refill(id)
)


drop table refill
alter table product alter column type TYPE varchar(15);