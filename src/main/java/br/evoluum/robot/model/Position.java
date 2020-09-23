package br.evoluum.robot.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Position {
    private int x;
    private int y;
    private Direction direction;
}
