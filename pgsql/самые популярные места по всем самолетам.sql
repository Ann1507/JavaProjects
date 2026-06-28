select ad.aircraft_code, seat_no from  aircrafts_data ad --извлекаем все самолеты
join lateral ( --объединяем с подзапросом который по каждому самолету выдает самые популярные места
select aircrafts_data.aircraft_code as ac, seat_no from aircrafts_data --подзапрос который ищет места с заданным количеством посадочных талонов
join flights on aircrafts_data.aircraft_code=flights.aircraft_code
join boarding_passes on flights.flight_id=boarding_passes.flight_id
group by aircrafts_data.aircraft_code, model, seat_no
having aircrafts_data.aircraft_code=ad.aircraft_code --условие поиска по конкретному самолету
and count(seat_no) = (select count(seat_no) from aircrafts_data --условие равенства количества мест максимальному который находится подзапросом
join flights on aircrafts_data.aircraft_code=flights.aircraft_code
join boarding_passes on flights.flight_id=boarding_passes.flight_id
group by aircrafts_data.aircraft_code, model, seat_no
having aircrafts_data.aircraft_code=ad.aircraft_code
order by count(seat_no) desc limit 1) -- конец ползапроса который находит максимальное количество на место
order by count(seat_no) desc -- конец подзапроса который находит места с максимальным количеством
) on ad.aircraft_code  = ac
order by ad.aircraft_code, seat_no
