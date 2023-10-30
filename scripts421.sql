-- Возраст студента не меньше 16 лет.
alter table student
add constraint student_age_constraint check(age > 16);


-- Имена студентов уникальны и не равны нулю.
alter table student
add constraint student_name_constraint unique(name);

alter table student
alter column name set not null;


-- Пара название и цвет должны быть уникальными.
alter table faculty
add constraint faculty_name_and_color_constraint unique(name, color);


-- Новому студенту автоматически присваиваться 20 лет.
alter table student
alter column age set default 20;