-- 코드를 입력하세요
WITH T1 AS (
    SELECT
        CATEGORY,
        MAX(PRICE) AS MAX_PRICE
    FROM FOOD_PRODUCT
    WHERE CATEGORY IN ('과자', '국', '김치', '식용유')
    GROUP BY
        CATEGORY
), T2 AS (
    SELECT
        *
    FROM FOOD_PRODUCT
    WHERE CATEGORY IN ('과자', '국', '김치', '식용유')
)
SELECT
    t.CATEGORY,
    t.MAX_PRICE,
    f.PRODUCT_NAME
FROM T1 t
JOIN T2 f
  ON t.MAX_PRICE = f.PRICE
ORDER BY
    MAX_PRICE DESC;