# SELECT MONTH(START_DATE) AS MONTH, CAR_ID AS CAR_ID, COUNT(*) AS RECORDS
# FROM (SELECT *
#       FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
#       WHERE DATE(START_DATE) >= '2022-08-11' 
#             AND DATE(START_DATE) < '2022-11-01'
#         GROUP BY CAR_ID
#         HAVING COUNT(HISTORY_ID) >= 5) s
# GROUP BY MONTH, CAR_ID
# HAVING COUNT(HISTORY_ID) > 0
# ORDER BY MONTH, CAR_ID DESC;

select month(start_date), car_id, count(history_id) 'records'
from car_rental_company_rental_history
where car_id in (
    select car_id
    from car_rental_company_rental_history
    where date(start_date) >= '2022-08-01' and date(start_date) < '2022-11-01'
    group by car_id
    having count(history_id) >= 5
) and date(start_date) >= '2022-08-01' and date(start_date) < '2022-11-01'
group by car_id, month(start_date)
order by month(start_date), car_id desc