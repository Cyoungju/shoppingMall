package com.example.demo.product;

import com.example.demo.core.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductController {
    private final ProductService productService;

    // RestController - 에러코드도 같이 적어줘야함? - 에러코드 필요함

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ProductResponse.FindAllDTO product){
        Product save = productService.save(product);

        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(save);
        return ResponseEntity.ok(apiResult);
    }

    // ** 전체 상품 확인
    @GetMapping("/products")
    public ResponseEntity<?> findAll(@RequestParam(defaultValue = "0") int page){
        List<ProductResponse.FindAllDTO> productResponses = productService.findAll(page);
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(productResponses);
        return ResponseEntity.ok(apiResult);
    }

    // ** 개별 상품 확인
    @GetMapping("/products/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        ProductResponse.FindByIdDTO productDTOS = productService.findById(id);
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(productDTOS);
        return ResponseEntity.ok(apiResult);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProductResponse.FindByIdDTO productDTO) {
        Product update = productService.update(id, productDTO);
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(update);
        return ResponseEntity.ok(apiResult);
    }


    @GetMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        productService.delete(id);
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success("success");
        return ResponseEntity.ok(apiResult);
    }


}