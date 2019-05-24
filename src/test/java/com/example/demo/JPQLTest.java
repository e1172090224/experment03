package com.example.demo;

import com.example.demo.entity.Address;
import com.example.demo.entity.User;
import com.example.demo.entity.UserAddress;
import com.example.demo.reposityory.AddressRepository;
import com.example.demo.reposityory.UserAddressRepository;
import com.example.demo.reposityory.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class JPQLTest {
    @Autowired
    private UserRepository userReposityory;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserAddressRepository userAddressRepository;

    @Test
    public void init() {
        User user = new User("de");
        userReposityory.save(user);
        User user2 = new User("fang");
        userReposityory.save(user2);

        Address a = new Address("5");
        addressRepository.save(a);
        Address a2 = new Address("6");
        addressRepository.save(a2);

        Address a3 = new Address("10");
        addressRepository.save(a3);

        UserAddress ua = new UserAddress(user, a);
        userAddressRepository.save(ua);

        UserAddress ua2 = new UserAddress(user, a2);
        userAddressRepository.save(ua2);

        UserAddress ua3 = new UserAddress(user2, a3);
        userAddressRepository.save(ua3);
    }

    @Test
    public void userRepTest() {
        User u = userReposityory.find(1);
        log.debug(u.getName());
    }

    @Test
    public void addressRepTest() {
        addressRepository.list("5")
                .forEach(a -> {
                    log.debug("{}", a.getId());
                });
    }

    @Test
    public void userAddressRepTest() {
        UserAddress ua = userAddressRepository.find("fang", "10");
        log.debug("插入时间：{}", ua.getInsertTime());
    }
}
