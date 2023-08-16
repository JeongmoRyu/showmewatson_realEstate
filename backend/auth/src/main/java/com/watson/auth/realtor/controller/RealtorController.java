package com.watson.auth.realtor.controller;

import com.watson.auth.realtor.dto.AgencyResponse;
import com.watson.auth.realtor.service.RealtorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/auth/realtor")
public class RealtorController {

    private final RealtorService realtorService;

    /* 사무소 정보 조회 */
    @GetMapping("/{registId}")
    public ResponseEntity<AgencyResponse> agencyDetails(@PathVariable String registId) {
        try {
            AgencyResponse agencyResponse = realtorService.findAgencyByRegistId(registId);
            if (agencyResponse != null) {
                return ResponseEntity.status(HttpStatus.OK).body(agencyResponse);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}