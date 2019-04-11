package htwg.konstantz.cloud.ex1.controller;

import htwg.konstantz.cloud.ex1.entities.Image;
import htwg.konstantz.cloud.ex1.entities.ImageFirestore;
import htwg.konstantz.cloud.ex1.entities.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController()
public class ImageController {

    private static final String DOWNLOAD_FILE_PATH = "downloadFileById";
    @Autowired
    private ImageRepository repo;

    //TODO initialize with project id
    private ImageFirestore firestore;

    @Autowired
    private FileStorageService fileStorageService;

    public void saveImage() {

    }

    @GetMapping("/images")
    List<Image> all() {
        return firestore.getAllImages();
    }

    @PostMapping("/images")
    public Image saveImage(@RequestBody Image image) {

        return firestore.saveImage(image);
    }

    //TODO add cloud Storage
    @PostMapping("uploadImage/{id}")
    public String uploadImage(@RequestParam("file") MultipartFile file, @PathVariable long id) {
        String fileName = fileStorageService.storeFile(file);


        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/" + DOWNLOAD_FILE_PATH + "/")
                .path("" + id)
                .toUriString();

        Image image = repo.findById(id).get();
        image.setPath(fileDownloadUri);
        image.setFileName(fileName);
        ;
        repo.save(image);
        return fileDownloadUri;
    }


    @GetMapping("/images/{id}")
    public Image findImageById(@PathVariable String id) {
        return firestore.getImage(id);
    }

    //TODO remove for Firestore use url form cloud store directly
    @GetMapping("/" + DOWNLOAD_FILE_PATH + "/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id, HttpServletRequest request) {

        String fileName = repo.findById(id).get().getFileName();

        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }
}
