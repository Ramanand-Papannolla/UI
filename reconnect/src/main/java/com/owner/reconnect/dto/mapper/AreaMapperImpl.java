package com.owner.reconnect.dto.mapper;

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
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_201 (Oracle Corporation)"
)
@Component
public class AreaMapperImpl implements AreaMapper {

    @Override
    public StateDto mapToStateDto(State state) {
        if ( state == null ) {
            return null;
        }

        StateDto stateDto = new StateDto();

        stateDto.setName( state.getName() );
        stateDto.setId( state.getId() );

        return stateDto;
    }

    @Override
    public List<StateDto> mapToStateDtos(List<State> states) {
        if ( states == null ) {
            return null;
        }

        List<StateDto> list = new ArrayList<StateDto>( states.size() );
        for ( State state : states ) {
            list.add( mapToStateDto( state ) );
        }

        return list;
    }

    @Override
    public DistrictDto mapToDistrictDto(District district) {
        if ( district == null ) {
            return null;
        }

        DistrictDto districtDto = new DistrictDto();

        districtDto.setName( district.getName() );
        districtDto.setId( district.getId() );

        return districtDto;
    }

    @Override
    public List<DistrictDto> mapToDistrictDtos(List<District> districts) {
        if ( districts == null ) {
            return null;
        }

        List<DistrictDto> list = new ArrayList<DistrictDto>( districts.size() );
        for ( District district : districts ) {
            list.add( mapToDistrictDto( district ) );
        }

        return list;
    }

    @Override
    public MandalDto mapToMandalDto(Mandal mandal) {
        if ( mandal == null ) {
            return null;
        }

        MandalDto mandalDto = new MandalDto();

        mandalDto.setName( mandal.getName() );
        mandalDto.setId( mandal.getId() );

        return mandalDto;
    }

    @Override
    public List<MandalDto> mapToMandalDtos(List<Mandal> mandals) {
        if ( mandals == null ) {
            return null;
        }

        List<MandalDto> list = new ArrayList<MandalDto>( mandals.size() );
        for ( Mandal mandal : mandals ) {
            list.add( mapToMandalDto( mandal ) );
        }

        return list;
    }

    @Override
    public VillageDto mapToVillageDto(Village village) {
        if ( village == null ) {
            return null;
        }

        VillageDto villageDto = new VillageDto();

        villageDto.setName( village.getName() );
        villageDto.setId( village.getId() );

        return villageDto;
    }

    @Override
    public List<VillageDto> mapToVillageDtos(List<Village> villages) {
        if ( villages == null ) {
            return null;
        }

        List<VillageDto> list = new ArrayList<VillageDto>( villages.size() );
        for ( Village village : villages ) {
            list.add( mapToVillageDto( village ) );
        }

        return list;
    }

    @Override
    public SurveyDto mapToSurveyDto(SurveyNumDetails survey) {
        if ( survey == null ) {
            return null;
        }

        SurveyDto surveyDto = new SurveyDto();

        if ( survey.getDocumentInfo() != null ) {
            if ( surveyDto.getDocumentDto() == null ) {
                surveyDto.setDocumentDto( new DocumentInfoDto() );
            }
            documentInfoToDocumentInfoDto( survey.getDocumentInfo(), surveyDto.getDocumentDto() );
        }
        if ( surveyDto.getDocumentDto() == null ) {
            surveyDto.setDocumentDto( new DocumentInfoDto() );
        }
        surveyNumDetailsToDocumentInfoDto( survey, surveyDto.getDocumentDto() );
        surveyDto.setName( survey.getName() );
        surveyDto.setId( survey.getId() );
        surveyDto.setSurveyNum( survey.getSurveyNumber() );

        return surveyDto;
    }

    @Override
    public List<SurveyDto> mapToSurveyDtos(List<SurveyNumDetails> surveys) {
        if ( surveys == null ) {
            return null;
        }

        List<SurveyDto> list = new ArrayList<SurveyDto>( surveys.size() );
        for ( SurveyNumDetails surveyNumDetails : surveys ) {
            list.add( mapToSurveyDto( surveyNumDetails ) );
        }

        return list;
    }

    @Override
    public PlotDto mapToPlotDto(PlotDetails plot) {
        if ( plot == null ) {
            return null;
        }

        PlotDto plotDto = new PlotDto();

        plotDto.setName( plot.getName() );
        plotDto.setId( plot.getId() );
        plotDto.setPlotNumber( plot.getPlotNumber() );

        return plotDto;
    }

    @Override
    public List<PlotDto> mapToPlotDtos(List<PlotDetails> plots) {
        if ( plots == null ) {
            return null;
        }

        List<PlotDto> list = new ArrayList<PlotDto>( plots.size() );
        for ( PlotDetails plotDetails : plots ) {
            list.add( mapToPlotDto( plotDetails ) );
        }

        return list;
    }

    @Override
    public SurveyNumDetails mapToSurveyNumDetails(SurveyDto surveyDto) {
        if ( surveyDto == null ) {
            return null;
        }

        SurveyNumDetails surveyNumDetails = new SurveyNumDetails();

        surveyNumDetails.setSurveyNumber( surveyDto.getSurveyNum() );
        surveyNumDetails.setId( surveyDto.getId() );
        surveyNumDetails.setName( surveyDto.getName() );

        return surveyNumDetails;
    }

    @Override
    public PlotDetails mapToPlotDetails(PlotDto plotDto) {
        if ( plotDto == null ) {
            return null;
        }

        PlotDetails plotDetails = new PlotDetails();

        plotDetails.setPlotNumber( plotDto.getPlotNumber() );
        plotDetails.setId( plotDto.getId() );
        plotDetails.setName( plotDto.getName() );

        return plotDetails;
    }

    @Override
    public DocumentInfo mapToDocumentInfo(DocumentInfoDto surveyDoc) {
        if ( surveyDoc == null ) {
            return null;
        }

        DocumentInfo documentInfo = new DocumentInfo();

        documentInfo.setName( surveyDoc.getTitle() );
        documentInfo.setType( surveyDoc.getType() );
        try {
            documentInfo.setDocument( getBlob( surveyDoc.getImage() ) );
        }
        catch ( ReconnectException e ) {
            throw new RuntimeException( e );
        }

        return documentInfo;
    }

    protected void documentInfoToDocumentInfoDto(DocumentInfo documentInfo, DocumentInfoDto mappingTarget) {
        if ( documentInfo == null ) {
            return;
        }

        mappingTarget.setTitle( documentInfo.getName() );
        mappingTarget.setType( documentInfo.getType() );
    }

    protected void surveyNumDetailsToDocumentInfoDto(SurveyNumDetails surveyNumDetails, DocumentInfoDto mappingTarget) {
        if ( surveyNumDetails == null ) {
            return;
        }

        try {
            mappingTarget.setImage( getImageString( surveyNumDetails.getDocumentInfo() ) );
        }
        catch ( ReconnectException e ) {
            throw new RuntimeException( e );
        }
    }
}
