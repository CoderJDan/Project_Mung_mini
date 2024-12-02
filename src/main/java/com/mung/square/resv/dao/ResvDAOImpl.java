package com.mung.square.resv.dao;

import com.mung.square.dto.ResvDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

    @Repository
    public class ResvDAOImpl implements ResvDAO {
    
        private final SqlSession sqlSession;
    
        @Autowired
        public ResvDAOImpl(SqlSession sqlSession) {
            this.sqlSession = sqlSession;
        }
    
        @Override
        public boolean isTimeAvailable(ResvDTO resvDTO) {
            // 예약 시간이 겹치는지 확인하는 MyBatis 쿼리 호출
            Integer count = sqlSession.selectOne("ResvMapper.checkOverlap", resvDTO);
            return count == 0;  // 겹치는 예약이 없으면 true
        }
    
        @Override
        public void insertReservation(ResvDTO resvDTO) {
            // 예약을 DB에 삽입
            sqlSession.insert("ResvMapper.insertReservation", resvDTO);
        }
    }
