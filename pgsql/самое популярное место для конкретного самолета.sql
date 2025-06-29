select aircrafts_data.aircraft_code, model, seat_no, count(seat_no) as seat_counts from aircrafts_data 
join flights on aircrafts_data.aircraft_code=flights.aircraft_code
join boarding_passes on flights.flight_id=boarding_passes.flight_id
group by aircrafts_data.aircraft_code, model, seat_no
having aircrafts_data.aircraft_code='773'
order by seat_counts desc limit 1

