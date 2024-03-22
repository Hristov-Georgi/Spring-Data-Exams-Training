package laptopShopExercise.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import laptopShopExercise.model.Laptop;
import laptopShopExercise.model.LaptopImportDTO;
import laptopShopExercise.model.WarrantyType;
import laptopShopExercise.model.shop.Shop;
import laptopShopExercise.repository.LaptopRepository;
import laptopShopExercise.repository.ShopRepository;
import laptopShopExercise.service.LaptopService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LaptopServiceImpl implements LaptopService {
    private static final String JSON_IMPORT_FILE_PATH = "src\\main\\resources\\files\\json\\laptops.json";

    private final LaptopRepository laptopRepository;
    private final ShopRepository shopRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final Validator validator;

    public LaptopServiceImpl (LaptopRepository laptopRepository, ShopRepository shopRepository) {
        this.laptopRepository = laptopRepository;
        this.shopRepository = shopRepository;
        this.modelMapper = new ModelMapper();
        this.gson = new GsonBuilder().create();
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }


    @Override
    public boolean areImported() {
        return this.laptopRepository.count() > 0;
    }

    @Override
    public String readLaptopsFileContent() throws IOException {
        Path path = Path.of(JSON_IMPORT_FILE_PATH);

        return Files.readString(path);
    }

    @Override
    public String importLaptops() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(JSON_IMPORT_FILE_PATH));

        LaptopImportDTO[] laptops = this.gson.fromJson(bufferedReader, LaptopImportDTO[].class);


        return Arrays.stream(laptops)
                .map(this::importSingleLaptop)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public String exportBestLaptops() {
        List<Laptop> laptopList = this.laptopRepository.findAll();

        return laptopList.stream()
                .map(Laptop::toString)
                .collect(Collectors.joining("\n\n"));

    }

    private String importSingleLaptop(LaptopImportDTO laptopDTO) {
        Set<ConstraintViolation<LaptopImportDTO>> errorMessages = this.validator.validate(laptopDTO);

        if(errorMessages.isEmpty()) {
            Optional<Laptop> l = this.laptopRepository.findByMacAddress(laptopDTO.getMacAddress());

            if(l.isPresent()) {
                return "Invalid Laptop";
            }

            Optional<Shop> shop = this.shopRepository.findByName(laptopDTO.getShop().getName());

            Laptop laptop = this.modelMapper.map(laptopDTO, Laptop.class);

            if(laptop.getWarrantyType() == null) {
                return "Invalid Laptop";
            }

            laptop.setShop(shop.get());

            this.laptopRepository.save(laptop);

            return String.format("Successfully imported Laptop %s - %.2f - %d -%d",
                    laptop.getMacAddress(),
                    laptop.getCpuSpeed(),
                    laptop.getRam(),
                    laptop.getStorage());

        } else {
            return "Invalid Laptop";
        }
    }


}
