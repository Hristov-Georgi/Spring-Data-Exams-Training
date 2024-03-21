package laptopShopExercise.model.shop;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "shops")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShopsImportWrapperDTO {

    @XmlElement(name = "shop")
    private List<ShopImportDTO> shops;

    public ShopsImportWrapperDTO(){}

    public ShopsImportWrapperDTO(List<ShopImportDTO> shops){

        this.shops = shops;
    }

    public List<ShopImportDTO> getShops() {
        return shops;
    }
}

