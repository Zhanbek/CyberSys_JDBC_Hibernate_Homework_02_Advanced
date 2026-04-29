package lesson2.dao;

import lesson2.entity.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarJDBCDao implements CarDAO{

    @Override
    public void add(Car car) {
        Connection connection = null;
        connection = getConnection();
        PreparedStatement preparedStatement = null;

        try {
            int markId = getMarkId(car.getMark(), connection);

            if(markId == -1){
                preparedStatement = connection.prepareStatement("insert into marks (mark) values (?)");
                preparedStatement.setString(1, car.getMark());
                preparedStatement.execute();

                preparedStatement = connection.prepareStatement("select max(id) from marks");
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                markId = resultSet.getInt(1);
                System.out.println("adding mark has been done");

            }

            preparedStatement = connection.prepareStatement("insert into cars(mark_id,model,price) values (?,?,?)");
            preparedStatement.setInt(1, markId);
            preparedStatement.setString(2, car.getModel());
            preparedStatement.setInt(3, car.getPrice());
            preparedStatement.execute();

        } catch (Exception e){
            e.printStackTrace();
        }
    }


    private int getMarkId(String markName, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select id from marks where mark = ?");
        preparedStatement.setString(1, markName);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return -1;
    }

    private String getMarkById(long id, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select mark from marks where id = ?");
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return "";
    }


    @Override
    public List<Car> getAll() {
        List<Car> cars = new ArrayList<>();

        Connection connection = null;
        connection = getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("select id, mark_id, model, price from cars");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Car car = new Car();
                car.setId(resultSet.getInt("id"));
                long mark_id = resultSet.getLong("mark_id");
                car.setMark(getMarkById(mark_id, connection));
                car.setModel(resultSet.getString("model"));
                car.setPrice(resultSet.getInt("price"));
                cars.add(car);
            }
            return cars;
        } catch (Exception e) {

        }

        return cars;
    }

    @Override
    public Car getById(long id) {
        return null;
    }

    @Override
    public void updatePrice(long id, int price) {
        Connection connection = null;
        connection = getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("update cars " +
                                                                "set price = ? " +
                                                                "where id = ?");
            preparedStatement.setInt(1, price);
            preparedStatement.setLong(2, id);
            preparedStatement.execute();
            System.out.println("A new price " + price + " has been set for car with an id " + id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String mark) {

    }

    private Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsshop?useSSL=false", "root",
                    "root");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }



}
