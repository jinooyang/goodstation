package data;

import lombok.Data;

import java.util.List;
@Data
public class TourSpotInsertDto {
    private String name ;
    private List<String> contentIDs;
}
