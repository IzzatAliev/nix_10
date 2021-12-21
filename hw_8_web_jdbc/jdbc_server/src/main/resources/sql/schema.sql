drop table if exists student_course;
drop table if exists students;
drop table if exists courses;

create table students
(
    id         bigint auto_increment
        primary key,
    created    datetime(6) null,
    updated    datetime(6) null,
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    age       int   not null
);

create table courses
(
    id               bigint auto_increment
        primary key,
    created          datetime(6)  null,
    updated          datetime(6)  null,
    course_name        varchar(255) not null,
    credit          int     not null,
    course_type     varchar(255) not null,
    description      text         null
);

create table student_course
(
    student_id bigint not null,
    course_id   bigint not null,
    primary key (student_id, course_id),
    foreign key (student_id) references students (id) on delete cascade,
    foreign key (course_id) references courses (id)
);
