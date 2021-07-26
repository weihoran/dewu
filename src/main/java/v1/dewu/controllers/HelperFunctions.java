package v1.dewu.controllers;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class HelperFunctions {

    //生成随机激活码
    public static String generateActivationCode() {
        /*
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        int random1 = new Random().nextInt(1000);
        int random2 = new Random().nextInt(1000);
        return random1 + dateFormat.format(date) + random2;
        */
        return String.valueOf(System.currentTimeMillis());
    }

    public static long generateSerialNumber() {
        return Long.parseLong(String.valueOf(System.currentTimeMillis()).substring(0,9));
    }

    //获取文件后缀
    public static String getSuffix(String filename){
        String suffix = "";
        int beginIndex = filename.lastIndexOf(".");
        if (beginIndex > 0)
            suffix = filename.substring(beginIndex);

        return suffix;
    }

    public static String saveFileToSystem(String originalFilename, String pathname, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(pathname);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        String suffix = getSuffix(originalFilename);
        //确保唯一性，有风险
        String filename = System.currentTimeMillis() + "" + System.nanoTime() % 100000 + suffix;
        System.out.println(filename);
        // 保存的目标文件
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(filename);
            System.out.println(filePath.toString());
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + filename, ioe);
        }
        return filename;
    }


}
