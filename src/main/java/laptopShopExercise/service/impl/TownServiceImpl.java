package laptopShopExercise.service.impl;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import laptopShopExercise.model.town.Town;
import laptopShopExercise.model.town.TownImportDTO;
import laptopShopExercise.model.town.TownsImportWrapperDTO;
import laptopShopExercise.repository.TownRepository;
import laptopShopExercise.service.TownService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class TownServiceImpl implements TownService {

    private static final String XML_IMPORT_FILE_PATH = "src\\main\\resources\\files\\xml\\towns.xml";

    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;

    @Autowired
    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;
        this.modelMapper = new ModelMapper();
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        Path path = Path.of(XML_IMPORT_FILE_PATH);

        return Files.readString(path);
    }

    @Override
    public String importTowns() throws JAXBException, FileNotFoundException {
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(XML_IMPORT_FILE_PATH));

        JAXBContext jaxbContext = JAXBContext.newInstance(TownsImportWrapperDTO.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        TownsImportWrapperDTO towns = (TownsImportWrapperDTO) unmarshaller.unmarshal(bufferedReader);

        List<String> importResult = new ArrayList<>();

        for (TownImportDTO t : towns.getTowns()) {

            Set<ConstraintViolation<TownImportDTO>> incorrectTown = this.validator.validate(t);

            if(incorrectTown.isEmpty()) {
                Town town = this.modelMapper.map(t, Town.class);
                this.townRepository.save(town);

                importResult.add("Successfully imported Town " + town.getName());

            } else {
                importResult.add("Invalid Town");
            }
        }

        return String.join("\n", importResult);
    }
}
