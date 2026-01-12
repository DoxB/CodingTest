-- 코드를 작성해주세요
WITH
    T1 AS (
        SELECT
            EMP_NO,
            AVG(SCORE) AS AVG_SCORE
        FROM
            HR_GRADE
        GROUP BY
            EMP_NO
    )
SELECT
    h.EMP_NO,
    h.EMP_NAME,
    CASE
        WHEN t.AVG_SCORE >= 96 THEN 'S'
        WHEN t.AVG_SCORE >= 90 THEN 'A'
        WHEN t.AVG_SCORE >= 80 THEN 'B'
        ELSE 'C'
    END AS GRADE,
    CASE
        WHEN t.AVG_SCORE >= 96 THEN h.SAL * 0.2
        WHEN t.AVG_SCORE >= 90 THEN h.SAL * 0.15
        WHEN t.AVG_SCORE >= 80 THEN h.SAL * 0.1
        ELSE h.SAL * 0
    END AS BONUS
FROM
    T1 t
    JOIN HR_EMPLOYEES h
      ON t.EMP_NO = h.EMP_NO
ORDER BY
    EMP_NO;
