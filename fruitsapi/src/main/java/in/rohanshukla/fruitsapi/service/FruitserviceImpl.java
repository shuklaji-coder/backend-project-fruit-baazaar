package in.rohanshukla.fruitsapi.service;

import in.rohanshukla.fruitsapi.entity.FruitEntity;
import in.rohanshukla.fruitsapi.fruitrepo.fruitrepository2;
import in.rohanshukla.fruitsapi.request.Fruitrequest;
import in.rohanshukla.fruitsapi.request.Fruitresponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FruitserviceImpl implements Fruitservice {

    private final S3Client s3Client;
    private final fruitrepository2 repository;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    // -------------------------- UPLOAD FILE -------------------------------- //
    @Override
    public String uploadFile(MultipartFile file) {
        String filenameExtension = file.getOriginalFilename()
                .substring(file.getOriginalFilename().lastIndexOf(".") + 1);

        String key = UUID.randomUUID().toString() + "." + filenameExtension;

        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .acl("public-read")
                    .contentType(file.getContentType())
                    .build();

            PutObjectResponse response = s3Client.putObject(
                    putObjectRequest,
                    RequestBody.fromBytes(file.getBytes())
            );

            if (response.sdkHttpResponse().isSuccessful()) {
                return "https://" + bucketName + ".s3.amazonaws.com/" + key;
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "File upload failed");
            }

        } catch (IOException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while uploading file");
        }
    }


    @Override
    public Fruitresponse addFruit(Fruitrequest request, MultipartFile file) {
        FruitEntity newFruitEntity = convertToEntity(request);

        String imageUrl = uploadFile(file);
        newFruitEntity.setImageUrl(imageUrl);

        newFruitEntity = repository.save(newFruitEntity);

        return convertToResponse(newFruitEntity);
    }


    @Override
    public List<Fruitresponse> readfruit() {
        return repository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }


    @Override
    public Fruitresponse readFruitById(String id) {
        FruitEntity existingFruit = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fruit not Found"));

        return convertToResponse(existingFruit);
    }


    @Override
    public boolean deleteFile(String filename) {
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(filename)
                .build();

        s3Client.deleteObject(deleteObjectRequest);
        return true;
    }


    @Override
    public void deleteFruit(String id) {

        // 1. Get fruit
        Fruitresponse response = readFruitById(id);

        // 2. Get image URL
        String imageUrl = response.getImageUrl();

        // 3. Extract S3 key
        String key = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);

        // 4. Delete image from S3
        deleteFile(key);

        // 5. Delete record from DB
        repository.deleteById(id);
    }


    // -------------------------- CONVERTERS -------------------------------- //
    public FruitEntity convertToEntity(Fruitrequest request) {
        return FruitEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .category(request.getCategory())
                .pricePerKg(request.getPricePerKg().intValue())
                .build();
    }

    private Fruitresponse convertToResponse(FruitEntity entity) {
        return Fruitresponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .category(entity.getCategory())
                .imageUrl(entity.getImageUrl())
                .pricePerKg(entity.getPricePerKg()) // ✅ FIXED
                .build();
    }


}
