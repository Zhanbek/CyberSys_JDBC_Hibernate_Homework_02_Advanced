package lesson2.dao;

import lesson2.entity.Car;

import java.sql.*;
import java.util.List;

public class CarJDBCDao implements CarDAO{

    @Override
    public void add(Car car) {
        Connection connection = null;
        connection = getConnection();
        PreparedStatement preparedStatement = null;

        try{
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



        }catch (Exception e){
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







    @Override
    public List<Car> getAll() {
        return List.of();
    }

    @Override
    public Car getById(long id) {
        return null;
    }

    @Override
    public void updatePrice(long id, int price) {

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
