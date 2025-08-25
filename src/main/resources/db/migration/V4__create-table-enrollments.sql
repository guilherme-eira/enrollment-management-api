create table enrollments(
    id serial constraint pk_enrollments_id primary key,
    student_id int constraint fk_enrollments_student_id references people(id),
    subject_id int constraint fk_enrollments_subject_id references subjects(id),
    status varchar(9) constraint nn_enrollments_status not null,
    constraint ck_enrollments_status check (status in ('APROVADO', 'REPROVADO', 'CURSANDO', 'TRANCADO'))
)