package br.evoluum.robot.service;

import br.evoluum.robot.error.AppException;
import br.evoluum.robot.model.Direction;
import br.evoluum.robot.model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MarsServiceTest {
    private MarsService service;

    @BeforeEach
    public void setUp() {
        service = new MarsService();
    }

    @Test
    public void should_to_be_at_correct_position(){
        assertNotNull(service.getMarsRegion());
        assertNotNull(service.getMarsRegion().getRobot());
        assertNotNull(service.getMarsRegion().getRobot().getCurrentPosition());
        assertEquals(0, service.getMarsRegion().getRobot().getCurrentPosition().getX());
        assertEquals(0, service.getMarsRegion().getRobot().getCurrentPosition().getY());
        assertEquals(Direction.NORTH, service.getMarsRegion().getRobot().getCurrentPosition().getDirection());
    }

    @Test
    public void should_throws_exception_when_command_has_invalid_instruction() {
        String command = "AAA";

        Assertions.assertThrows(AppException.class, () -> {
            service.move(command);
        });
    }

    @Test
    public void should_throws_exeception_when_command_is_invalid(){
        String command = "MMMMMMMMMMMMMMMMMMMMMMMM";
        Assertions.assertThrows(AppException.class, () -> {
            service.move(command);
        });
    }


    @Test
    public void should_move_to_2_0_s() throws AppException {
        String command = "MMRMMRMM";

        Position currentPosition = service.move(command);
        assertNotNull(currentPosition);
        assertNotNull(currentPosition.getDirection());
        assertEquals(2, currentPosition.getX());
        assertEquals(0, currentPosition.getY());
        assertEquals(Direction.SOUTH, currentPosition.getDirection());
    }

    @Test
    public void should_move_to_0_2_w() throws AppException {
        String command = "MML";

        Position currentPosition = service.move(command);
        assertNotNull(currentPosition);
        assertNotNull(currentPosition.getDirection());
        assertEquals(0, currentPosition.getX());
        assertEquals(2, currentPosition.getY());
        assertEquals(Direction.WEST, currentPosition.getDirection());
    }
}
