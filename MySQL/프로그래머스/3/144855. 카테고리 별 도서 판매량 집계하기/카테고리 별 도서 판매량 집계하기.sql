-- 코드를 입력하세요
WITH
    T1 AS (
        SELECT
            BOOK_ID,
            SUM(SALES) AS TOTAL_SALES
        FROM
            BOOK_SALES
        WHERE
            SALES_DATE BETWEEN '2022-01-01' AND '2022-01-31'
        GROUP BY
            BOOK_ID
    )
SELECT
    b.CATEGORY,
    SUM(t.TOTAL_SALES) AS TOTAL_SALES
FROM
    T1 t
    JOIN BOOK b ON b.BOOK_ID = t.BOOK_ID
GROUP BY
    b.CATEGORY
ORDER BY
    CATEGORY;