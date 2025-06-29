select ad.aircraft_code , ad.model,(
select seat_no from aircrafts_data 
join flights on aircrafts_data.aircraft_code=flights.aircraft_code
join boarding_passes on flights.flight_id=boarding_passes.flight_id
group by aircrafts_data.aircraft_code, model, seat_no
having aircrafts_data.aircraft_code=ad.aircraft_code
order by count(seat_no) desc limit 1
) as seat_no from aircrafts_data as ad
order by aircraft_code

--самый часто используемый самолет
--два варианта по количеству пассажиров и по количеству полетов

--рейтинг самолетов наверху часто используемые самолеты ,ниже реже используемые самолеты