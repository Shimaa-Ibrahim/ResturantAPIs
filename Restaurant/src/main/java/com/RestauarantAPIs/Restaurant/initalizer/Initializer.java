package com.RestauarantAPIs.Restaurant.initalizer;

import com.RestauarantAPIs.Restaurant.entities.Tables;
import com.RestauarantAPIs.Restaurant.entities.User;
import com.RestauarantAPIs.Restaurant.entities.UserRole;
import com.RestauarantAPIs.Restaurant.services.TablesService;
import com.RestauarantAPIs.Restaurant.services.UserService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


/*
 * to provide admin user and regular user and number of tables
 *  when run the app for the first time.
 * admin username = admin@admin.com, password = admin
 * user username = user@user.com, password = user
 * */

@Component
public class Initializer implements CommandLineRunner {

    private final Log logger = LogFactory.getLog(Initializer.class);

    @Autowired
    private UserService userService;

    @Autowired
    private TablesService tablesService;

    @Override
    public void run(String... args) throws Exception {

        logger.info("RESTAURANT API");

        if (!userService.findAll().iterator().hasNext()) {
            logger.info("No Users accounts found. Creating Admin user");
            User admin = new User("admin", "admin", "admin@admin.com", "01111111111", UserRole.ADMIN);
            userService.signUpUser(admin);
            User user = new User("user", "user", "user@user.com", "01111111111", UserRole.USER);
            userService.signUpUser(user);
            logger.info("Admin created with following data: name: admin, email: admin@admin.com, password: admin");
        }

        if (!tablesService.findAll().iterator().hasNext()) {
            ArrayList<Tables> restaurantTables = new ArrayList<Tables>();

            logger.info("No Tables found. Creating restaurant table");
            for (Integer i = 1; i <= 4; i++) {
                Tables table = new Tables(i.toString(), 2);
                restaurantTables.add(table);
            }
            for (Integer i = 5; i < 12; i++) {
                Tables table = new Tables(i.toString(), 5);
                restaurantTables.add(table);
            }
            for (Integer i = 13; i < 15; i++) {
                Tables table = new Tables(i.toString(), 10);
                restaurantTables.add(table);
            }
            for (Tables table : restaurantTables) {
                this.tablesService.addTable(table);
            }
            logger.info("tables added");

        }

    }
}
