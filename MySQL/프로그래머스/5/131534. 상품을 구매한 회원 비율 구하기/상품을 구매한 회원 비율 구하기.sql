-- 코드를 입력하세요
WITH
    T1 AS (
        SELECT
            USER_ID
        FROM
            USER_INFO
        WHERE
            YEAR(JOINED) = 2021
    ), T2 AS (
        SELECT DISTINCT
            o.USER_ID,
            YEAR(o.SALES_DATE) AS `YEAR`,
            MONTH(o.SALES_DATE) AS `MONTH`
        FROM
            ONLINE_SALE o
        WHERE
            EXISTS (
                SELECT 1 FROM T1 t
                WHERE t.USER_ID = o.USER_ID
            )
    ), T3 AS (
        SELECT
            `YEAR`,
            `MONTH`,
            COUNT(USER_ID) AS PURCHASED_USERS
        FROM
            T2
        GROUP BY
            `YEAR`,
            `MONTH`
    )
SELECT
    `YEAR`,
    `MONTH`,
    PURCHASED_USERS,
    ROUND(PURCHASED_USERS / (
        SELECT COUNT(USER_ID) FROM T1), 1) AS PUCHASED_RATIO
FROM
    T3
ORDER BY
    `YEAR`,
    `MONTH`;