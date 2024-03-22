package laptopShopExercise.model;

import jakarta.persistence.*;
import laptopShopExercise.model.shop.Shop;


import java.math.BigDecimal;

@Entity
@Table(name = "laptops")
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "mac_address", nullable = false)
    private String macAddress;

    @Column(name = "cpu_speed", nullable = false)
    private float cpuSpeed;

    @Column(nullable = false)
    private int ram;

    @Column(nullable = false)
    private int storage;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private WarrantyType warrantyType;

    @ManyToOne
    private Shop shop;

    public Laptop() {
    }

    public Laptop(String macAddress, float cpuSpeed, int ram, int storage,
                  String description, BigDecimal price, WarrantyType warrantyType) {
        this.macAddress = macAddress;
        this.cpuSpeed = cpuSpeed;
        this.ram = ram;
        this.storage = storage;
        this.description = description;
        this.price = price;
        this.warrantyType = warrantyType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    @Override
    public String toString() {
        return String.format("""
                Laptop - %s
                *Cpu speed - %.2f
                **Ram - %d
                ***Storage - %d
                ****Price - %.2f
                #Shop name - %s
                ##Town - %s""",
                this.macAddress,
                this.cpuSpeed,
                this.ram,
                this.storage,
                this.price,
                this.shop.getName(),
                this.shop.getTown().getName());
    }
}
