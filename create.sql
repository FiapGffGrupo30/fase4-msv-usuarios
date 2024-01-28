create table tb_customer (id bigint not null auto_increment, username varchar(50), credit_card varchar(150), document varchar(255), email varchar(255), first_name varchar(255), last_name varchar(255), primary key (id)) engine=InnoDB;
create table tb_customer_addresses (main bit, customer_model_id bigint not null, city varchar(10), state varchar(10), building_number varchar(50), zipcode varchar(50), address_id varchar(255), complement varchar(255), line varchar(255)) engine=InnoDB;
create table tb_customer_phones (area_code integer, main bit, number integer, customer_model_id bigint not null, phone_id varchar(255)) engine=InnoDB;
alter table tb_customer_addresses add constraint UK_fja9fmw2roco5hac8s0am2t7u unique (address_id);
alter table tb_customer_phones add constraint UK_k8px4wnh5hpard6kap8e48s4b unique (phone_id);
alter table tb_customer_addresses add constraint FK232gg25857rj756qoh63uvmin foreign key (customer_model_id) references tb_customer (id);
alter table tb_customer_phones add constraint FKm821qyfrdfnhgu5l5rdctw3i5 foreign key (customer_model_id) references tb_customer (id);
