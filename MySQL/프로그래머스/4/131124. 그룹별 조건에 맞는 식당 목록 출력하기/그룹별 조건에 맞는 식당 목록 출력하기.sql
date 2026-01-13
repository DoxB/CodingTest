-- 코드를 입력하세요
WITH
    T1 AS (
        SELECT
            MEMBER_ID,
            COUNT(REVIEW_ID) AS REVIEW_COUNT
        FROM
            REST_REVIEW
        GROUP BY
            MEMBER_ID
    ), T2 AS (
        SELECT
            MEMBER_ID,
            RANK() OVER (ORDER BY REVIEW_COUNT DESC) AS REVIEW_RANK
        FROM
            T1
    )
SELECT
    m.MEMBER_NAME,
    r.REVIEW_TEXT,
    DATE_FORMAT(REVIEW_DATE, '%Y-%m-%d') AS REVIEW_DATE
FROM
    T2 tt
    JOIN MEMBER_PROFILE m
      ON tt.MEMBER_ID = m.MEMBER_ID
    JOIN REST_REVIEW r
      ON tt.MEMBER_ID = r.MEMBER_ID
WHERE
    tt.REVIEW_RANK = 1
ORDER BY
    REVIEW_DATE,
    REVIEW_TEXT;