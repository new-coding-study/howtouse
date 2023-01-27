package com.uni.reactpro.product.dto;

import org.springframework.web.multipart.MultipartFile;

public class ProductDto {
    private Long productCode;
    private String productName;
    private String productPrice;
    private String productDescription;
    private String productOrderable;
    private Long categoryCode;
    private String categoryName;
    private MultipartFile productImage;
    private String productImageUrl;
    private Long productStock;

    public ProductDto() {
    }

    public ProductDto(Long productCode, String productName, String productPrice,
                      String productDescription, String productOrderable, Long categoryCode,
                      String categoryName, MultipartFile productImage, String productImageUrl, Long productStock) {
        this.productCode = productCode;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
        this.productOrderable = productOrderable;
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
        this.productImage = productImage;
        this.productImageUrl = productImageUrl;
        this.productStock = productStock;
    }

    public Long getProductCode() {
        return productCode;
    }

    public void setProductCode(Long productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductOrderable() {
        return productOrderable;
    }

    public void setProductOrderable(String productOrderable) {
        this.productOrderable = productOrderable;
    }

    public Long getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(Long categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public MultipartFile getProductImage() {
        return productImage;
    }

    public void setProductImage(MultipartFile productImage) {
        this.productImage = productImage;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public Long getProductStock() {
        return productStock;
    }

    public void setProductStock(Long productStock) {
        this.productStock = productStock;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "productCode=" + productCode +
                ", productName='" + productName + '\'' +
                ", productPrice='" + productPrice + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productOrderable='" + productOrderable + '\'' +
                ", categoryCode=" + categoryCode +
                ", categoryName='" + categoryName + '\'' +
                ", productImage=" + productImage +
                ", productImageUrl='" + productImageUrl + '\'' +
                ", productStock=" + productStock +
                '}';
    }
}
