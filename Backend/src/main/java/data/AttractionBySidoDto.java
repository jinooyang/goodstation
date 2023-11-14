package data;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AttractionBySidoDto {
    private String content_id;
    private String sido_code;
    private BigDecimal longitude;
    private BigDecimal latitude;

}
