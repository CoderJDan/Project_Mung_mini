package com.mung.square.payment.controller;

import com.mung.square.dto.*;
import com.mung.square.payment.service.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/kakaopay")
public class kakaopayController {
    private final OrderService orderService;
    private final PaymentService paymentService;
    private final RefundService refundService;
    private final UpdateService updateService;

    @GetMapping
    public String kakaopay(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");
        SessionUtils.addAttribute("userId", userId);
        System.out.println(userId);
        ReservationDTO resv = orderService.getLatestReservationByUserId(userId);
        model.addAttribute("resv", resv);
        return "/include/kakaopayContent";
    }

    private final KakaoPayService kakaoPayService;

    @PostMapping("/ready")
    @ResponseBody
    public ReadyResponse payReady(@RequestBody OrderCreateForm orderCreateForm) {
        int resvNum = orderCreateForm.getResvNum();
        String name = orderCreateForm.getName();
        int totalPrice = orderCreateForm.getTotalPrice();
        SessionUtils.addAttribute("partner_order_id", resvNum);
        SessionUtils.addAttribute("totalPrice", totalPrice);

        // 카카오 결제 준비하기
        ReadyResponse readyResponse = kakaoPayService.payReady(resvNum, name, totalPrice);
        // 세션에 결제 고유번호(tid) 저장
        SessionUtils.addAttribute("tid", readyResponse.getTid());
        System.out.println(readyResponse);
        return readyResponse;
    }

    @GetMapping("/completed")
    public String payCompleted(@RequestParam("pg_token") String pgToken, Model model) {
        String tid = SessionUtils.getStringAttributeValue("tid");
        String partner_order_id = SessionUtils.getStringAttributeValue("partner_order_id");

        // 카카오 결제 요청하기
        ApproveResponse approveResponse = kakaoPayService.payApprove(tid, pgToken, partner_order_id);
        System.out.println(approveResponse);

        SessionUtils.addAttribute("itemName", approveResponse.getItem_name());

        model.addAttribute("approveResponse", approveResponse);

        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setTid(approveResponse.getTid());
        paymentDTO.setUserId(SessionUtils.getStringAttributeValue("userId"));
        paymentDTO.setItemName(approveResponse.getItem_name());
        paymentDTO.setTotalPrice(Integer.parseInt(SessionUtils.getStringAttributeValue("totalPrice")));
        paymentDTO.setStatus(1);
        paymentDTO.setOrderNum(Integer.parseInt(approveResponse.getPartner_order_id()));

        System.out.println(paymentDTO);

        paymentService.insertPayment(paymentDTO);


        return "/include/kakaopaySuccess";
    }


    @PostMapping("/refund")
    public ResponseEntity<CancelResponse> refund(@RequestBody Map<String, Integer> request) {

        int orderNum = request.get("orderNum");

        RefundDTO refundDTO = refundService.getRefund(orderNum);

        String tid = refundDTO.getTid();
        int totalPrice = refundDTO.getTotalPrice();

        updateService.updateStatus(orderNum);
        updateService.updateReservationStatus(orderNum);

        CancelResponse kakaocancelResponse = kakaoPayService.payCancel(tid, totalPrice);
        ResponseEntity<CancelResponse> response = new ResponseEntity<>(kakaocancelResponse, HttpStatus.OK);
        System.out.println(kakaocancelResponse);
        return response;
    }
}