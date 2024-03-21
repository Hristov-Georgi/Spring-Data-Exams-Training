package laptopShopExercise.model.town;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import laptopShopExercise.model.town.TownImportDTO;

import java.util.List;

@XmlRootElement(name = "towns")
@XmlAccessorType(XmlAccessType.FIELD)
public class TownsImportWrapperDTO {

    @XmlElement(name = "town")
    private List<TownImportDTO> towns;

    public TownsImportWrapperDTO(){}

    public TownsImportWrapperDTO(List<TownImportDTO> towns){
        this.towns = towns;
    }

    public List<TownImportDTO> getTowns() {
        return towns;
    }
}
