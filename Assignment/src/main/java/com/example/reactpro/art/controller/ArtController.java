package com.example.reactpro.art.controller;

import com.example.reactpro.art.dto.ArtDTO;
import com.example.reactpro.art.service.ArtService;
import com.example.reactpro.common.ResponseDto;
import com.example.reactpro.common.paging.Pagenation;
import com.example.reactpro.common.paging.ResponseDtoWithPaging;
import com.example.reactpro.common.paging.SelectCriteria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@Slf4j
@RestController
@RequestMapping("/api/v1")
public class ArtController {
    private final ArtService artService;


    public ArtController(ArtService artService) {
        this.artService = artService;
    }

    @GetMapping("/arts/{artAge}")
    public ResponseEntity<ResponseDto> selectArtListWithPaging(@RequestParam(name="offset", defaultValue="1") String offset, @PathVariable String artAge) {

        log.info("[ArtController] selectArtListWithPaging : " + offset+", isThatTrue?"+ (artAge.equals("modern")) +", artAge : "+artAge);
        int totalCount = 0;

            if(artAge.equals("modern")){
              totalCount = artService.selectModernArtTotal(artAge);
            }else{
                totalCount = artService.selectContemporaryOrMiddleArtTotal(artAge);
            }


        System.out.println("totalCount = " + totalCount);
        int limit = 9;
        int buttonAmount = 3;
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(Integer.parseInt(offset), totalCount, limit, buttonAmount, artAge);;

        log.info("[ProductController] selectCriteria : " + selectCriteria);

        ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
        responseDtoWithPaging.setPageInfo(selectCriteria);
        responseDtoWithPaging.setData(artService.selectArtListWithPaging(selectCriteria));

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", responseDtoWithPaging));
    }


    @GetMapping("/art/{artNo}")
    public ResponseEntity<ResponseDto> selectArtDetail(@PathVariable int artNo) {

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "작품 조회 성공",  artService.selectArt(artNo)));
    }

    @PostMapping(value = "/arts-management")
    public ResponseEntity<ResponseDto> insertArt(@ModelAttribute ArtDTO artDTO) {
        log.info("[ArtController] PostMapping artDTO : " + artDTO);
        System.out.println("artDTO = " + artDTO);
        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "작품 등록 성공",  artService.insertArt(artDTO)));
    }

    @PutMapping(value = "/arts-management/{artNo}")
    public ResponseEntity<ResponseDto> updateArt(@ModelAttribute ArtDTO artDTO, @PathVariable int artNo) {
        log.info("[ArtController]PutMapping artDTO : " + artDTO);
        artDTO.setArtNo(artNo);
        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "작품 업데이트 성공",  artService.updateArt(artDTO)));
    }

    @DeleteMapping(value = "/arts-management/{artNo}")
    public ResponseEntity<ResponseDto> deleteArt(@PathVariable int artNo){
        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "작품 삭제 성공",  artService.deleteArt(artNo)));

    }
}
