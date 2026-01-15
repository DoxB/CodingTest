-- 코드를 작성해주세요
WITH
    T1 AS (
        SELECT
            YEAR(YM) AS `YEAR`,
            PM_VAL1,
            PM_VAL2
        FROM
            AIR_POLLUTION
        WHERE
            LOCATION1 = '경기도'
            AND LOCATION2 = '수원'
    )
SELECT
    `YEAR`,
    ROUND(AVG(PM_VAL1), 2) AS 'PM10',
    ROUND(AVG(PM_VAL2), 2) AS 'PM2.5'
FROM
    T1
GROUP BY
    `YEAR`
ORDER BY
    `YEAR`