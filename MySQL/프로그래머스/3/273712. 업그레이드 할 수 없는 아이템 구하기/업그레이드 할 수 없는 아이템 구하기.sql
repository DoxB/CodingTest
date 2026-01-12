-- 코드를 작성해주세요
WITH
    T1 AS (
        SELECT
            a.ITEM_ID,
            b.ITEM_ID AS NXT_ID
        FROM
            ITEM_TREE a
            LEFT JOIN ITEM_TREE b
              ON a.ITEM_ID = b.PARENT_ITEM_ID
    )
SELECT
    ITEM_ID,
    ITEM_NAME,
    RARITY
FROM
    ITEM_INFO
WHERE
    ITEM_ID IN (
        SELECT DISTINCT ITEM_ID FROM T1 WHERE NXT_ID IS NULL)
ORDER BY
    ITEM_ID DESC;