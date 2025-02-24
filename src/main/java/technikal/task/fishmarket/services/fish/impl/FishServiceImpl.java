package technikal.task.fishmarket.services.fish.impl;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import technikal.task.fishmarket.dto.fish.FishDto;
import technikal.task.fishmarket.persistence.entity.Fish;
import technikal.task.fishmarket.persistence.repository.FishRepository;
import technikal.task.fishmarket.services.fish.FishService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FishServiceImpl implements FishService {

  private final FishRepository fishRepository;

  @Override
  @Transactional(readOnly = true)
  public List<Fish> get(@Nonnull final Sort sort) {
    return fishRepository.findAll(sort);
  }

  @Override
  @Transactional
  public Fish create(@Nonnull final FishDto fishDto) {
    final var fishImages = fishDto.getImages();
    final var fishCatchDate = new Date();

    if (fishImages.isEmpty()) {
      throw new RuntimeException("Fish cannot be without images!");
    }

    final var fishImagesStorageList = new ArrayList<String>();

    try {
      final var uploadDir = "public/images/";
      final var uploadPath = Paths.get(uploadDir);

      if (!Files.exists(uploadPath)) {
        Files.createDirectories(uploadPath);
      }

      for (final var image : fishImages) {
        try (var inputStream = image.getInputStream()){
          final var storageFileName = fishCatchDate.getTime() + "_" + image.getOriginalFilename();
          Files.copy(inputStream, Paths.get(uploadDir+storageFileName), StandardCopyOption.REPLACE_EXISTING);
          fishImagesStorageList.add(storageFileName);
        }
      }

    } catch (final IOException ex) {
      System.out.println("Exception: " + ex.getMessage());
    }

    final var fish = Fish.builder()
        .catchDate(fishCatchDate)
        .images(fishImagesStorageList)
        .name(fishDto.getName())
        .price(fishDto.getPrice())
        .build();

    return fishRepository.save(fish);
  }

  @Override
  @Transactional
  public void delete(@Nonnull final Integer id) {
    try {
      final var fish = fishRepository.findById(id)
          .orElseThrow(() -> new RuntimeException("Fish with id: " + id + " not found!"));

      for (var image : fish.getImages()) {
        final var imagePath = Paths.get("public/images/" + image);
        Files.delete(imagePath);
      }

      fishRepository.delete(fish);

    } catch(Exception ex) {
      System.out.println("Exception: " + ex.getMessage());
    }
  }
}
