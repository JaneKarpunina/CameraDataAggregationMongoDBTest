package aggregation.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Camera {

    public Camera() {

    }

    public Camera(Integer id, String sourceDataUrl, String tokenDataUrl) {
        this.id = id;
        this.sourceDataUrl = sourceDataUrl;
        this.tokenDataUrl = tokenDataUrl;
    }

    Integer id;

    @JsonProperty("source_data_url")
    String sourceDataUrl;

    @JsonProperty("token_data_url")
    String tokenDataUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSourceDataUrl() {
        return sourceDataUrl;
    }

    public void setSourceDataUrl(String sourceDataUrl) {
        this.sourceDataUrl = sourceDataUrl;
    }

    public String getTokenDataUrl() {
        return tokenDataUrl;
    }

    public void setTokenDataUrl(String tokenDataUrl) {
        this.tokenDataUrl = tokenDataUrl;
    }
}
