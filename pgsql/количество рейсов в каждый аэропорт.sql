select  airport_name, count(airport_name) as flights_number from airports_data 
join flights on airport_code=arrival_airport
group by airport_name
order by flights_number desc, airport_name 


