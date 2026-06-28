select seat_no  from aircrafts_data
join flights on aircrafts_data.aircraft_code=flights.aircraft_code
join boarding_passes on flights.flight_id=boarding_passes.flight_id
group by aircrafts_data.aircraft_code, model, seat_no
having aircrafts_data.aircraft_code='733'
and count(seat_no) = (select count(seat_no) from aircrafts_data
join flights on aircrafts_data.aircraft_code=flights.aircraft_code
join boarding_passes on flights.flight_id=boarding_passes.flight_id
group by aircrafts_data.aircraft_code, model, seat_no
having aircrafts_data.aircraft_code='733'
order by count(seat_no) desc limit 1)
order by count(seat_no) desc