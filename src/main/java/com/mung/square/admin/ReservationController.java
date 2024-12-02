package com.mung.square.admin;

import com.mung.square.dto.Reservation;
import com.mung.square.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/reservations")
@SessionAttributes("user")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("")
    public String listReservations(@RequestParam(required = false) String status, @RequestParam(required = false) String dateRange, @ModelAttribute("user") UserDTO user, Model model) {
        String startDate = null;
        String endDate = null;

        if ("today".equals(dateRange)) {
            startDate = LocalDate.now().toString();
            endDate = LocalDate.now().toString();
        } else if ("week".equals(dateRange)) {
            startDate = LocalDate.now().minusDays(7).toString();
            endDate = LocalDate.now().toString();
        } else if ("month".equals(dateRange)) {
            startDate = LocalDate.now().minusDays(30).toString();
            endDate = LocalDate.now().toString();
        }

        if ("pending".equals(status)) {
            status = "대기중";
        } else if ("confirmed".equals(status)) {
            status = "승인";
        } else if ("cancelled".equals(status)) {
            status = "취소";
        }

        List<Reservation> reservations = reservationService.getFilteredReservations(status, startDate, endDate);
        model.addAttribute("reservations", reservations);

        return "include/admin"; // 필터링된 데이터를 admin.html에 렌더링
    }

    @PostMapping("/updateStatus")
    @ResponseBody
    public String updateStatus(@RequestBody Map<String, String> payload) {
        String id = payload.get("id");
        String status = payload.get("status");

        if (id == null || status == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid parameters");
        }

        boolean isUpdated = reservationService.updateReservationStatus(id, status);
        if (isUpdated) {
            return "success";
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update status");
        }
    }
}
