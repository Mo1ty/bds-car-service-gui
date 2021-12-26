package org.but.feec.carservice;

import org.but.feec.carservice.config.DataSourceConfig;

public class Main {
    public static void main(String[] args) {
        DataSourceConfig.initializeDataSource();
        App.main(args);
    }
}