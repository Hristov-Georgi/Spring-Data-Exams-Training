package laptopShopExercise.model.shop;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.xml.bind.annotation.*;
import laptopShopExercise.model.town.TownNameDTO;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@XmlRootElement(name = "shop")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShopImportDTO {

    @XmlElement
    @Length(min = 4)
    private String address;

    @XmlElement(name = "employee-count")
    @Min(1)
    @Max(50)
    private int employeeCount;

    @XmlElement
    @Min(20000)
    private BigDecimal income;

    @XmlElement(name = "name")
    @Length(min = 4)
    private String name;

    @XmlElement(name = "shop-area")
    @Min(150)
    private double shopArea;

    @XmlElement(name = "town")
    private TownNameDTO town;

    public ShopImportDTO(){}

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShopArea(double shopArea) {
        this.shopArea = shopArea;
    }

    public String getAddress() {
        return address;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public String getName() {
        return name;
    }

    public double getShopArea() {
        return shopArea;
    }

    public TownNameDTO getTown() {
        return town;
    }

    public void setTown(TownNameDTO town) {
        this.town = town;
    }
}
