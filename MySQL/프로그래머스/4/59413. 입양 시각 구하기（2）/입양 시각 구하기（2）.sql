-- 코드를 입력하세요
WITH
    RECURSIVE T1 AS (
        SELECT
            0 AS `HOUR`
        UNION ALL
        SELECT
            `HOUR` + 1
        FROM T1
        WHERE `HOUR` < 23
    )
SELECT
    t.`HOUR`,
    COUNT(a.ANIMAL_ID) AS `COUNT`
FROM
    T1 t
    LEFT JOIN ANIMAL_OUTS a ON HOUR(a.DATETIME) = t.`HOUR`
GROUP BY
    t.`HOUR`
ORDER BY
    `HOUR`;