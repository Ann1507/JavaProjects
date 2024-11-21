select distinct title,lastname,firstname,regiondescription as region
from employee
inner join  employeeterritory on employee.employeeId=employeeterritory.employeeId 
inner join territory on employeeterritory.territoryId=territory.territoryId
left join region on territory.regionId=region.regionId
where (regiondescription='Eastern' or regiondescription='Western') and title like '%Sales%'
order by regiondescription,title,lastname,firstname