-- 코드를 작성해주세요
WITH
    T1 AS (
        SELECT
            EMP_NO,
            SUM(SCORE) AS TOTAL_SCORE
        FROM
            HR_GRADE
        WHERE
            `YEAR` = 2022
        GROUP BY
            EMP_NO
    )
SELECT
    t.TOTAL_SCORE AS SCORE,
    t.EMP_NO,
    h.EMP_NAME,
    h.POSITION,
    h.EMAIL
FROM
    T1 t
    JOIN HR_EMPLOYEES h
      ON t.EMP_NO = h.EMP_NO
WHERE     
    t.TOTAL_SCORE = (SELECT MAX(TOTAL_SCORE) FROM T1);