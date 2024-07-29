create table mentor(
mentor_seq number,
id varchar2(15),
pwd varchar2(20),
department varchar2(30),
email varchar2(30),
phone varchar2(15),
status number);

CREATE TABLE mentee(
mentee_seq  number,
pwd  varchar2(20),
email  varchar2(30),
phone  varchar2(15));


create table waiting(

mentor_seq number,
mentee_seq number

);

create table mentoring(

mentor_seq number,
mentee_seq number

);