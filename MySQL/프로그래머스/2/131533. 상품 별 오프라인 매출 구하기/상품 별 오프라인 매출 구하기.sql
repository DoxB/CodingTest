-- 코드를 입력하세요
WITH
    T1 AS (
        SELECT
            PRODUCT_ID,
            SUM(SALES_AMOUNT) AS AMOUNT
        FROM
            OFFLINE_SALE
        GROUP BY
            PRODUCT_ID
    )
SELECT
    p.PRODUCT_CODE,
    (t.AMOUNT * p.PRICE) AS SALES
FROM
    T1 t
    JOIN PRODUCT p
      ON t.PRODUCT_ID = p.PRODUCT_ID
ORDER BY
    SALES DESC,
    PRODUCT_CODE;