package br.evoluum.robot.controller;


import br.evoluum.robot.error.AppException;
import br.evoluum.robot.model.Position;
import br.evoluum.robot.service.MarsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/mars")
public class MarsRestController {

    private final MarsService service;

    @Autowired
    public MarsRestController(MarsService service) {
        this.service = service;
    }

    @GetMapping
    @ApiOperation(value = "Returns the robot's current position", response = ResponseEntity.class, httpMethod = "GET")
    public ResponseEntity currentPosition() {

        Position currentPosition = service.getMarsRegion().getRobot().getCurrentPosition();

        return ResponseEntity.ok().body(currentPosition);
    }

    @PostMapping("/{command}")
    @ApiOperation(value = "Move the robot on the mars region", response = ResponseEntity.class, httpMethod = "POST")
    public ResponseEntity move(@PathVariable(name = "command") String command) throws AppException {

        Position currentPosition = service.move(command);

        return ResponseEntity.ok().body(currentPosition);
    }
}
