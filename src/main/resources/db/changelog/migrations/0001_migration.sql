-- liquibase formatted sql
-- changeset marcus:0001.1.tb_customer
create table tb_customer
(
    id          bigint not null auto_increment,
    username    varchar(50),
    credit_card varchar(150),
    document    varchar(255),
    email       varchar(255),
    first_name  varchar(255),
    last_name   varchar(255),
    primary key (id)
) engine = InnoDB;

-- changeset marcus:0001.1.tb_customer_addresses
create table tb_customer_addresses
(
    main              bit,
    customer_model_id bigint not null,
    city              varchar(10),
    state             varchar(10),
    building_number   varchar(50),
    zipcode           varchar(50),
    address_id        varchar(255),
    complement        varchar(255),
    line              varchar(255)
) engine = InnoDB;

-- changeset marcus:0001.1.tb_customer_phones
create table tb_customer_phones
(
    area_code         integer,
    main              bit,
    number            integer,
    customer_model_id bigint not null,
    phone_id          varchar(255)
) engine = InnoDB;

-- changeset marcus:0001.1.constraints
alter table tb_customer_addresses
    add constraint unique_key_address_id unique (address_id);
alter table tb_customer_phones
    add constraint unique_key_phone_id unique (phone_id);
alter table tb_customer_addresses
    add constraint foreing_key_customer_addresses foreign key (customer_model_id) references tb_customer (id);
alter table tb_customer_phones
    add constraint foreing_key_customer_phones foreign key (customer_model_id) references tb_customer (id);
