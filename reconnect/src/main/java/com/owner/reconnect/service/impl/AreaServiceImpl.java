package com.owner.reconnect.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.owner.reconnect.dto.DistrictDto;
import com.owner.reconnect.dto.MandalDto;
import com.owner.reconnect.dto.PlotDto;
import com.owner.reconnect.dto.StateDto;
import com.owner.reconnect.dto.SurveyDto;
import com.owner.reconnect.dto.UserDetailsDto;
import com.owner.reconnect.dto.UserPlotInfoDto;
import com.owner.reconnect.dto.VentureDtlsDto;
import com.owner.reconnect.dto.VillageDto;
import com.owner.reconnect.dto.mapper.AreaMapper;
import com.owner.reconnect.dto.mapper.UserDetailsMapper;
import com.owner.reconnect.entities.District;
import com.owner.reconnect.entities.DocumentInfo;
import com.owner.reconnect.entities.Mandal;
import com.owner.reconnect.entities.PlotDetails;
import com.owner.reconnect.entities.State;
import com.owner.reconnect.entities.SurveyNumDetails;
import com.owner.reconnect.entities.UserDetails;
import com.owner.reconnect.entities.Village;
import com.owner.reconnect.exception.AlreadyPlotExistedException;
import com.owner.reconnect.exception.ReconnectException;
import com.owner.reconnect.exception.UsernameNotFoundException;
import com.owner.reconnect.repository.AreaRepository;
import com.owner.reconnect.repository.UserRepository;
import com.owner.reconnect.service.AreaService;
import com.owner.reconnect.util.StringUtils;

@Service
@Transactional
public class AreaServiceImpl implements AreaService {

	@Autowired
	private AreaRepository areaRepository;

	@Autowired
	private AreaMapper areaMapper;

	@Autowired
	private UserDetailsMapper userDetailsMapper;

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<StateDto> findAllStates() {
		List<State> findAllStates = areaRepository.findAllStates();
		List<StateDto> stateDtos = areaMapper.mapToStateDtos(findAllStates);
		return stateDtos;
	}

	@Override
	public List<DistrictDto> findAllDistrictsByStateId(Long stateId) {
		List<District> districtsList = this.areaRepository
				.findAllDistrictsByState(stateId);
		List<DistrictDto> districtDtos = this.areaMapper
				.mapToDistrictDtos(districtsList);
		return districtDtos;
	}

	@Override
	public List<MandalDto> findAllMandalsByDistrictId(Long districtId) {
		List<Mandal> mandals = this.areaRepository
				.findAllMandalsByDistrict(districtId);
		List<MandalDto> mandalDtoList = this.areaMapper
				.mapToMandalDtos(mandals);
		return mandalDtoList;
	}

	@Override
	public List<VillageDto> findAllVillagesByMondalId(Long mondalId) {
		List<Village> villages = this.areaRepository
				.findAllVillagesByMondal(mondalId);
		List<VillageDto> villageDtoList = this.areaMapper
				.mapToVillageDtos(villages);
		return villageDtoList;
	}

	@Override
	public List<SurveyDto> findAllSurveyNumsByVillageId(Long villageId) {
		List<SurveyNumDetails> surveys = this.areaRepository
				.findAllSurveyNumsByVillage(villageId);
		List<SurveyDto> surveyDtos = this.areaMapper.mapToSurveyDtos(surveys);
		return surveyDtos;
	}

	@Override
	public List<PlotDto> findAllPlotsBySurveyNumId(Long surveyId) {
		List<PlotDetails> plots = this.areaRepository
				.findAllPlotsBySurveyNum(surveyId);
		List<PlotDto> plotDtos = this.areaMapper.mapToPlotDtos(plots);
		return plotDtos;
	}

