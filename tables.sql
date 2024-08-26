create table if not exists bank_account (
                                            id bigserial primary key ,
                                            interest numeric,
                                            start_date date,
                                            end_date date,
                                            foreign key (id) references product(id)
)

drop table if exists bank_account