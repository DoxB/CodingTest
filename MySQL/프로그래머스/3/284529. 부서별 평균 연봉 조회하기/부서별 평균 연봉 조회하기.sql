-- 코드를 작성해주세요
WITH
    T1 AS (
        SELECT
            DEPT_ID,
            ROUND(AVG(SAL), 0) AS AVG_SAL
        FROM
            HR_EMPLOYEES
        GROUP BY
            DEPT_ID
    )
SELECT
    t.DEPT_ID,
    d.DEPT_NAME_EN,
    t.AVG_SAL
FROM
    T1 t
    JOIN HR_DEPARTMENT d
      ON t.DEPT_ID = d.DEPT_ID
ORDER BY
    AVG_SAL DESC;