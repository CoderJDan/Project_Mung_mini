package com.mung.square.admin;

import com.mung.square.dto.Reservation;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReservationMapper {
    @Select("SELECT * FROM reservations") // SQL 직접 작성 (또는 XML 매퍼 사용)
    List<Reservation> getAllReservations();

    @SelectProvider(type = ReservationSqlProvider.class, method = "getFilteredReservations")
    List<Reservation> getFilteredReservations(
            @Param("status") String status,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate
    );
    @Update("UPDATE reservations SET status = #{status} WHERE id = #{id}")
    int updateStatus(@Param("id") String id, @Param("status") String status);
}


