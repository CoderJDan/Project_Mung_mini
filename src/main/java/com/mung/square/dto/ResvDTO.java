package com.mung.square.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("resv")
public class ResvDTO {
    //User DTO에서 2개
    //Map DTO에서 2개
    /*
    * resv_num INT PRIMARY KEY,
    email VARCHAR(100),                             -- User 테이블의 email을 참조
    name VARCHAR(40),                               -- User 테이블의 name을 참조
    member_no VARCHAR(20),                          -- Branch 테이블의 member_no를 참조
    branch_name VARCHAR(10),                        -- Branch 테이블의 branch_name을 참조
    resv_name VARCHAR(40),                          -- 예약자 이름
    resv_phonenum VARCHAR(40),                      -- 예약자 전화번호
    resv_date DATETIME,                             -- 예약 날짜
    start_time DATETIME,                            -- 예약받은 시작 시간
    end_time DATETIME,                              -- 예약받은 종료 시간
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 예약 생성 시간
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 예약 수정 시간
    barcount INT,                                   -- 바 수 (바 선택 갯수)
    */
    private int resv_num;
    private String user_id;
    private String branch_name;
    private String status;
    private String resv_date;
    private Timestamp created_at;
    private Timestamp start_time;
    private Timestamp end_time;
    private int barcount;
    /*
     * resv_num INT PRIMARY KEY,
    user_id VARCHAR(50) NOT NULL,
    reservation_date TIMESTAMP NOT NULL,
    branch_name VARCHAR(10) NOT NULL,
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    resv_date DATETIME NOT NULL,
    start_time DATETIME NOT NULL,                           -- 예약받은 시작 시간
    end_time DATETIME NOT NULL,                           -- 예약받은 종료 시간
    barcount INT,
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     */
}

