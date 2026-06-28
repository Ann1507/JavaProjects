select ad.aircraft_code, ad.model, count(*) as totalflight from aircrafts_data ad 
inner join flights f on ad.aircraft_code =f.aircraft_code 
group by ad.aircraft_code,ad.model 
order by totalflight desc