package com.dayz.reservation.controller;

import com.dayz.common.aop.LoginMember;
import com.dayz.common.dto.ApiResponse;
import com.dayz.common.dto.CustomPageRequest;
import com.dayz.common.dto.CustomPageResponse;
import com.dayz.member.domain.Member;
import com.dayz.reservation.dto.ReadAllAtelierReservationResponse;
import com.dayz.reservation.dto.ReadAllMyReservationResponse;
import com.dayz.reservation.dto.SaveReservationRequest;
import com.dayz.reservation.service.ReservationService;
import java.util.Map;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping(value = "/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<Map<String, Long>> saveReservation(@LoginMember Member member,
        @Valid @RequestBody SaveReservationRequest saveReservationRequest) {
        return ApiResponse.ok(Map.of("reservationId",
            reservationService.saveReservation(saveReservationRequest, member)));
    }

    @GetMapping(value = "/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<CustomPageResponse> getMyReservations(@LoginMember Member member,
        CustomPageRequest pageRequest) {
        CustomPageResponse<ReadAllMyReservationResponse> myReservation = reservationService.getMyReservation(
                pageRequest, member.getId());
        return ApiResponse.ok(myReservation);
    }

    @GetMapping(value = "/reservations/ateliers/{atelierId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<CustomPageResponse> getAtelierReservations(
        @PathVariable("atelierId") Long atelierId, CustomPageRequest pageRequest) {
        CustomPageResponse<ReadAllAtelierReservationResponse> myReservation = reservationService.getAtelierReservation(
                pageRequest, atelierId);
        return ApiResponse.ok(myReservation);
    }

    @DeleteMapping(value = "/reservations/{reservationId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse deleteReservation(@PathVariable("reservationId") Long reservationId){
        reservationService.deleteReservation(reservationId);
        return ApiResponse.noContent();
    }
}