	@Override
	public UserPlotInfoDto save(UserPlotInfoDto userPlotInfo)
			throws ReconnectException {
		State state = this.areaRepository.findSateById(userPlotInfo
				.getStateId());
		District district = this.areaRepository.findDistrictById(userPlotInfo
				.getDistrictId());
		Mandal mandal = this.areaRepository.findMandalById(userPlotInfo
				.getMandalId());
		Village village = this.areaRepository.findVillageById(userPlotInfo
				.getVillageId());
		if (null == state || null == district || null == mandal
				|| null == village) {
			throw new IllegalArgumentException("Invalid Details : " + state
					+ " " + district + " " + mandal + " " + village);
		}

		final UserDetailsDto userDto = userPlotInfo.getUser();
		final UserDetails user = this.userRepository.findById(userDto.getId());
		if (null == user) {
			throw new UsernameNotFoundException("User Not found :"
					+ userDto.getId());
		}
		final SurveyDto surveyDto = userPlotInfo.getSurveyNumber();
		if (null == surveyDto
				|| (null == surveyDto.getId() && StringUtils
						.isNullOrEmpty(surveyDto.getSurveyNum()))) {
			throw new IllegalArgumentException(
					"No survey number selected or provided");
		}
		SurveyNumDetails surveyNumDetails = null;
		if (null != surveyDto.getId()) {
			surveyNumDetails = this.areaRepository.findSurveyById(surveyDto
					.getId());
		}
		if (null == surveyNumDetails) {
			surveyNumDetails = this.areaMapper.mapToSurveyNumDetails(surveyDto);
		}
//		Set<SurveyNumDetails> surveyDetailsSet = new HashSet<>();
//		surveyDetailsSet.add(surveyNumDetails);
		surveyNumDetails.setVillage(village);
		user.getSurveyNumDetails().add(surveyNumDetails);
//		user.setSurveyNumDetails(surveyDetailsSet);
		if (null != userPlotInfo.getSurveyDoc()) {
			DocumentInfo document = this.areaMapper
					.mapToDocumentInfo(userPlotInfo.getSurveyDoc());
			document.setSurveyNumDetails(surveyNumDetails);
			if (null == surveyNumDetails.getDocumentInfo()) {
				surveyNumDetails.setDocumentInfo(document);
			} else {
				DocumentInfo surveyDoc = surveyNumDetails.getDocumentInfo();
				surveyDoc.setDocument(document.getDocument());
				surveyDoc.setName(document.getName());
				surveyDoc.setType(document.getType());
			}
		}
		// Plot Details
		final PlotDto plotDto = userPlotInfo.getPlotNumber();
		if (null == plotDto
				|| (null == plotDto.getId() && StringUtils
						.isNullOrEmpty(plotDto.getPlotNumber()))) {
			throw new IllegalArgumentException("No Plot details");
		}
		PlotDetails plotDetails = null;
		if (null != plotDto.getId()) {
			plotDetails = this.areaRepository.findPlotById(plotDto.getId());
		}

		if (null != plotDetails) {
			throw new AlreadyPlotExistedException(
					"Already plot existed with the provided Plot number :"
							+ plotDto.getPlotNumber());
		}
		PlotDetails newPlot = this.areaMapper.mapToPlotDetails(plotDto);
		Set<PlotDetails> plotDetailsSet = new HashSet<>();
		plotDetailsSet.add(newPlot);
		newPlot.setUserDetails(user);
		user.setPlotDetails(plotDetailsSet);
		newPlot.setSurveyNumDetails(surveyNumDetails);
		final PlotDetails plot = this.areaRepository.addPlotDetails(newPlot);

		if (null != plot) {
			plotDto.setId(plot.getId());
			plotDto.setPlotNumber(plot.getPlotNumber());
			final SurveyNumDetails surveyDetails = plot.getSurveyNumDetails();
			surveyDto.setId(surveyDetails.getId());
			surveyDto.setSurveyNum(surveyDetails.getSurveyNumber());
		}

		return userPlotInfo;
	}

	@Override
	public List<VentureDtlsDto> getVentureDtls(long userId) {
		final UserDetails userDetails = this.userRepository.findById(userId);
		Set<SurveyNumDetails> surveyNumList = userDetails.getSurveyNumDetails();
		List<VentureDtlsDto> ventureDtoList = new ArrayList<>();
		for (SurveyNumDetails surveyNumDetails : surveyNumList) {
			VentureDtlsDto ventureDtlsDto = new VentureDtlsDto();
			final SurveyDto surveyDto = this.areaMapper
					.mapToSurveyDto(surveyNumDetails);
			final VillageDto villageDto = this.areaMapper
					.mapToVillageDto(surveyNumDetails.getVillage());
			ventureDtlsDto.setSurveyDto(surveyDto);
			ventureDtlsDto.setVillage(villageDto);
			ventureDtoList.add(ventureDtlsDto);
		}
		return ventureDtoList;
	}

	@Override
	public List<UserPlotInfoDto> findUsersBySurveyId(long surveyId) {
		final SurveyNumDetails survey = this.areaRepository
				.findSurveyById(surveyId);
		final Set<PlotDetails> plotDetails = survey.getPlotDetails();
		final SurveyDto SurveyDto = this.areaMapper.mapToSurveyDto(survey);
		List<UserPlotInfoDto> userPlotInfoList = new ArrayList<>();
		for (PlotDetails plot : plotDetails) {
			UserPlotInfoDto userPlotInfo = new UserPlotInfoDto();
			final PlotDto plotDto = this.areaMapper.mapToPlotDto(plot);
			final UserDetailsDto user = this.userDetailsMapper
					.mapToUserDetailsDto(plot.getUserDetails());
			userPlotInfo.setPlotNumber(plotDto);
			userPlotInfo.setUser(user);
			userPlotInfo.setSurveyNumber(SurveyDto);
			userPlotInfoList.add(userPlotInfo);
		}

		return userPlotInfoList;
	}
}
