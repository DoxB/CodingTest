-- 코드를 입력하세요
WITH
    T1 AS (
        SELECT
            i.ANIMAL_ID,
            i.NAME,
            DATEDIFF(o.DATETIME, i.DATETIME) + 1 AS DURATION
        FROM
            ANIMAL_INS i
            JOIN ANIMAL_OUTS o
              ON i.ANIMAL_ID = o.ANIMAL_ID
        ORDER BY
            DURATION DESC
    )
SELECT
    ANIMAL_ID,
    NAME
FROM
    T1
LIMIT 2;