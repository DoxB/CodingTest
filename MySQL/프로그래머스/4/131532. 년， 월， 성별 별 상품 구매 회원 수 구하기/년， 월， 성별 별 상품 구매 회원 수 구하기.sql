-- 코드를 입력하세요
WITH
    T1 AS (
        SELECT
            o.USER_ID,
            o.SALES_DATE,
            u.GENDER
        FROM
            ONLINE_SALE o
            JOIN USER_INFO u ON o.USER_ID = u.USER_ID
        WHERE
            u.GENDER IS NOT NULL
    )
SELECT
    YEAR(SALES_DATE) AS `YEAR`,
    MONTH(SALES_DATE) AS `MONTH`,
    GENDER,
    COUNT(DISTINCT USER_ID) AS USERS
FROM
    T1
GROUP BY
    YEAR(SALES_DATE),
    MONTH(SALES_DATE),
    GENDER
ORDER BY
    `YEAR`, `MONTH`, GENDER;