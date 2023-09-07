package com.example.entity.goods;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record ImageUploadRequest(
        MultipartFile goodsImgFile
) {
}