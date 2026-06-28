select ad.aircraft_code, ad.model, count(*) as totalpax from aircrafts_data ad 
inner join flights f on ad.aircraft_code =f.aircraft_code 
inner join ticket_flights tf on f.flight_id =tf.flight_id 
group by ad.aircraft_code,ad.model 
order by totalpax desc