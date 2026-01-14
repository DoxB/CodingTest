-- 코드를 입력하세요
WITH
    T1 AS (
        SELECT
            WRITER_ID,
            COUNT(BOARD_ID) AS `COUNT`
        FROM
            USED_GOODS_BOARD
        GROUP BY
            WRITER_ID
        HAVING
            COUNT(BOARD_ID) >= 3
    )
SELECT
    g.USER_ID,
    g.NICKNAME,
    CONCAT(g.CITY, ' ', g.STREET_ADDRESS1, ' ', g.STREET_ADDRESS2) AS 전체주소,
    CONCAT(SUBSTRING(g.TLNO, 1, 3), '-', SUBSTRING(g.TLNO, 4, 4), '-', SUBSTRING(g.TLNO, 8, 4)) AS 전화번호
FROM
    T1 t
    JOIN USED_GOODS_USER g
      ON t.WRITER_ID = g.USER_ID
ORDER BY
    USER_ID DESC;