-- liquibase formatted sql
-- changeset marcus:0001.1.tb_customer
create table tb_customer
(
    id          UUID not null,
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
    customer_model_id UUID not null,
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
    customer_model_id UUID not null,
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

-- changeset marcus:0001.1.data_tb_customer
INSERT INTO tb_customer (id, username, credit_card, document, email, first_name, last_name)
VALUES ('c5f39f63-9d3a-4a79-8149-f59a017fd0ab', 'user1', '1234 5678 9012 3456', '12345678901', 'user1@example.com',
        'John', 'Doe'),
       ('77054809-673b-4944-8b18-d3358d9b4df6', 'user2', '2345 6789 0123 4567', '23456789012', 'user2@example.com',
        'Jane', 'Smith'),
       ('8cc936b6-fd0c-4c41-ab5c-8ab851077961', 'user3', '3456 7890 1234 5678', '34567890123', 'user3@example.com',
        'Michael', 'Johnson'),
       ('1d242ce4-9a51-4055-86cc-c78b2e01d42c', 'user4', '4567 8901 2345 6789', '45678901234', 'user4@example.com',
        'Emma', 'Wilson'),
       ('91bbb7f7-a66a-44f0-ab27-40f470b43791', 'user5', '5678 9012 3456 7890', '56789012345', 'user5@example.com',
        'David', 'Brown'),
       ('a41e94f0-3f09-43ec-9c44-60904db70843', 'user6', '6789 0123 4567 8901', '67890123456', 'user6@example.com',
        'Olivia', 'Martinez'),
       ('eb2b52f8-469a-4662-a1f7-8fdb601d7cf9', 'user7', '7890 1234 5678 9012', '78901234567', 'user7@example.com',
        'James', 'Taylor'),
       ('0ac89ae1-77a9-464e-b39c-5fd11c84f8c7', 'user8', '8901 2345 6789 0123', '89012345678', 'user8@example.com',
        'Sophia', 'Anderson'),
       ('d606864f-567e-417d-a576-679c12a84cfc', 'user9', '9012 3456 7890 1234', '90123456789', 'user9@example.com',
        'Ethan', 'Thomas'),
       ('f50855a9-4471-4319-aff2-131ab2a4c0ff', 'user10', '0123 4567 8901 2345', '01234567890', 'user10@example.com',
        'Ava', 'Hernandez');

-- changeset marcus:0001.1.data_tb_customer_addresses
INSERT INTO tb_customer_addresses (main, customer_model_id, city, state, building_number, zipcode, address_id,
                                   complement, line)
VALUES (1, 'c5f39f63-9d3a-4a79-8149-f59a017fd0ab', 'City1', 'State1', '123', '12345-678',
        '81a4f1e5-8ab7-424d-bde6-ec6369ce6ea9', 'Complement1', 'Address line 1'),
       (1, '77054809-673b-4944-8b18-d3358d9b4df6', 'City2', 'State2', '456', '23456-789',
        'e4d62cac-fc73-40bf-b912-4612bbc71c3c', 'Complement2', 'Address line 2'),
       (1, '8cc936b6-fd0c-4c41-ab5c-8ab851077961', 'City3', 'State3', '789', '34567-890',
        'a59a4610-9339-4e62-95d4-4b8997c8f35e', 'Complement3', 'Address line 3'),
       (1, '1d242ce4-9a51-4055-86cc-c78b2e01d42c', 'City4', 'State4', '912', '45678-901',
        'c93300a1-677b-4149-9e3e-3e17b5e62a9e', 'Complement4', 'Address line 4'),
       (1, '91bbb7f7-a66a-44f0-ab27-40f470b43791', 'City5', 'State5', '345', '56789-012',
        'ee8a1bec-5620-4dd4-b007-9e14f00642b9', 'Complement5', 'Address line 5'),
       (1, 'a41e94f0-3f09-43ec-9c44-60904db70843', 'City6', 'State6', '678', '67890-123',
        'b11087a8-0ae6-4a26-b415-60fc45db6e60', 'Complement6', 'Address line 6'),
       (1, 'eb2b52f8-469a-4662-a1f7-8fdb601d7cf9', 'City7', 'State7', '901', '78901-234',
        '6c0757a8-b729-41eb-9fb9-cf056aa5ef03', 'Complement7', 'Address line 7'),
       (1, '0ac89ae1-77a9-464e-b39c-5fd11c84f8c7', 'City8', 'State8', '234', '89012-345',
        'da2b963b-b517-40a8-a933-a25d1eb5570a', 'Complement8', 'Address line 8'),
       (1, 'd606864f-567e-417d-a576-679c12a84cfc', 'City9', 'State9', '567', '90123-456',
        '05568ccc-6c86-4fa1-ba58-b0512a5e4f61', 'Complement9', 'Address line 9'),
       (1, 'f50855a9-4471-4319-aff2-131ab2a4c0ff', 'City10', 'State10', '890', '01234-567',
        'a6254fe7-9b26-44da-b0a0-6cc58de80e4f', 'Complement10', 'Address line 10');

-- changeset marcus:0001.1.data_tb_customer_phones
INSERT INTO tb_customer_phones (area_code, main, number, customer_model_id, phone_id)
VALUES (123, 1, 123456789, 'c5f39f63-9d3a-4a79-8149-f59a017fd0ab', '66217744-8a30-4df2-b2cd-f63c498755ff'),
       (234, 1, 234567890, '77054809-673b-4944-8b18-d3358d9b4df6', '0884a28c-c853-4c43-884f-214f2101d8e8'),
       (345, 1, 345678901, '8cc936b6-fd0c-4c41-ab5c-8ab851077961', '187e6c34-39f1-4017-b189-a07431b91826'),
       (456, 1, 456789012, '1d242ce4-9a51-4055-86cc-c78b2e01d42c', 'b199d7f3-4c9d-41af-9382-bc32a1d4954a'),
       (567, 1, 567890123, '91bbb7f7-a66a-44f0-ab27-40f470b43791', 'ff8be526-d331-45e4-97ac-2d1032a2ae84'),
       (678, 1, 678901234, 'a41e94f0-3f09-43ec-9c44-60904db70843', '5e1b8ba0-3942-453b-8cb1-9336cee7b68a'),
       (789, 1, 789012345, 'eb2b52f8-469a-4662-a1f7-8fdb601d7cf9', '4312b9ba-23cc-4fb0-957d-ec7573e2e700'),
       (890, 1, 890123456, '0ac89ae1-77a9-464e-b39c-5fd11c84f8c7', 'c88e830a-caed-4e60-9f94-2137a98bd3cc'),
       (901, 1, 901234567, 'd606864f-567e-417d-a576-679c12a84cfc', '06d254ec-fdce-496c-86e0-3b64cefafe73'),
       (123, 1, 1234567890, 'f50855a9-4471-4319-aff2-131ab2a4c0ff', 'a533daa1-dc33-4e7f-928e-ae66588b8de2');