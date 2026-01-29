package in.rohanshukla.fruitsapi.service;

import in.rohanshukla.fruitsapi.request.Fruitrequest;
import in.rohanshukla.fruitsapi.request.Fruitresponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface Fruitservice {

    String uploadFile(MultipartFile file);

    Fruitresponse addFruit(Fruitrequest request, MultipartFile file);

    List<Fruitresponse> readfruit();

    Fruitresponse readFruitById(String id);

    boolean deleteFile(String filename);

    void deleteFruit(String id);   // ← FINAL CORRECT
}
