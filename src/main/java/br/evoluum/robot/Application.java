package br.evoluum.robot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Mychell Teixeira (mychellt@gmail.com)
 * @since 22/09/2020
 */


@SpringBootApplication
@ComponentScan(basePackages = {
        "br.evoluum.robot.controller",
        "br.evoluum.robot.service",
        "br.evoluum.robot.configuration"
})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
