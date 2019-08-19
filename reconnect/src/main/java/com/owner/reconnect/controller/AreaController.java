package com.owner.reconnect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.owner.reconnect.dto.DistrictDto;
import com.owner.reconnect.dto.MandalDto;
import com.owner.reconnect.dto.PlotDto;
import com.owner.reconnect.dto.StateDto;
import com.owner.reconnect.dto.SurveyDto;
import com.owner.reconnect.dto.UserPlotInfoDto;
import com.owner.reconnect.dto.VentureDtlsDto;
import com.owner.reconnect.dto.VillageDto;
import com.owner.reconnect.exception.ReconnectException;
import com.owner.reconnect.service.AreaService;

@RestController
@RequestMapping("/area")
public class AreaController {

	@Autowired
	private AreaService areaService;

	@RequestMapping(value = "/states", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<StateDto> states() {
		List<StateDto> states = areaService.findAllStates();
		return states;
	}

	@RequestMapping(value = "/districts/{stateId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DistrictDto> districts(@PathVariable long stateId) {
		List<DistrictDto> districts = areaService
				.findAllDistrictsByStateId(stateId);
		return districts;
	}

	@RequestMapping(value = "/mandals/{districtId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MandalDto> mandals(@PathVariable long districtId) {
		List<MandalDto> mandals = areaService
				.findAllMandalsByDistrictId(districtId);
		return mandals;
	}

	@RequestMapping(value = "/villages/{mandalId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<VillageDto> villages(@PathVariable long mandalId) {
		List<VillageDto> villageDtos = areaService
				.findAllVillagesByMondalId(mandalId);
		return villageDtos;
	}

	@RequestMapping(value = "/surveys/{villageId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<SurveyDto> surveys(@PathVariable long villageId) {
		List<SurveyDto> surveys = areaService
				.findAllSurveyNumsByVillageId(villageId);
		return surveys;
	}

	@RequestMapping(value = "/plots/{surveyId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PlotDto> plots(@PathVariable long surveyId) {
		List<PlotDto> plots = areaService.findAllPlotsBySurveyNumId(surveyId);
		return plots;
	}

	@PostMapping("/plotDetails")
	public UserPlotInfoDto save(@RequestBody UserPlotInfoDto plotDto)
			throws ReconnectException {
		return this.areaService.save(plotDto);
	}

	@RequestMapping(value = "/venture/user/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<VentureDtlsDto> ventureDtls(@PathVariable long userId) {
		List<VentureDtlsDto> ventureList = this.areaService
				.getVentureDtls(userId);
		return ventureList;
	}

	@RequestMapping(value = "/listUsers/survey/{surveyId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserPlotInfoDto> userList(@PathVariable long surveyId) {
		List<UserPlotInfoDto> userList = this.areaService
				.findUsersBySurveyId(surveyId);
		return userList;
	}
}
