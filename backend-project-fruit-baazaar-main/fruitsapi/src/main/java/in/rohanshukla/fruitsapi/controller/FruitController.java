package in.rohanshukla.fruitsapi.controller;

import in.rohanshukla.fruitsapi.request.Fruitrequest;
import in.rohanshukla.fruitsapi.request.Fruitresponse;
import in.rohanshukla.fruitsapi.service.Fruitservice;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/fruit")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://localhost:5174"
})
@AllArgsConstructor
public class FruitController {

    private final Fruitservice fruitService;

    // ✅ ADD FRUIT
    @PostMapping(consumes = "multipart/form-data")
    public Fruitresponse addFruit(
            @RequestPart("fruit") Fruitrequest request,
            @RequestPart("file") MultipartFile file
    ) {
        return fruitService.addFruit(request, file);
    }

    // ✅ GET ALL FRUITS
    @GetMapping
    public List<Fruitresponse> readFruit() {
        return fruitService.readfruit();
    }

    // ✅ GET FRUIT BY ID
    @GetMapping("/{id}")
    public Fruitresponse readFruit(@PathVariable String id) {
        return fruitService.readFruitById(id);
    }

    // ✅ DELETE FRUIT
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFruit(@PathVariable String id) {
        fruitService.deleteFruit(id);
    }
}
