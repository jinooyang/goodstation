package data;

import com.enjoytrip.station.model.dto.StationInfoDto;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class DataService {
    @Autowired
    private DataMapper dataMapper;

    void insInfNoLonLat(StationInfoDto dto) {
        dataMapper.insInfNoLonLat(dto);
    }

    List<StationInfoDto> selectAll() {
        return dataMapper.selectAll();
    }

    void addLongLat(StationInfoDto dto) {
        dataMapper.addLongLat(dto);
    }

    List<StationInfoDto> findStationPos(){
        return dataMapper.findStationPos();
    }

    List<AttractionBySidoDto> findAttractionBySidoCode(String sido){
        return dataMapper.findAttractionBySidoCode(sido);
    }
    void addStTourSpot(TourSpotInsertDto t){
//        System.out.println(map.toString());
        dataMapper.addStTourSpot(t);
    }

}
