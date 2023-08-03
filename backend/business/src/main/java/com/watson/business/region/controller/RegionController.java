package com.watson.business.region.controller;

import com.watson.business.region.dto.EmdResponse;
import com.watson.business.region.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/region")
public class RegionController {
	private final RegionService regionService;

	@GetMapping()
    public List<String> getGunguNamesBySido(@RequestParam("sido") String sido) {
        return regionService.getGunguNamesBySido(sido);
    }

    @GetMapping(value = "/court-code")
    public List<EmdResponse> getCourtCodesByGungu(@RequestParam("gunguName") String gunguName) {
        return regionService.getCourtCodesByGungu(gunguName);
    }
}
