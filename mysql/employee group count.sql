select region, count(region) as employee_num from  (
	select distinct regiondescription as region,lastname,firstname
	from employee
	inner join  employeeterritory on employee.employeeId=employeeterritory.employeeId 
	inner join territory on employeeterritory.territoryId=territory.territoryId
	left join region on territory.regionId=region.regionId
	-- where title like '%Sales%'
    where title REGEXP '^\.*Sales\.*$'
) as t1 
group by region
order by region