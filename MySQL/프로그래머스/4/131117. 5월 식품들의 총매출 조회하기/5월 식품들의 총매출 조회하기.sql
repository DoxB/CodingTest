-- 코드를 입력하세요
WITH
    T1 AS (
        SELECT
            PRODUCT_ID,
            SUM(AMOUNT) AS AMOUNT
        FROM
            FOOD_ORDER
        WHERE
            PRODUCE_DATE BETWEEN '2022-05-01' AND '2022-05-31'
        GROUP BY
            PRODUCT_ID
    )
SELECT
    t.PRODUCT_ID,
    f.PRODUCT_NAME,
    (f.PRICE * t.AMOUNT) AS TOTAL_SALES
FROM
    T1 t
    JOIN FOOD_PRODUCT f
      ON t.PRODUCT_ID = f.PRODUCT_ID
ORDER BY
    (f.PRICE * t.AMOUNT) DESC,
    t.PRODUCT_ID;