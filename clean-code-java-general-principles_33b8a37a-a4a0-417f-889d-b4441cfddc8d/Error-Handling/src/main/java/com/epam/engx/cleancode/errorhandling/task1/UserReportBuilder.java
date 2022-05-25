package com.epam.engx.cleancode.errorhandling.task1;

import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.Order;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.User;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.UserDao;

import java.util.List;

public class UserReportBuilder {

    private UserDao userDao;

    public Double getUserTotalOrderAmount(String userId) {

        checkUserDaoExists();

        User user = findUserById(userId);

        List<Order> orders = getValidateOrdersForUser(user);

        return getTotalAmountOfOrder(orders);
    }

    private Double getTotalAmountOfOrder(List<Order> orders) {
        Double sum = 0.0;
        for (Order order : orders) {

            if (order.isSubmitted()) {
                Double total = order.total();
                checkValidTotalAmount(total);
                sum += total;
            }
        }
        return sum;
    }

    private void checkValidTotalAmount(Double total) {
        if (total < 0) {
            throw new InvalidTotalAmountException();
        }
    }

    private List<Order> getValidateOrdersForUser(User user) {
        List<Order> orders = user.getAllOrders();

        if (orders.isEmpty()) {
            throw new OrderNotSubmittedException();
        }
        return orders;
    }

    private User findUserById(String userId) {
        User user = userDao.getUser(userId);
        if (user == null) {
            throw new UserIdNotFoundException();
        }
        return user;
    }

    private void checkUserDaoExists() {
        if (userDao == null) {
            throw new UserDaoNotExistsException();
        }
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
