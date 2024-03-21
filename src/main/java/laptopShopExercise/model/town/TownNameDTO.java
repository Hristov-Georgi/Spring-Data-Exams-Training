package laptopShopExercise.model.town;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class TownNameDTO {

    @XmlElement(name = "name")
    private String name;

    public String getName() {
        return this.name;
    }
}


