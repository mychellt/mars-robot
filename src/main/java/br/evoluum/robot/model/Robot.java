package br.evoluum.robot.model;

import lombok.*;
import org.springframework.util.StringUtils;

import static br.evoluum.robot.model.Direction.*;

/**
 * @author Mychell Teixeira (mychellt@gmail.com)
 * @since 22/09/2020
 */

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Robot {
    private Position currentPosition;

    public Position rotate(String rotate) {
        switch (currentPosition.getDirection()){
            case NORTH:
                currentPosition.setDirection(isRight(rotate) ? EAST: WEST);
                break;
            case EAST:
                currentPosition.setDirection(isRight(rotate) ? SOUTH: NORTH);
                break;
            case WEST:
                currentPosition.setDirection(isRight(rotate) ? NORTH: SOUTH);
                break;
            case SOUTH:
                currentPosition.setDirection(isRight(rotate) ? WEST: EAST);
                break;
            default:
                break;
        }
        return currentPosition;
    }

    private boolean isRight(String rotate) {
        return !StringUtils.isEmpty(rotate) && rotate.equalsIgnoreCase("R");
    }
}
