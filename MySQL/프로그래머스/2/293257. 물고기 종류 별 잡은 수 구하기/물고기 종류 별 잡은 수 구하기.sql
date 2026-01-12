-- 코드를 작성해주세요
WITH
    T1 AS (
        SELECT
            FISH_TYPE,
            COUNT(ID) AS FISH_COUNT
        FROM
            FISH_INFO
        GROUP BY
            FISH_TYPE
    )
SELECT
    t.FISH_COUNT,
    i.FISH_NAME
FROM
    T1 t
    LEFT JOIN FISH_NAME_INFO i
      ON t.FISH_TYPE = i.FISH_TYPE
ORDER BY
    t.FISH_COUNT DESC;