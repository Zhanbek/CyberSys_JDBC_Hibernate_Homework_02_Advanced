package lesson2;

import lesson2.dao.CarDAO;
import lesson2.dao.DAOFactory;
import lesson2.dao.IDAOFactory;
import lesson2.entity.Car;

import java.util.List;

public class TestLes2 {
    private static void outputAll(CarDAO dao) {
        List<Car> cars = dao.getAll();
        for (Car c : cars) {
            System.out.println(c);
        }
    }

    static void main() {
       IDAOFactory factory = DAOFactory.getInstance();
       CarDAO carDAO = factory.getCarDAO();

       Car car = new Car();
        car.setMark("BMW");
        car.setModel("X6");
        car.setPrice(15800);

        carDAO.add(car);

        System.out.println("Car added successfully");
        System.out.println();
        outputAll(carDAO);

        System.out.println();
        carDAO.updatePrice(2, 2333);
        System.out.println();
        outputAll(carDAO);

        System.out.println();
        carDAO.delete("BMW");
        System.out.println();
        outputAll(carDAO);

    }
}
