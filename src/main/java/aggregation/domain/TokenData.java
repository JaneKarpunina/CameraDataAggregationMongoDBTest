package aggregation.domain;

import java.util.Objects;

public class TokenData {

    public TokenData() {
    }

    public TokenData(Integer id, String value, String ttl) {
        this.id = id;
        this.value = value;
        this.ttl = ttl;
    }

    Integer id;

    String value;

    String ttl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TokenData tokenData)) return false;
        return Objects.equals(id, tokenData.id) && Objects.equals(value, tokenData.value) && Objects.equals(ttl, tokenData.ttl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, ttl);
    }
}
