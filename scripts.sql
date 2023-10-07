select *
from student s
where s.age between 10 and 20;


select s."name"
from student s;


select *
from student s
where lower("name") like '%%о%%';


select *
from student s
where s.age < s.id;


select *
from student s
order by age;