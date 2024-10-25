select title,lastname,firstname,territorydescription,regiondescription
from employee
inner join  employeeterritory on employee.employeeId=employeeterritory.employeeId 
inner join territory on employeeterritory.territoryId=territory.territoryId
left join region on territory.regionId=region.regionId
order by regiondescription,territorydescription,title,lastname,firstname