package com.negoreserva.common.feature.concrete.product.enums;

import com.negoreserva.common.feature.concrete.product.model.ProductFile;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.random.RandomGenerator;

@Getter
@AllArgsConstructor
public enum ProductFileFaker {
    IMAGE_1(ProductFile.builder().title("Image 1").description("First test image").url("https://example.com/image1.jpg").type(ProductFileType.IMAGE).build()),
    IMAGE_2(ProductFile.builder().title("Image 2").description("Second test image").url("https://example.com/image2.jpg").type(ProductFileType.IMAGE).build()),
    VIDEO_1(ProductFile.builder().title("Video 1").description("First test video").url("https://example.com/video1.mp4").type(ProductFileType.VIDEO).build()),
    VIDEO_2(ProductFile.builder().title("Video 2").description("Second test video").url("https://example.com/video2.mp4").type(ProductFileType.VIDEO).build());

    private final ProductFile productFile;

    public static List<ProductFile> listProductFiles() {
        return Arrays.stream(ProductFileFaker.values()).map(ProductFileFaker::getProductFile).toList();
    }

    public static ProductFile random() {
        var files = listProductFiles();
        var random = RandomGenerator.getDefault();
        var index = random.nextInt(files.size());
        return files.get(index);
    }
}