package aggregation.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
public class CameraDataAggregated {


    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    CameraSource cameraSource;

    TokenData tokenData;

    public CameraDataAggregated() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public CameraSource getCameraSource() {
        return cameraSource;
    }

    public void setCameraSource(CameraSource cameraSource) {
        this.cameraSource = cameraSource;
    }

    public Integer getCameraSourceId() {
        return cameraSource.getId();
    }

    public TokenData getTokenData() {
        return tokenData;
    }

    public void setTokenData(TokenData tokenData) {
        this.tokenData = tokenData;
    }

    public CameraDataAggregated(CameraSource cameraSource, TokenData tokenData) {
       this.cameraSource = cameraSource;
       this.tokenData = tokenData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CameraDataAggregated that)) return false;
        return Objects.equals(cameraSource, that.cameraSource) && Objects.equals(tokenData, that.tokenData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cameraSource, tokenData);
    }

    @Override
    public String toString() {

        return "urltype: " + cameraSource.urlType + ", videoUrl:"
                + cameraSource.videoUrl + ", value: " + tokenData.value + ", ttl: "
                + tokenData.ttl;

    }

}
