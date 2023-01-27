package com.example.reactpro.art.service;

import com.example.reactpro.art.dao.ArtMapper;
import com.example.reactpro.art.dto.ArtDTO;
import com.example.reactpro.common.paging.SelectCriteria;
import com.example.reactpro.util.FileUploadUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
@Slf4j
@Service
public class ArtService {

    @Value("${image.image-dir}")
    private String IMAGE_DIR;
    @Value("${image.image-url}")
    private String IMAGE_URL;

    private final ArtMapper artMapper;

    public ArtService(ArtMapper artMapper) {
        this.artMapper = artMapper;
    }

    public ArtDTO selectArt(int artNo) {
        log.info("[ArtService] selectArt Start ===================================");
        ArtDTO artDTO = artMapper.selectArt(artNo);
        artDTO.setArtImageUrl(IMAGE_URL + artDTO.getArtImageUrl());
        log.info("[ArtService] selectArt End ===================================");
        return artDTO;
    }




    public Object selectArtListWithPaging(SelectCriteria selectCriteria) {
        log.info("[ArtService] selectArtListWithPaging Start ===================================");
        List<ArtDTO> artList = artMapper.selectArtListWithPaging(selectCriteria);

        for(int i = 0 ; i <artList.size() ; i++) {
            artList.get(i).setArtImageUrl(IMAGE_URL + artList.get(i).getArtImageUrl());
        }
        log.info("[ArtService] selectArtListWithPaging End ===================================");
        return artList;
    }

    @Transactional
    public Object updateArt(ArtDTO artDTO) {
        log.info("[ArtService] updateArt Start ===================================");
        log.info("[ArtService] artDTO : " + artDTO);
        String replaceFileName = null;
        int result = 0;

        try {
            String oriImage =artMapper.selectArt(artDTO.getArtNo()).getArtImageUrl();
            System.out.println("artDTO.getArtNo() : "+artDTO.getArtNo());
            log.info("[updateArt] oriImage : " + oriImage);

            if(artDTO.getArtImage() != null){
                // 이미지 변경 진행
                String imageName = UUID.randomUUID().toString().replace("-", "");
                replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, artDTO.getArtImage());

                log.info("[updateArt] IMAGE_DIR!!"+ IMAGE_DIR);
                log.info("[updateArt] imageName!!"+ imageName);

                log.info("[updateArt] InsertFileName : " + replaceFileName);
                artDTO.setArtImageUrl(replaceFileName);

                log.info("[updateArt] deleteImage : " + oriImage);
                boolean isDelete = FileUploadUtils.deleteFile(IMAGE_DIR, oriImage);
                log.info("[update] isDelete : " + isDelete);
            } else {
                // 이미지 변경 없을 시
                artDTO.setArtImageUrl(oriImage);
            }

            result = artMapper.updateArt(artDTO);

        } catch (IOException e) {
            log.info("[updateArt] Exception!!");
            FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileName);
            throw new RuntimeException(e);
        }
        log.info("[ArtService] updateArt End ===================================");
        log.info("[ArtService] result > 0 성공: "+ result);

        return (result > 0) ? "작품 업데이트 성공" : "작품 업데이트 실패";
    }

    @Transactional
    public String insertArt(ArtDTO artDTO) {
        log.info("[ArtService] insertArt Start ===================================");
        log.info("[ArtService] artDTO : " + artDTO);
        String imageName = UUID.randomUUID().toString().replace("-", "");
        String replaceFileName = null;
        int result = 0;
        log.info("[ArtService] IMAGE_DIR : " + IMAGE_DIR);
        log.info("[ArtService] imageName : " + imageName);
        try {
            replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, artDTO.getArtImage());
            log.info("[ArtService] replaceFileName : " + replaceFileName);

            artDTO.setArtImageUrl(replaceFileName);

            log.info("[ArtService] insert Image Name : "+ replaceFileName);

            result = artMapper.insertArt(artDTO);

        } catch (IOException e) {
            log.info("[ArtService] IOException IMAGE_DIR : "+ IMAGE_DIR);

            log.info("[ArtService] IOException deleteFile : "+ replaceFileName);

            FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileName);
            throw new RuntimeException(e);
        }
        log.info("[ArtService] result > 0 성공: "+ result);
        return (result > 0) ? "작품 입력 성공" : "작품 입력 실패";
    }



    public String deleteArt(int artNo) {
        log.info("[ArtService] deleteArt Start ===================================");
        int result = artMapper.deleteArt(artNo);

        log.info("[ArtService] deleteArt End ===================================");
        return (result > 0) ? "작품 삭제 성공" : "작품 삭제 실패";
    }

//    public int selectModernArtTotal(String artAge) {
//        log.info("[ArtService] selectModernArtTotal Start ==================================="+artAge);
//
//
//        int result = artMapper.selectArtTotal();
//
//        log.info("[ArtService] selectArtTotal End ===================================");
//        return result;
//    }
//
    public int selectContemporaryOrMiddleArtTotal(String artAge) {
        log.info("[ArtService] selectContemporaryOrMiddleArtTotal Start ==================================="+artAge);
        int result = artMapper.selectArtTotal(artAge);

        log.info("[ArtService] selectContemporaryOrMiddleArtTotal End ===================================");
        return result;
    }

    public int selectModernArtTotal(String artAge) {
        int result = artMapper.selectArtTotal(artAge);

        log.info("[ArtService] selectModernArtTotal End ===================================");
        return result;
    }
}
