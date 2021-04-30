create table if not exists students
(
    id          int auto_increment
        primary key,
    first_name  varchar(50) not null,
    second_name varchar(50) not null,
    middle_name varchar(50) null,
    birthdate   date        not null,
    `group`     varchar(20) not null,
    student_id  varchar(20) not null,
    constraint students_student_id_uindex
        unique (student_id)
);