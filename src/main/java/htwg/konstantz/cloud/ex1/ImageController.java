package htwg.konstantz.cloud.ex1;

import htwg.konstantz.cloud.ex1.entities.Image;
import htwg.konstantz.cloud.ex1.entities.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class ImageController {

    @Autowired
    private ImageRepository repo;

    public void saveImage() {

    }

    @GetMapping("/images")
    List<Image> all() {
        return repo.findAll();
    }

    @PostMapping("/images")
    public void saveImage(@RequestBody Image image) {
        //TODO save image in file system
        //TODO save image in static dir save path in image
        //TODO image is served  and can be used in js
        repo.save(image);
    }

    @GetMapping("/images/{id}")
    public Image findImageById(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }
}
