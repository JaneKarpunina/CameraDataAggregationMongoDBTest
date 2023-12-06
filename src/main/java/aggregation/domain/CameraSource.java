package aggregation.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class CameraSource {

    public CameraSource() {
    }

    public CameraSource(Integer id, String urlType, String videoUrl) {
        this.id = id;
        this.urlType = urlType;
        this.videoUrl = videoUrl;
    }

    Integer id;

    @JsonProperty("url_type")
    String urlType;

    @JsonProperty("video_url")
    String videoUrl;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrlType() {
        return urlType;
    }

    public void setUrlType(String urlType) {
        this.urlType = urlType;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CameraSource that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(urlType, that.urlType) && Objects.equals(videoUrl, that.videoUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, urlType, videoUrl);
    }
}
