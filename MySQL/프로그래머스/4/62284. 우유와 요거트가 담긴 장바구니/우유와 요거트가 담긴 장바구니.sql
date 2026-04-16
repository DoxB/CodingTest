-- 코드를 입력하세요
WITH
    T1 AS (
        SELECT
            DISTINCT(CART_ID) AS CART_ID,
            NAME
        FROM
            CART_PRODUCTS
        WHERE
            NAME = 'Milk'
    ),
    T2 AS (
        SELECT
            DISTINCT(CART_ID) AS CART_ID,
            NAME
        FROM
            CART_PRODUCTS
        WHERE
            NAME = 'Yogurt'
    )
SELECT
    t1.CART_ID AS CART_ID
FROM
    T1 t1
    JOIN T2 t2
      ON t1.CART_ID = t2.CART_ID
ORDER BY
    CART_ID;



