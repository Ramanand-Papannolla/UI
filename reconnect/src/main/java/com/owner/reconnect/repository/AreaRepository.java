package com.owner.reconnect.repository;

import java.util.List;

import com.owner.reconnect.entities.District;
import com.owner.reconnect.entities.Mandal;
import com.owner.reconnect.entities.PlotDetails;
import com.owner.reconnect.entities.State;
import com.owner.reconnect.entities.SurveyNumDetails;
import com.owner.reconnect.entities.Village;

public interface AreaRepository {
	List<State> findAllStates();

	List<District> findAllDistrictsByState(Long stateId);

	List<Mandal> findAllMandalsByDistrict(Long districtId);

	List<Village> findAllVillagesByMondal(Long mondalId);

	List<SurveyNumDetails> findAllSurveyNumsByVillage(Long villageId);

	List<PlotDetails> findAllPlotsBySurveyNum(Long surveyId);

	State findSateById(long stateId);

	District findDistrictById(long districtId);

	Mandal findMandalById(long mandalId);

	Village findVillageById(long villageId);

	SurveyNumDetails findSurveyById(Long id);

	PlotDetails findPlotById(Long id);

	PlotDetails addPlotDetails(PlotDetails newPlot);

}
