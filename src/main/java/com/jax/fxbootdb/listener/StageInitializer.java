package com.jax.fxbootdb.listener;

import com.jax.fxbootdb.GuiApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StageInitializer implements ApplicationListener<GuiApplication.StageReadyEvent> {

    private final ApplicationContext applicationContext;

    public StageInitializer(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(GuiApplication.StageReadyEvent event) {
        try {
            Stage stage = event.getStage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/Application.fxml"));
            loader.setControllerFactory(aClass -> applicationContext.getBean(aClass));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
