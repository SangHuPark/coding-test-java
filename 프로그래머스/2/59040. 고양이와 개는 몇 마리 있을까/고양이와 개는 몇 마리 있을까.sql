select ANIMAL_TYPE, count(*) as count
from ANIMAL_INS
where ANIMAL_TYPE like 'cat' or ANIMAL_TYPE like 'dog'
group by ANIMAL_TYPE
order by left(ANIMAL_TYPE, 1);