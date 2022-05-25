package com.epam.engx.cleancode.naming.task4.service.impl;


import com.epam.engx.cleancode.naming.task4.service.CustomerContactService;
import com.epam.engx.cleancode.naming.task4.thirdpartyjar.CustomerContact;
import com.epam.engx.cleancode.naming.task4.thirdpartyjar.CustomerContactDAO;

public class CustomerContactServiceImpl implements CustomerContactService {

    private CustomerContactDAO customerContactDao;

    @Override
    public CustomerContact findById(Long customerId) {
        return customerContactDao.findById(customerId);
    }

    @Override
    public void update(CustomerContact customerContact) {
        customerContactDao.update(customerContact);
    }
}
