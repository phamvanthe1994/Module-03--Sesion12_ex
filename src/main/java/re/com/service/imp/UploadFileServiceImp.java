package re.com.service.imp;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import re.com.service.UploadFileService;

import java.util.Map;

@Service
public class UploadFileServiceImp implements UploadFileService {
    //Upload file multipart lên cloudiary và nhận lại đường dẫn ảnh trên cloudiary
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public String uploadFile(MultipartFile file) {
        //1. Lấy ra tên ảnh trong file

        String originalFilename = file.getOriginalFilename();

        if (originalFilename != null && originalFilename.contains(".")) {
            originalFilename = originalFilename.substring(0, originalFilename.lastIndexOf("."));
        }
        //2. upload file lên cloudiary giữ nguyên tên ảnh
        Map cloudiaryParams = ObjectUtils.asMap("public_id", originalFilename);
        Map cloudResult = null;
        try {
            cloudResult = cloudinary.uploader().upload(file.getBytes(), cloudiaryParams);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi upload file: " + e.getMessage());
        }
        return cloudResult.get("url").toString();
    }
}