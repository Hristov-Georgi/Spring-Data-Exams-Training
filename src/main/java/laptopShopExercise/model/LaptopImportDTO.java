package laptopShopExercise.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import laptopShopExercise.model.shop.ShopNameDTO;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

public class LaptopImportDTO {

    @Length(min = 9)
    private String macAddress;

    @Positive
    private float cpuSpeed;

    @Min(8)
    @Max(128)
    private int ram;

    @Min(128)
    @Max(1024)
    private int storage;

    @Length(min = 10)
    private String description;

    @Positive
    private BigDecimal price;

    private WarrantyType warrantyType;

    private ShopNameDTO shop;

    public LaptopImportDTO() {
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public float getCpuSpeed() {
        return cpuSpeed;
    }

    public void setCpuSpeed(float cpuSpeed) {
        this.cpuSpeed = cpuSpeed;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public WarrantyType getWarrantyType() {
        return warrantyType;
    }

    public void setWarrantyType(WarrantyType warrantyType) {
        this.warrantyType = warrantyType;
    }

    public ShopNameDTO getShop() {
        return shop;
    }

    public void setShop(ShopNameDTO shop) {
        this.shop = shop;
    }
}
