-- 코드를 작성해주세요
WITH T1 AS (
   SELECT
        i.ID,
        i.FISH_TYPE,
        n.FISH_NAME
    FROM FISH_INFO i
    JOIN FISH_NAME_INFO n
        ON i.FISH_TYPE = n.FISH_TYPE
    WHERE FISH_NAME = 'BASS'
        OR FISH_NAME = 'SNAPPER'
)
SELECT
    COUNT(*) AS FISH_COUNT
FROM T1;
    