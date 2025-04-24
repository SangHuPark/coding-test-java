# with rent_30 as ( 
#     select *
#     from CAR_RENTAL_COMPANY_CAR car
#     join CAR_RENTAL_COMPANY_RENTAL_HISTORY his on car.car_id = his.car_id
#     where car.car_type like '세단' or car.car_type like 'SUV'
#     and date_format(his.start_date, "%Y-%m") >= '2022-12' 
#     or date_format(his.end_date, "%Y-%m") < '2022-11');


# select distinct(his.car_id), car_type, car.daily_fee * 30 as daily_fee
# from CAR_RENTAL_COMPANY_CAR car
# join CAR_RENTAL_COMPANY_RENTAL_HISTORY his on car.car_id = his.car_id
# where (car.car_type like '세단' or car.car_type like 'SUV')
#     and car.car_id not in ( select car_id
#                         from CAR_RENTAL_COMPANY_RENTAL_HISTORY
#                         where start_date <= '2022-11-30' 
#                             and end_date >= '2022-11-01')
#     and ((car.daily_fee * 30) >= 500000 and (car.daily_fee * 30) < 2000000);
    
select rc.car_id, p.car_type, FLOOR(daily_fee * 30 * (1 - CAST(REPLACE(discount_rate, '%', '') AS DECIMAL(5,2)) / 100)) AS fee
from CAR_RENTAL_COMPANY_DISCOUNT_PLAN p
join (select distinct(his.car_id), car_type, car.daily_fee * 30 as daily_fee
    from CAR_RENTAL_COMPANY_CAR car
    join CAR_RENTAL_COMPANY_RENTAL_HISTORY his on car.car_id = his.car_id
    where (car.car_type like '세단' or car.car_type like 'SUV')
        and car.car_id not in ( select car_id
                            from CAR_RENTAL_COMPANY_RENTAL_HISTORY
                            where start_date <= '2022-11-30' 
                                and end_date >= '2022-11-01')
        and ((car.daily_fee * 30) >= 500000 
             and (car.daily_fee * 30) < 2000000)) rc
on p.car_type = rc.car_type
where left(p.duration_type, 2) = '30';

SELECT 
    c.car_id, 
    c.car_type, 
    FLOOR(c.daily_fee * 30 * (1 - CAST(REPLACE(discount_rate, '%', '') AS DECIMAL(5,2)) / 100)) AS fee
FROM CAR_RENTAL_COMPANY_CAR c
JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN p
    ON c.car_type = p.car_type AND p.duration_type = '30일 이상'
WHERE c.car_type IN ('세단', 'SUV')
  AND c.car_id NOT IN (
      SELECT car_id
      FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
      WHERE start_date <= '2022-11-30' AND end_date >= '2022-11-01'
  )
  AND FLOOR(c.daily_fee * 30 * (1 - p.discount_rate / 100)) BETWEEN 500000 AND 1999999
ORDER BY fee DESC, car_type ASC, car_id DESC;
