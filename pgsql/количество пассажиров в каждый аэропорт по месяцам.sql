select airport_name,count(airport_name) as pax_no, extract(month from actual_arrival ) as m from airports_data 
join flights on airport_code=arrival_airport
join ticket_flights on flights.flight_id=ticket_flights.flight_id
join boarding_passes on boarding_passes.flight_id=ticket_flights.flight_id and boarding_passes.ticket_no=ticket_flights.ticket_no

group by airport_name, m
order by pax_no desc , m
 

