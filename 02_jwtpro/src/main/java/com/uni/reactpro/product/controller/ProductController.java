package com.uni.reactpro.product.controller;

import com.uni.reactpro.common.ResponseDto;
import com.uni.reactpro.common.paging.Pagenation;
import com.uni.reactpro.common.paging.ResponseDtoWithPaging;
import com.uni.reactpro.common.paging.SelectCriteria;
import com.uni.reactpro.product.dto.ProductDto;
import com.uni.reactpro.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class ProductController {


    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<ResponseDto> selectProductListWithPaging(@RequestParam(name="offset", defaultValue="1") String offset) {

        log.info("[ProductController] selectProductListWithPaging : " + offset);

        int totalCount = productService.selectProductTotal();
        int limit = 10;
        int buttonAmount = 5;
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(Integer.parseInt(offset), totalCount, limit, buttonAmount);;

        log.info("[ProductController] selectCriteria : " + selectCriteria);

        ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
        responseDtoWithPaging.setPageInfo(selectCriteria);
        responseDtoWithPaging.setData(productService.selectProductListWithPaging(selectCriteria));

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", responseDtoWithPaging));
    }

    @GetMapping("/products-management")
    public ResponseEntity<ResponseDto> selectProductListWithPagingForAdmin(@RequestParam(name="offset", defaultValue="1") String offset) {

        log.info("[ProductController] selectProductListWithPagingForAdmin : " + offset);

        int totalCount = productService.selectProductTotalForAdmin();
        int limit = 10;
        int buttonAmount = 5;
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(Integer.parseInt(offset), totalCount, limit, buttonAmount);;

        log.info("[ProductController] selectCriteria : " + selectCriteria);

        ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
        responseDtoWithPaging.setPageInfo(selectCriteria);
        responseDtoWithPaging.setData(productService.selectProductListWithPagingForAdmin(selectCriteria));

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", responseDtoWithPaging));
    }

    @GetMapping("/products-management/{productCode}")
    public ResponseEntity<ResponseDto> selectProductDetailForAdmin(@PathVariable String productCode) {

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "상품 상세정보 조회 성공",  productService.selectProductForAdmin(productCode)));
    }

    @GetMapping("/products/meals")
    public ResponseEntity<ResponseDto> selectProductListAboutMeal() {

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공",  productService.selectProductListAboutMeal()));
    }

    @GetMapping("/products/dessert")
    public ResponseEntity<ResponseDto> selectProductListAboutDessert() {

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공",  productService.selectProductListAboutDessert()));
    }

    @GetMapping("/products/beverage")
    public ResponseEntity<ResponseDto> selectProductListAboutBeverage() {

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공",  productService.selectProductListAboutBeverage()));
    }

    @GetMapping("/products/search")
    public ResponseEntity<ResponseDto> selectSearchList(@RequestParam(name="s", defaultValue="all") String search) {

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공",  productService.selectSearchProductList(search)));
    }

    @GetMapping("/products/{productCode}")
    public ResponseEntity<ResponseDto> selectProductDetail(@PathVariable String productCode) {

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "상품 상세정보 조회 성공",  productService.selectProduct(productCode)));
    }

    @PostMapping(value = "/products")
    public ResponseEntity<ResponseDto> insertProduct(@ModelAttribute ProductDto productDto) {
        log.info("[ProductController] PostMapping productDto : " + productDto);
        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "상품 입력 성공",  productService.insertProduct(productDto)));
    }

    @PutMapping(value = "/products")
    public ResponseEntity<ResponseDto> updateProduct(@ModelAttribute ProductDto productDto) {
        log.info("[ProductController]PutMapping productDto : " + productDto);

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "상품 업데이트 성공",  productService.updateProduct(productDto)));
    }

}
