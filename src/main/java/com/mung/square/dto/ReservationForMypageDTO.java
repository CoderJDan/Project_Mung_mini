package com.mung.square.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("reservationformypage")
public class ReservationForMypageDTO {
    private int resvNum;
    private String userId;
    private String formattedResvDate;  // "YYYY/MM/DD" 형식
    private String formattedStartTime; // "HH:mm:ss" 형식
    private String formattedEndTime;   // "HH:mm:ss" 형식
    private String branchName;
    private String status;
    private Timestamp createdAt;
    private Integer barcount;
}
