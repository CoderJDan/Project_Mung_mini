package com.mung.square.review;

import com.mung.square.dto.ReviewFileDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileUploadService {
    //설정파일에 정의된 property정보를 가져와서 어느 위치에 업로드해야 하는지 받아오기
    @Value("${file.dir}")//C/fullstack7/upload/
    private String uploadpath;
    //파일명을 전달받아서 업로드 폴더경로와 파일명을 연결해서 실제 어느 위치에 어떤 파일로 저장될지 full path를 만들어서 리턴하는 메소드
    public String getUploadpath(String filename){
        return uploadpath + filename;
    }

    //실제 파일 업로드를 실행하는 메소드
    //업로드되는 파일의 정보를 List로 만들어서 반환
    public List<ReviewFileDTO> uploadFiles(List<MultipartFile> multipartFiles) throws IOException {
        //업로드되는 파일의 정보가 저장될 List
        List<ReviewFileDTO> fileDTOList = new ArrayList<>();
        //매개변수로 전달된 업로드할 파일정보가 저장된 List에 있는 모든 MultipartFile을 꺼내서 업로드
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {//파일의 내용이 empty상태인지 평가
                //원본파일명을 추출
                String originalFilename = multipartFile.getOriginalFilename();
                //저장파일명생성
                String storeFilename = createStoreFrilename(originalFilename);
                System.out.println(storeFilename +":"+originalFilename);
                //파일을 업로드
                //transferTo메소드는 지정된 경로의 파일명으로 파일객체를 생성
                multipartFile.transferTo(new File(getUploadpath(storeFilename)));
                //업로드되는 정보를 dto로 만들어서 List에 add
                String fileUrl = "/uploads/" + storeFilename;
                fileDTOList.add(new ReviewFileDTO(UUID.randomUUID().toString(),
                        null,originalFilename,storeFilename, fileUrl));
            }
        }
        return fileDTOList;
    }
    public String getUploadPath(String filename) {
        return uploadpath + filename; // uploadPath는 설정 파일에서 읽음
    }
    //저장되는 파일명을 리턴하는 메소드 - UUID를 이용해서 파일명을 생성
    //원본파일을 받아서 저장되는 파일명을 만들어서 리턴
    private String createStoreFrilename(String originalFilename){
        //확장자를 추출
        int position = originalFilename.lastIndexOf(".");//확장자 위치 구하기
        String ext = originalFilename.substring(position+1);
        String uuid = UUID.randomUUID().toString();
        return uuid+"."+ext;

    }

}
