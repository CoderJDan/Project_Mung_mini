package com.mung.square.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.sql.Timestamp;
import java.time.LocalDateTime;

// response 용 DTO ->

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("reservationorder")
public class ReservationDTO {
    private int resvNum;
    private String branchName;
    private int barcount;
    private LocalDateTime resvDate;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String userId;
    private String name;        // user table 에서 가져올 정보
    private String phoneNumber; // user table 에서 가져올 정보
}
