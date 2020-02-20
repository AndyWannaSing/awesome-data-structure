package awesome.data.structure.xml.jaxb;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement
@XmlType(propOrder = {"brand", "description", "carExt", "extList"})
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    private String registration;

    private String brand;

    private String description;

    private CarExt carExt;

    private List<CarExt> extList;

    @XmlAttribute(name = "new_registration")
    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    @XmlElement(name = "new_brand")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @XmlElement
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement(name = "car_ext")
    public CarExt getCarExt() {
        return carExt;
    }

    public void setCarExt(CarExt carExt) {
        this.carExt = carExt;
    }

    @XmlElementWrapper(name="extList")
    @XmlElements(@XmlElement(name = "car_ext", type = CarExt.class))
    public List<CarExt> getExtList() {
        return extList;
    }

    public void setExtList(List<CarExt> extList) {
        this.extList = extList;
    }

    @XmlRootElement
    @XmlType(propOrder = {"carNo","mileage"})
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CarExt {

        private String owner;
        private String mobile;
        private String carNo;
        private Long mileage;

        @XmlAttribute(name = "new_owner")
        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        @XmlAttribute
        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        @XmlElement
        public String getCarNo() {
            return carNo;
        }

        public void setCarNo(String carNo) {
            this.carNo = carNo;
        }

        @XmlElement
        public Long getMileage() {
            return mileage;
        }

        public void setMileage(Long mileage) {
            this.mileage = mileage;
        }
    }
}
