package v1.dewu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import v1.dewu.entity.CodeUser;
import v1.dewu.entity.Identification;
import v1.dewu.service.FileService;
import v1.dewu.service.IdentificationService;
import v1.dewu.service.UserService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static v1.dewu.controllers.HelperFunctions.*;

@RestController
public class UserController {
    private static final String PATHNAME = "photos/";
    @Autowired
    UserService userService;
    @Autowired
    IdentificationService identificationService;
    @Autowired
    FileService fileService;

    @PostMapping("/api/login")
    public boolean loginValidation(@RequestBody String code) {
        return userService.checkExistance(code);
    }

    @PostMapping("/api/create/{times}")
    public CodeUser createCodeUser(@PathVariable int times, @RequestBody String password) throws Exception {
        if(password.equals("dewu123")){
            CodeUser user = new CodeUser();
            user.setTimes(times);
            user.setCode(generateActivationCode());
            userService.saveOrUpdate(user);
            return user;
        }
        else
            throw new Exception("wrong password");
    }


    @PostMapping("/api/get/times")
    public int getTimes(@RequestBody String code){
        int times = userService.getUserById(code).getTimes();
        if(times == 0){
            userService.deleteUser(code);
            return times;
        }

        return times;
    }

    @GetMapping("/api/get/identification/{serialnumber}")
    public Identification getIdentification(@PathVariable long serialnumber){
        return identificationService.getIdenification(serialnumber);
    }

    @GetMapping("api/get/file/{filename:.+}")
    @ResponseBody
    public byte[] getFile(@PathVariable String filename) throws IOException {
        File file = fileService.load(filename);
        byte[] bytes = Files.readAllBytes(file.toPath());
        byte[] encode = java.util.Base64.getEncoder().encode(bytes);
        return encode;
    }

    @PostMapping("/api/identify")
    public long identify(@RequestParam("productName") String productName, @RequestParam("userCode") String userCode, @RequestParam("files") MultipartFile[] files, MultipartHttpServletRequest request) throws IOException {
        CodeUser user = userService.getUserById(userCode);
        user.consumeTimes();
        userService.saveOrUpdate(user);
        //String pathname = "photos/";
        Identification identification = new Identification();
        identification.setProductName(productName);
        long serialNumber = generateSerialNumber();
        identification.setSerialNumber(serialNumber);
        List<String> imageList = new ArrayList<>();
        for(int i = 0; i < files.length; i++) {
            String originalFilename = files[i].getOriginalFilename();
            String filename = fileService.save(files[i]);
            imageList.add(filename);
        }
        identification.setImagelist(imageList);
        identificationService.saveOrUpdate(identification);
        return serialNumber;
    }


}
