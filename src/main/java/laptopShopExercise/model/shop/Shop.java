package laptopShopExercise.model.shop;

import jakarta.persistence.*;
import laptopShopExercise.model.town.Town;


import java.math.BigDecimal;

@Entity
@Table(name = "shops")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, name = "names", nullable = false)
    private String name;

    @Column(name = "incomes", nullable = false)
    private BigDecimal income;

    @Column
    private String address;

    @Column(name = "employee_count", nullable = false)
    private int employeeCount;

    @Column(name = "shop_area", nullable = false)
    private double shopArea;

    @ManyToOne
    private Town town;

    public Shop(){}

    public Shop(String name, BigDecimal income, String address, int employeeCount, double shopArea) {
        this.name = name;
        this.income = income;
        this.address = address;
        this.employeeCount = employeeCount;
        this.shopArea = shopArea;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    public double getShopArea() {
        return shopArea;
    }

    public void setShopArea(double shopArea) {
        this.shopArea = shopArea;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}
