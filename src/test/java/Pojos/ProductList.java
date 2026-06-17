package Pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductList{
    @JsonProperty("responseCode")
    public int getResponseCode() {
        return this.responseCode; }
    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode; }
    int responseCode;
    @JsonProperty("products")
    public ArrayList<Product> getProducts() {
        return this.products; }
    public void setProducts(ArrayList<Product> products) {
        this.products = products; }
    ArrayList<Product> products;
}


