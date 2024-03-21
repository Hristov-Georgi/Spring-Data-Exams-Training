package laptopShopExercise.service.impl;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import laptopShopExercise.model.shop.Shop;
import laptopShopExercise.model.shop.ShopImportDTO;
import laptopShopExercise.model.shop.ShopsImportWrapperDTO;
import laptopShopExercise.model.town.Town;
import laptopShopExercise.repository.ShopRepository;
import laptopShopExercise.repository.TownRepository;
import laptopShopExercise.service.ShopService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ShopServiceImpl implements ShopService {
    private static final String XML_SHOPS_IMPORT_FILE_PATH = "src\\main\\resources\\files\\xml\\shops.xml";

    private final ShopRepository shopRepository;
    private final TownRepository townRepository;
    private final Validator validator;
    private final ModelMapper modelMapper;

    @Autowired
    public ShopServiceImpl(ShopRepository shopRepository, TownRepository townRepository) {
        this.shopRepository = shopRepository;
        this.townRepository = townRepository;
        this.modelMapper = new ModelMapper();
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }


    @Override
    public boolean areImported() {
        return this.shopRepository.count() > 0;
    }

    @Override
    public String readShopsFileContent() throws IOException {
        Path path = Path.of(XML_SHOPS_IMPORT_FILE_PATH);

        return Files.readString(path);
    }

    @Override
    public String importShops() throws JAXBException, FileNotFoundException {
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(XML_SHOPS_IMPORT_FILE_PATH));

        JAXBContext jaxbContext = JAXBContext.newInstance(ShopsImportWrapperDTO.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        ShopsImportWrapperDTO shopDTOs = (ShopsImportWrapperDTO) unmarshaller.unmarshal(bufferedReader);

        return shopDTOs.getShops()
                .stream()
                .map(this::importSingleShop)
                .collect(Collectors.joining("\n"));
    }

    private String importSingleShop(ShopImportDTO shopDTO) {

        Set<ConstraintViolation<ShopImportDTO>> violationSet = this.validator.validate(shopDTO);

        if(violationSet.isEmpty()) {
            Shop shop = this.modelMapper.map(shopDTO, Shop.class);

            Optional<Shop> isShopExist = this.shopRepository.findByName(shop.getName());

            if(isShopExist.isPresent()) {
                return "Invalid Shop";

            } else {
                Optional<Town> town = this.townRepository.findByName(shop.getTown().getName());

                shop.setTown(town.get());
                this.shopRepository.save(shop);

                return "Successfully imported Shop " + shop.getName() + '-' + shop.getIncome();
            }

        } else {
            return "Invalid Shop";
        }
    }


}
