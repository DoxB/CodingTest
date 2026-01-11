-- 코드를 입력하세요
WITH T1 AS (
    SELECT
        *
    FROM CAR_RENTAL_COMPANY_CAR
    WHERE OPTIONS REGEXP '통풍시트|열선시트|가죽시트'
)
SELECT
    CAR_TYPE,
    COUNT(*)
FROM T1
GROUP BY
    CAR_TYPE
ORDER BY
    CAR_TYPE;
    