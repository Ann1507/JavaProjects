select distinct model from bookings."airports_data" 
join bookings."flights" on airport_code=arrival_airport 
join bookings."aircrafts_data" on bookings."flights".aircraft_code=bookings."aircrafts_data".aircraft_code
where airport_code='YKS';