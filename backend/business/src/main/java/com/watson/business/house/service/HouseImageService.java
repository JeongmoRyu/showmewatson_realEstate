package com.watson.business.house.service;


import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface HouseImageService {
    List<String> addFile(List<MultipartFile> multipartFile, String dirName);
    void removeFile(String fileName);

    String createFileName(String fileName, String dirName);
        // 먼저 파일 업로드 시, 파일명을 난수화하기 위해 random으로 돌립니다.

    String getFileExtension(String fileName);
    // file 형식이 잘못된 경우를 확인하기 위해 만들어진 로직이며, 파일 타입과 상관없이 업로드할 수 있게 하기 위해 .의 존재 유무만 판단하였습니다
}
