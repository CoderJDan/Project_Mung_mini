package com.mung.square.resv.controller;

import com.mung.square.dto.ResvDTO;
import com.mung.square.map.service.MapService;
import com.mung.square.resv.service.ResvService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/resv")
@RequiredArgsConstructor
public class ResvController {
    private final MapService mapService;
    private final ResvService resvService;


    @GetMapping
    public String resv() {

        return "/include/resvContent";
    }

    // view 에서 controller 로

    // 예약을 처리하는 엔드포인트
    @PostMapping("/reserve")
    @ResponseBody
    public ResponseEntity<String> reserve(@RequestBody ResvDTO resvDTO) {
        if (resvService.isReservationAvailable(resvDTO)) {
            resvService.createReservation(resvDTO);
            return ResponseEntity.ok("Reservation successful.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Reservation time overlaps.");
        }
    }
}
