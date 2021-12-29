package org.but.feec.carservice.service;

import com.zaxxer.hikari.HikariDataSource;
import org.but.feec.carservice.api.CarStandardView;
import org.but.feec.carservice.api.SuccessAndFailAlerts;
import org.but.feec.carservice.config.DataSourceConfig;
import org.but.feec.carservice.data.CarRepository;
import org.but.feec.carservice.exceptions.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static java.lang.Integer.valueOf;

public class CarEditService {

    private CarRepository carRepository;

    public CarEditService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

//    public PersonDetailView getPersonDetailView(Long id) {
//        return carRepository.findPersonDetailedView(id);
//    }

    public List<CarStandardView> getCarStandardView() {
        return carRepository.getCarsStandardViewList();
    }

    private static HikariDataSource dataSource;

    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);


}
