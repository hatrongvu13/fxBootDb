package com.jax.fxbootdb.controllers;

import com.jax.fxbootdb.data.entities.Users;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class ApplicationController implements Initializable {

    @FXML
    private Label alertMessage;

    @FXML
    private Button btnConnect;

    @FXML
    private TextField hostInfo;

    @FXML
    private TextField dbInfo;

    @FXML
    private TextField passwordInfo;

    @FXML
    private TextField portInfo;

    @FXML
    private TextField usernameInfo;

    @FXML
    void connectDb(MouseEvent event) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(configDatasource());
            List<Users> usersList = jdbcTemplate.query("SELECT * FROM users", new BeanPropertyRowMapper<>(Users.class));
            alertMessage.setText("Susscess connect");
            System.out.println(usersList.get(0));
        } catch (Exception e) {
            alertMessage.setText("Error connect");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public DataSource configDatasource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:postgresql://"+hostInfo.getText()+":"+portInfo.getText()+"/"+dbInfo.getText());
        dataSource.setUsername(usernameInfo.getText());
        dataSource.setPassword(passwordInfo.getText());
        return dataSource;
    }
}
