package com.site.patinha_feliz.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FotoDTO {

    @JsonProperty("url_s3")
    private String urlS3;
    @JsonProperty("url")
    private String url;
    @JsonProperty("descricao")
    private String descricao;
}