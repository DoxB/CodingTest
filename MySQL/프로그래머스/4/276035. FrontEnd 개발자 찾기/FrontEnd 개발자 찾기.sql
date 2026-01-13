-- 코드를 작성해주세요
WITH
    T1 AS (
        SELECT
            SUM(CODE) AS FE
        FROM
            SKILLCODES
        WHERE
            CATEGORY = 'Front End'
    )
SELECT
    ID,
    EMAIL,
    FIRST_NAME,
    LAST_NAME
FROM
    DEVELOPERS
WHERE
    ((SELECT * FROM T1) & SKILL_CODE) > 0
ORDER BY
    ID;