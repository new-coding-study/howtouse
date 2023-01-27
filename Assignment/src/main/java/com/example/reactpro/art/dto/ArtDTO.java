package com.example.reactpro.art.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
@NoArgsConstructor
@Data
@AllArgsConstructor
public class ArtDTO {
    private int artNo;
    private String artName;
    private String authorName;
    private String artDescription;
    private String artPlace;
    private String artAge;
    private MultipartFile artImage;
    private String artImageUrl;

    private String artMovement;
    private String artYear;

    public int getArtNo() {
        return artNo;
    }

    public void setArtNo(int artNo) {
        this.artNo = artNo;
    }

    public String getArtName() {
        return artName;
    }

    public void setArtName(String artName) {
        this.artName = artName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getArtDescription() {
        return artDescription;
    }

    public void setArtDescription(String artDescription) {
        this.artDescription = artDescription;
    }

    public String getArtPlace() {
        return artPlace;
    }

    public void setArtPlace(String artPlace) {
        this.artPlace = artPlace;
    }

    public String getArtAge() {
        return artAge;
    }

    public void setArtAge(String artAge) {
        this.artAge = artAge;
    }

    public MultipartFile getArtImage() {
        return artImage;
    }

    public void setArtImage(MultipartFile artImage) {
        this.artImage = artImage;
    }

    public String getArtImageUrl() {
        return artImageUrl;
    }

    public void setArtImageUrl(String artImageUrl) {
        this.artImageUrl = artImageUrl;
    }

    public String getArtMovement() {
        return artMovement;
    }

    public void setArtMovement(String artMovement) {
        this.artMovement = artMovement;
    }

    public String getArtYear() {
        return artYear;
    }

    public void setArtYear(String artYear) {
        this.artYear = artYear;
    }
}
