package br.evoluum.robot.controller;

import br.evoluum.robot.error.AppException;
import br.evoluum.robot.model.Direction;
import br.evoluum.robot.model.MarsRegion;
import br.evoluum.robot.model.Position;
import br.evoluum.robot.model.Robot;
import br.evoluum.robot.service.MarsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(value = MarsRestController.class, secure = false)
public class MarsRestControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private MarsService service;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void currentPosition() throws Exception {

        when(service.getMarsRegion())
            .thenReturn(MarsRegion.builder()
                .robot(Robot.builder()
                    .currentPosition(
                        Position.builder()
                            .x(0)
                            .y(0)
                            .direction(Direction.NORTH)
                            .build()
                    )
                    .build())
                .build());

        mvc.perform(get("/rest/mars/")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service, times(1)).getMarsRegion();
    }

    @Test
    public void shoud_throws_exception_when_no_rotate() throws Exception {
        String command = "MMM";
        when(service.move(command)).thenThrow(AppException.class);

        mvc.perform(post("/rest/mars/" + command)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(service, times(1)).move(anyString());
    }

    @Test
    public void shoud_throws_exception_when_given_a_invalid_command() throws Exception {
        String command = "AAA";
        when(service.move(command)).thenThrow(AppException.class);

        mvc.perform(post("/rest/mars/" + command)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(service, times(1)).move(anyString());
    }

    @Test
    public void shoud_throws_exception_when_given_a_too_longer_command() throws Exception {
        String command = "MMMMMMMMMMMMMMMMMMMMMMMM";
        when(service.move(command)).thenThrow(AppException.class);

        mvc.perform(post("/rest/mars/" + command)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(service, times(1)).move(anyString());
    }

}
