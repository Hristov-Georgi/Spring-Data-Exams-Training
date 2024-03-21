package laptopShopExercise.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import laptopShopExercise.model.Customer;
import laptopShopExercise.model.CustomerImportDTO;
import laptopShopExercise.model.town.Town;
import laptopShopExercise.repository.CustomerRepository;
import laptopShopExercise.repository.TownRepository;
import laptopShopExercise.service.CustomerService;
import laptopShopExercise.util.LocalDateTypeAdapter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private static final String JSON_IMPORT_FILE_PATH = "src\\main\\resources\\files\\json\\customers.json";

    private final CustomerRepository customerRepository;
    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final Validator validator;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, TownRepository townRepository) {
        this.customerRepository = customerRepository;
        this.townRepository = townRepository;
        this.modelMapper = new ModelMapper();
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public boolean areImported() {
        return this.customerRepository.count() > 0;
    }

    @Override
    public String readCustomersFileContent() throws IOException {
        return Files.readString(Path.of(JSON_IMPORT_FILE_PATH));
    }

    @Override
    public String importCustomers() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(JSON_IMPORT_FILE_PATH));

        CustomerImportDTO[] customers = this.gson.fromJson(bufferedReader, CustomerImportDTO[].class);

        return Arrays.stream(customers)
                .map(this::importSingleCustomer)
                .collect(Collectors.joining("\n"));
    }

    private String importSingleCustomer(CustomerImportDTO c) {

        Set<ConstraintViolation<CustomerImportDTO>> validate = this.validator.validate(c);

        if(validate.isEmpty()) {
            Optional<Customer> isPresent = this.customerRepository.findByEmail(c.getEmail());

            if(isPresent.isPresent()) {
                return "Invalid Customer";
            } else {
                Optional<Town> town = this.townRepository.findByName(c.getTown().getName());

                Customer customer = this.modelMapper.map(c, Customer.class);

                customer.setTown(town.get());

                this.customerRepository.save(customer);

                return "Successfully imported Customer " +
                        c.getFirstName() +
                        '-' +
                        c.getLastName() +
                        '-' +
                        c.getEmail();
            }

        } else {
            return "Invalid Customer";
        }
    }
}
