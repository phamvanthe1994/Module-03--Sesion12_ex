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
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public String uploadFile(MultipartFile file) {
        try {
            // Lấy tên file mà không cần phần mở rộng
            String originalFilename = file.getOriginalFilename();
            String publicId = null;

            if (originalFilename != null && originalFilename.contains(".")) {
                publicId = originalFilename.substring(0, originalFilename.lastIndexOf("."));
            } else {
                publicId = originalFilename;
            }

            Map uploadResult = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.asMap("public_id", publicId, "resource_type", "auto")
            );

            if (uploadResult == null || uploadResult.isEmpty()) {
                throw new RuntimeException("Upload file thất bại! Cloudinary trả về null.");
            }

            // Cloudinary trả về secure_url
            return uploadResult.get("secure_url").toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
