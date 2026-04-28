package lesson2;

import lesson2.dao.CarDAO;
import lesson2.dao.DAOFactory;
import lesson2.dao.IDAOFactory;
import lesson2.entity.Car;

public class TestLes2 {
    static void main() {
       IDAOFactory factory = DAOFactory.getInstance();
       CarDAO carDAO = factory.getCarDAO();

       Car car = new Car();
        car.setMark("BMW");
        car.setModel("X2");
        car.setPrice(1800);

        carDAO.add(car);

    }
}
