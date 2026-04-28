package lesson2;

import lesson2.dao.CarDAO;
import lesson2.dao.DAOFactory;
import lesson2.dao.IDAOFactory;
import lesson2.entity.Car;

import java.util.List;

public class TestLes2 {
    static void main() {
       IDAOFactory factory = DAOFactory.getInstance();
       CarDAO carDAO = factory.getCarDAO();

       Car car = new Car();
        car.setMark("BMW");
        car.setModel("X3");
        car.setPrice(3800);

        carDAO.add(car);

        List<Car> cars = carDAO.getAll();
        for (Car c : cars) {
            System.out.println(c);
        }
    }
}
