-- This will be auto-picked up by Spring Data JPA + H2
drop table if exists SAMPLE;

create table SAMPLE (
    id int auto_increment primary key,
    username varchar(200) not null,
    remarks varchar(500) default ''
);

insert into SAMPLE (username, remarks) values
    ('jensen', ''),
    ('jen', ''),
    ('jang', '');

