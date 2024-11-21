select title, count(title) as num
from employee 
group by title
having num>1
order by title
