-- 코드를 입력하세요
WITH
    T1 AS (
        SELECT
            DISTINCT(a.CAR_ID)
        FROM
            CAR_RENTAL_COMPANY_RENTAL_HISTORY a
            WHERE
                NOT EXISTS (
                    SELECT 1 FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY b
                    WHERE a.CAR_ID = b.CAR_ID
                    AND NOT (
                        b.START_DATE > '2022-11-30'
                        OR b.END_DATE < '2022-11-01'
                    )
                )
    ), T2 AS (
        SELECT
            t.CAR_ID,
            r.CAR_TYPE,
            (r.DAILY_FEE * 30) AS FEE
        FROM
            T1 t
            JOIN CAR_RENTAL_COMPANY_CAR r
              ON t.CAR_ID = r.CAR_ID
        WHERE
            r.CAR_TYPE IN ('세단', 'SUV')
    ), T3 AS (
        SELECT
            tt.CAR_ID,
            tt.CAR_TYPE,
            ROUND(tt.FEE - tt.FEE * (p.DISCOUNT_RATE / 100), 0) AS FEE
        FROM
            T2 tt
            JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN p
              ON p.CAR_TYPE = tt.CAR_TYPE
        WHERE
            p.DURATION_TYPE = '30일 이상'
    )
SELECT
    CAR_ID,
    CAR_TYPE,
    FEE
FROM
    T3
WHERE
    FEE >= 500000
    AND FEE < 2000000
ORDER BY
    FEE DESC,
    CAR_TYPE,
    CAR_ID DESC;