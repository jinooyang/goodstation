package data;

import com.enjoytrip.station.model.dto.StationInfoDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DataMapper {
    void insInfNoLonLat(StationInfoDto dto);
    List<StationInfoDto> selectAll ();
    void addLongLat(StationInfoDto dto);
    List<StationInfoDto> findStationPos();

    List<AttractionBySidoDto> findAttractionBySidoCode(String sido);

    void addStTourSpot(TourSpotInsertDto t);
}
