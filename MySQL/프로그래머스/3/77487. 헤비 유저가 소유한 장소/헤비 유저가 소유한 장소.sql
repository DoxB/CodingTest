-- 코드를 입력하세요
WITH
    T1 AS (
        SELECT
            HOST_ID
        FROM
            PLACES
        GROUP BY
            HOST_ID
        HAVING
            COUNT(*) >= 2
    )
SELECT
    p.ID,
    p.NAME,
    t.HOST_ID
FROM
    T1 t
    JOIN PLACES p
      ON t.HOST_ID = p.HOST_ID
ORDER BY
    p.ID;



    