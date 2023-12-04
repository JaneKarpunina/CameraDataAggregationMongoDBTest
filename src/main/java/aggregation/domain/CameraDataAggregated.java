package aggregation.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CameraDataAggregated {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
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
