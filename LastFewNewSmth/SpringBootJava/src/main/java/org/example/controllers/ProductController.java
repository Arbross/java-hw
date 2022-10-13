package org.example.controllers;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.example.dto.productdto.CreateProductDTO;
import org.example.dto.productdto.ProductDTO;
import org.example.dto.productimagedto.ProductImageSaveDTO;
import org.example.dto.productitemdto.ProductItemDTO;
import org.example.entities.ProductImage;
import org.example.interfaces.IProductService;
import org.example.mapper.ApplicationMapper;
import org.example.repositories.ProductImageRepository;
import org.example.repositories.ProductRepository;
import org.example.storage.IStorageService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/product")
@Api(tags = "Product")
public class ProductController {
    private final IProductService productService;
    private final IStorageService storageService;
    private final ProductImageRepository productImageRepository;
    private final ProductRepository productRepository;
    private final ApplicationMapper mapper;

    @GetMapping("list")
    public List<ProductItemDTO> index() {
        List<ProductItemDTO> products = mapper.productToProductItemDTO_List(productRepository.findAll());
        return products;
    }

    @PostMapping("/")
    public String add(@Valid @RequestBody CreateProductDTO model) throws Exception {
        productService.Add(model);

        return "Okay!";
    }

    @PostMapping("upload")
    public ProductImageSaveDTO handleFileUpload(@RequestParam("productImage") MultipartFile file) {
        String fileName = storageService.store(file);

        ProductImage productImage =
                new ProductImage(fileName, 0);

        productImageRepository.save(productImage);

        return new ProductImageSaveDTO(productImage.getId(), fileName);
    }

    @GetMapping("/{id}")
    public ProductDTO get(@PathVariable int id) {
        return productService.Get(id);
    }

    @PutMapping("/")
    public String update(@Valid @RequestBody ProductDTO model) throws Exception {
        productService.Update(model);

        return "Okay!";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) throws Exception {
        productService.Delete(id);

        return "Okay!";
    }
}
