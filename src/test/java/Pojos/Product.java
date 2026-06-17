package Pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product{
    @JsonProperty("id")
    public int getId() {
        return this.id; }
    public void setId(int id) {
        this.id = id; }
    int id;
    @JsonProperty("name")
    public String getName() {
        return this.name; }
    public void setName(String name) {
        this.name = name; }
    String name;
    @JsonProperty("price")
    public String getPrice() {
        return this.price; }
    public void setPrice(String price) {
        this.price = price; }
    String price;
    @JsonProperty("brand")
    public String getBrand() {
        return this.brand; }
    public void setBrand(String brand) {
        this.brand = brand; }
    String brand;
    @JsonProperty("category")
    public Category getCategory() {
        return this.category; }
    public void setCategory(Category category) {
        this.category = category; }
    Category category;
}
