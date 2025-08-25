create table courses(
    id serial constraint pk_courses_id primary key,
    name varchar(100) constraint nn_courses_name not null constraint uk_courses_name unique,
    description varchar(500) constraint nn_courses_description not null
)