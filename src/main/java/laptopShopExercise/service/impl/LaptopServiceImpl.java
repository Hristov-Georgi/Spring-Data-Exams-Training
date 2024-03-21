package laptopShopExercise.service.impl;

import laptopShopExercise.repository.LaptopRepository;
import laptopShopExercise.service.LaptopService;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LaptopServiceImpl implements LaptopService {

    private final LaptopRepository laptopRepository;

    public LaptopServiceImpl (LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }


    @Override
    public boolean areImported() {
        return false;
    }

    @Override
    public String readLaptopsFileContent() throws IOException {
        return null;
    }

    @Override
    public String importLaptops() throws IOException {
        return null;
    }

    @Override
    public String exportBestLaptops() {
        return null;
    }
}
