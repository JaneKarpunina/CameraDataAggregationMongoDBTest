package aggregation.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CameraDataAggregated {
    CameraSource cameraSource;

    TokenData tokenData;

    public CameraDataAggregated(CameraSource cameraSource, TokenData tokenData) {
       this.cameraSource = cameraSource;
       this.tokenData = tokenData;
    }

    @Override
    public String toString() {

        return "urltype: " + cameraSource.urlType + ", videoUrl:"
                + cameraSource.videoUrl + ", value: " + tokenData.value + ", ttl: "
                + tokenData.ttl;

    }






}
