create table subjects(
    id serial constraint pk_subjects_id primary key,
    name varchar(100) constraint nn_subjects_name not null,
    constraint uk_subjects_name unique (name),
    description varchar(500) constraint nn_subjects_description not null,
    start_date date constraint nn_subjects_start_date not null,
    course_id int constraint fk_subjects_course_id references courses(id),
    teacher_id int constraint fk_subjects_teacher_id references people(id)
)