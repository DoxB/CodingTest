-- 코드를 작성해주세요
WITH
    RECURSIVE T1 AS (
        SELECT
            1 AS g,
            ID
        FROM
            ECOLI_DATA
        WHERE
            PARENT_ID IS NULL
        UNION ALL
        SELECT
            t.g + 1,
            e.ID
        FROM T1 t
        JOIN ECOLI_DATA e
          ON e.PARENT_ID = t.ID
    )
SELECT
    COUNT(ID) AS `COUNT`,
    g AS GENERATION
FROM
    T1
WHERE
    ID NOT IN (
        SELECT
            DISTINCT(PARENT_ID)
        FROM
            ECOLI_DATA
        WHERE
            PARENT_ID IS NOT NULL)
GROUP BY
    g
ORDER BY
    g;
