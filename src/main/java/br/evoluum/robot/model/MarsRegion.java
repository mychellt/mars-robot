package br.evoluum.robot.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class MarsRegion {
    private int width;
    private int height;
    private Robot robot;
}