-- 코드를 입력하세요
WITH
    T1 AS (
        SELECT
            APNT_YMD,
            APNT_NO,
            PT_NO,
            MCDP_CD,
            MDDR_ID
        FROM
            APPOINTMENT
        WHERE
            APNT_CNCL_YN = 'N'
            AND MCDP_CD = 'CS'
            AND DATE_FORMAT(APNT_YMD, '%Y-%m-%d') = '2022-04-13'
    )
SELECT
    t.APNT_NO,
    p.PT_NAME,
    p.PT_NO,
    t.MCDP_CD,
    d.DR_NAME,
    t.APNT_YMD
FROM
    T1 t
    JOIN PATIENT p
      ON t.PT_NO = p.PT_NO
    JOIN DOCTOR d
      ON t.MDDR_ID = d.DR_ID
ORDER BY
    t.APNT_YMD;