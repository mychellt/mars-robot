package br.evoluum.robot.service;

import br.evoluum.robot.error.AppException;
import br.evoluum.robot.model.Direction;
import br.evoluum.robot.model.MarsRegion;
import br.evoluum.robot.model.Position;
import br.evoluum.robot.model.Robot;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;


/**
 * @author Mychell Teixeira (mychellt@gmail.com)
 * @since 22/09/2020
 */
@Service
public class MarsService {
    @Getter @Setter
    private MarsRegion marsRegion;

    public MarsService() {
        marsRegion = MarsRegion.builder()
            .height(5)
            .width(5)
            .robot(Robot.builder()
                .currentPosition(Position.builder()
                    .direction(Direction.NORTH)
                    .x(0)
                    .y(0)
                    .build())
                .build())
            .build();
    }

    public Position move(String command) throws AppException {

        validate(command);
        Robot robot = marsRegion.getRobot();
        for(String letter : command.split("")){
            if(letter.equalsIgnoreCase("M")) {
                if(!isAllowedMovement()){
                    throw new AppException("Invalid Movement");
                }
                if(robot.getCurrentPosition().getDirection().isNorth() ||
                   robot.getCurrentPosition().getDirection().isSouth()) {
                    robot.getCurrentPosition().setY(
                            robot.getCurrentPosition().getDirection().isNorth() ?
                            robot.getCurrentPosition().getY()+1 :
                            robot.getCurrentPosition().getY()-1 );
                }
                else {
                    robot.getCurrentPosition().setX(
                            robot.getCurrentPosition().getDirection().isEast() ?
                            robot.getCurrentPosition().getX()+1 :
                            robot.getCurrentPosition().getX()-1);
                }
            }
            else {
                robot.rotate(letter);
            }
        }

        return marsRegion.getRobot().getCurrentPosition();
    }

    private boolean isAllowedMovement() {
        Robot robot = marsRegion.getRobot();
        if((robot.getCurrentPosition().getX() == 5 && ( robot.getCurrentPosition().getDirection().isEast())) ||
            (robot.getCurrentPosition().getX() == 0 && ( robot.getCurrentPosition().getDirection().isWest())) ) {
            return false;
        }
        return (robot.getCurrentPosition().getY() != 5 || (!robot.getCurrentPosition().getDirection().isNorth())) &&
                (robot.getCurrentPosition().getY() != 0 || (!robot.getCurrentPosition().getDirection().isSouth()));
    }

    private void validate(String command) throws AppException {
        if(StringUtils.isEmpty(command)) {
            throw new AppException("Command can't be empty.");
        }
        for(String letter : command.split("")){
            if(!letter.equalsIgnoreCase("M") &&
               !letter.equalsIgnoreCase("R") &&
                !letter.equalsIgnoreCase("L")) {
                throw new AppException("Invalid command!");
            }
        }

        if(Arrays.stream(command.split(""))
                .noneMatch(s -> s.equalsIgnoreCase("L")) &&
            Arrays.stream(command.split(""))
                        .noneMatch(s -> s.equalsIgnoreCase("R"))
        ){
            throw new AppException("Invalid command!");
        }
    }
}
