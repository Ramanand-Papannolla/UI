package com.owner.reconnect.service;

import java.util.List;

import com.owner.reconnect.dto.DistrictDto;
import com.owner.reconnect.dto.MandalDto;
import com.owner.reconnect.dto.PlotDto;
import com.owner.reconnect.dto.StateDto;
import com.owner.reconnect.dto.SurveyDto;
import com.owner.reconnect.dto.UserPlotInfoDto;
import com.owner.reconnect.dto.VentureDtlsDto;
import com.owner.reconnect.dto.VillageDto;
import com.owner.reconnect.exception.ReconnectException;

public interface AreaService {

	List<StateDto> findAllStates();

	List<DistrictDto> findAllDistrictsByStateId(Long stateId);

	List<MandalDto> findAllMandalsByDistrictId(Long districtId);

	List<VillageDto> findAllVillagesByMondalId(Long mondalId);

	List<SurveyDto> findAllSurveyNumsByVillageId(Long villageId);

	List<PlotDto> findAllPlotsBySurveyNumId(Long surveyId);

	UserPlotInfoDto save(UserPlotInfoDto userPlotInfo)
			throws ReconnectException;

	List<VentureDtlsDto> getVentureDtls(long userId);

	List<UserPlotInfoDto> findUsersBySurveyId(long surveyId);
}
