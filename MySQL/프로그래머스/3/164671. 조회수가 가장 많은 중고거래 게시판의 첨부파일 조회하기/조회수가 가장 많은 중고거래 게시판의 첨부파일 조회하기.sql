-- 코드를 입력하세요
WITH
    T1 AS (
        SELECT
           BOARD_ID
        FROM
            USED_GOODS_BOARD
        ORDER BY
            `VIEWS` DESC
        LIMIT 1
    )
SELECT
    CONCAT('/home/grep/src/', t.BOARD_ID, '/', f.FILE_ID, FILE_NAME, FILE_EXT) AS FILE_PATH
FROM
    USED_GOODS_FILE f
    JOIN T1 t
      ON f.BOARD_ID = t.BOARD_ID
ORDER BY
    f.FILE_ID DESC;