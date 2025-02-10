package com.biblio.web.cloudfiles.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class FileStorageService {

    private final Cloudinary cloudinary;
    public FileStorageService(Cloudinary cloudinary){
        this.cloudinary=cloudinary;
    }

    public String uploadFile(MultipartFile file, String folder) throws IOException {
        Map<String, String> parameter = ObjectUtils.asMap("folder",folder);
        return cloudinary.uploader().upload(file.getBytes(),parameter).get("url").toString();
    }

    public void deleteFile(String idFile) throws Exception {
         cloudinary.api().deleteResources(List.of(idFile),null);
    }

}