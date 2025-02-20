package com.biblio.web.cloudfiles.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Log4j2
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

    public void deleteFile(String pathPhoto,String folder) throws Exception {
        if(pathPhoto.isEmpty()){
            log.warn("not exist path photo current");
            return;
        }

         String idFile = getIdFile(pathPhoto, folder);
         cloudinary.api().deleteResources(List.of(idFile),null);
    }

    private String getIdFile(String pathPhoto, String folder){
        return pathPhoto.substring(pathPhoto.lastIndexOf(folder+"/"),pathPhoto.lastIndexOf("."));
    }

}