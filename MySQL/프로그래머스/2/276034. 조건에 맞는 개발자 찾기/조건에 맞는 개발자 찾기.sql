-- 코드를 작성해주세요
WITH T1 AS (
    SELECT
        d.ID,
        d.EMAIL,
        d.FIRST_NAME,
        d.LAST_NAME,
        s.CODE,
        s.NAME
    FROM DEVELOPERS d
    JOIN SKILLCODES s ON (d.SKILL_CODE & s.CODE) > 0
)
SELECT
    DISTINCT(ID) AS ID,
    EMAIL,
    FIRST_NAME,
    LAST_NAME
FROM T1
WHERE NAME = 'Python' OR NAME = 'C#'
ORDER BY ID;