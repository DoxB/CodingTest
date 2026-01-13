-- 코드를 입력하세요
WITH
    T1 AS (
        SELECT
            o.ANIMAL_ID,
            o.NAME,
            i.DATETIME
        FROM
            ANIMAL_OUTS o
            LEFT JOIN ANIMAL_INS i
              ON o.ANIMAL_ID = i.ANIMAL_ID
    )
SELECT
    ANIMAL_ID,
    NAME
FROM
    T1
WHERE
    DATETIME IS NULL;