package com.owner.reconnect.dto.mapper;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import com.owner.reconnect.dto.DistrictDto;
import com.owner.reconnect.dto.DocumentInfoDto;
import com.owner.reconnect.dto.MandalDto;
import com.owner.reconnect.dto.PlotDto;
import com.owner.reconnect.dto.StateDto;
import com.owner.reconnect.dto.SurveyDto;
import com.owner.reconnect.dto.VillageDto;
import com.owner.reconnect.entities.District;
import com.owner.reconnect.entities.DocumentInfo;
import com.owner.reconnect.entities.Mandal;
import com.owner.reconnect.entities.PlotDetails;
import com.owner.reconnect.entities.State;
import com.owner.reconnect.entities.SurveyNumDetails;
import com.owner.reconnect.entities.Village;
import com.owner.reconnect.exception.ReconnectException;

@Mapper
public interface AreaMapper {

	@Mappings({ @Mapping(target = "id", source = "id"),
			@Mapping(target = "name", source = "name") })
	public StateDto mapToStateDto(State state);

	public List<StateDto> mapToStateDtos(List<State> states);

	@Mappings({ @Mapping(target = "id", source = "id"),
			@Mapping(target = "name", source = "name") })
	public DistrictDto mapToDistrictDto(District district);

	public List<DistrictDto> mapToDistrictDtos(List<District> districts);

	@Mappings({ @Mapping(target = "id", source = "id"),
			@Mapping(target = "name", source = "name") })
	public MandalDto mapToMandalDto(Mandal mandal);

	public List<MandalDto> mapToMandalDtos(List<Mandal> mandals);

	@Mappings({ @Mapping(target = "id", source = "id"),
			@Mapping(target = "name", source = "name") })
	public VillageDto mapToVillageDto(Village village);

	public List<VillageDto> mapToVillageDtos(List<Village> villages);

	@Mappings({
			@Mapping(target = "id", source = "id"),
			@Mapping(target = "name", source = "name"),
			@Mapping(target = "surveyNum", source = "surveyNumber"),
			@Mapping(target = "documentDto.title", source = "documentInfo.name"),
			@Mapping(target = "documentDto.type", source = "documentInfo.type"),
			@Mapping(target = "documentDto.image", source = "documentInfo", qualifiedByName = "imageString") })
	public SurveyDto mapToSurveyDto(SurveyNumDetails survey);

	public List<SurveyDto> mapToSurveyDtos(List<SurveyNumDetails> surveys);

	@Mappings({ @Mapping(target = "id", source = "id"),
			@Mapping(target = "name", source = "name") })
	public PlotDto mapToPlotDto(PlotDetails plot);

	public List<PlotDto> mapToPlotDtos(List<PlotDetails> plots);

	@Mappings({ @Mapping(target = "surveyNumber", source = "surveyNum") })
	public SurveyNumDetails mapToSurveyNumDetails(SurveyDto surveyDto);

	@Mappings({ @Mapping(target = "plotNumber", source = "plotNumber") })
	public PlotDetails mapToPlotDetails(PlotDto plotDto);

	@Mappings({
			@Mapping(target = "name", source = "title"),
			@Mapping(target = "type", source = "type"),
			@Mapping(target = "document", source = "image", qualifiedByName = "blob") })
	public DocumentInfo mapToDocumentInfo(DocumentInfoDto surveyDoc);

	@Named("blob")
	default Blob getBlob(String input) throws ReconnectException {
		Blob blob = null;
		if (null != input) {
			try {
				blob = new SerialBlob(input.getBytes());
			} catch (Exception e) {
				System.out.println("Exception occured while uploading image");
				throw new ReconnectException(
						"Exception occured while uploading image"
								+ e.getMessage());
			}

		}
		return blob;
	}

	@Named("imageString")
	default String getImageString(DocumentInfo blob) throws ReconnectException {
		String image = null;
		if (null != blob) {
			byte[] bytes;
			try {
				bytes = blob.getDocument().getBytes(1, (int) blob.getDocument().length());
				image = new String(bytes);
			} catch (SQLException e) {
				System.out.println("Exception occured while getting image");
				throw new ReconnectException(
						"Exception occured while uploading image :"
								+ e.getMessage());
			}

		}
		return image;
	}
}
