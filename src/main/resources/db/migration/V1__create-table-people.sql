create table people(
    id serial constraint pk_people_id primary key,
    name varchar(100) constraint nn_people_name not null,
    email varchar(200) constraint nn_people_email not null,
    document char(11) constraint nn_people_document not null constraint uk_people_document unique,
    active boolean,
    role char(9) constraint nn_people_role not null constraint ck_people_role check (role in ('ESTUDANTE', 'PROFESSOR'))
)