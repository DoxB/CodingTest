-- 코드를 입력하세요
WITH
    T1 AS (
        SELECT
            HISTORY_ID,
            CAR_ID,
            START_DATE,
            END_DATE
        FROM
            CAR_RENTAL_COMPANY_RENTAL_HISTORY
        WHERE
            CAR_ID IN (
                SELECT
                    CAR_ID
                FROM
                    CAR_RENTAL_COMPANY_CAR
                WHERE
                    CAR_TYPE = '트럭'
            )
    ), T2 AS (
        SELECT
            HISTORY_ID,
            CAR_ID,
            DATEDIFF(END_DATE, START_DATE) + 1 AS DURATION
        FROM
            T1
    ), T3 AS (
        SELECT
            CASE
                WHEN DURATION_TYPE = '7일 이상' THEN 7
                WHEN DURATION_TYPE = '30일 이상' THEN 30
                WHEN DURATION_TYPE = '90일 이상' THEN 90
            END DURATION_TYPE,
            DISCOUNT_RATE
        FROM
            CAR_RENTAL_COMPANY_DISCOUNT_PLAN
        WHERE
            CAR_TYPE = '트럭'
    )
SELECT
    HISTORY_ID,
    CASE
        WHEN DURATION < 7 THEN (DURATION * (
                                    SELECT
                                        DAILY_FEE
                                    FROM
                                        CAR_RENTAL_COMPANY_CAR c
                                    WHERE c.CAR_ID = t.CAR_ID
                                ))
        WHEN DURATION < 30 THEN ROUND((DURATION * (
                                    SELECT
                                        DAILY_FEE
                                    FROM
                                        CAR_RENTAL_COMPANY_CAR c
                                    WHERE c.CAR_ID = t.CAR_ID
                                ) * (100 - (
                                    SELECT
                                        DISCOUNT_RATE
                                    FROM
                                        T3
                                    WHERE
                                        DURATION_TYPE = 7
                                )) / 100), 0)
        WHEN DURATION < 90 THEN ROUND((DURATION * (
                                    SELECT
                                        DAILY_FEE
                                    FROM
                                        CAR_RENTAL_COMPANY_CAR c
                                    WHERE c.CAR_ID = t.CAR_ID
                                ) * (100 - (
                                    SELECT
                                        DISCOUNT_RATE
                                    FROM
                                        T3
                                    WHERE
                                        DURATION_TYPE = 30
                                )) / 100), 0)
        ELSE ROUND((DURATION * (
                        SELECT
                            DAILY_FEE
                        FROM
                            CAR_RENTAL_COMPANY_CAR c
                        WHERE c.CAR_ID = t.CAR_ID
                    ) * (100 - (
                        SELECT
                            DISCOUNT_RATE
                        FROM
                            T3
                        WHERE
                            DURATION_TYPE = 90
                    )) / 100), 0)
    END AS FEE
FROM
    T2 t
ORDER BY
    FEE DESC,
    HISTORY_ID DESC