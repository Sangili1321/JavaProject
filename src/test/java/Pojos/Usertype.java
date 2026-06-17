package Pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//We need JsonProperty only when the variable name is not matching with jsonKey
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usertype{
    @JsonProperty("usertype")
    public String getUsertype() {
        return this.usertype; }
    public void setUsertype(String usertype) {
        this.usertype = usertype; }
    String usertype;
}
