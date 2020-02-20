package awesome.data.structure.xml.jaxb;

import java.util.ArrayList;
import java.util.List;

public class XMLTest {
    public static void getCarAsXml() {
        String registration = "abc123";
        String brand = "Volvo";
        String description = "Sedan";
        Car.CarExt carExt = new Car.CarExt("黎青", "123", "TTT", 100l);
        List<Car.CarExt> carExtList = new ArrayList<>();
        List<Car.CarExt> cars = new ArrayList<>();
        carExtList.add(carExt);
        cars.add(carExt);
        cars.add(carExt);

        Car car = new Car(registration, brand, description, carExt, carExtList, cars);
        XmlUtil xmlUtil = new XmlUtil();
        String xml = xmlUtil.convertToXml(car, car.getClass());

        String xpathExpression = "/car/@registration";
        String actual = xmlUtil.extractValue(xml, xpathExpression);

        xpathExpression = "/car/brand";
        actual = xmlUtil.extractValue(xml, xpathExpression);

        xpathExpression = "/car/description";
        actual = xmlUtil.extractValue(xml, xpathExpression);
    }

    public static void main(String[] args) {
        getCarAsXml();
    }
}
