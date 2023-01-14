package com.company.hotelaria.hotel.tests.guestTests;

import com.company.hotelaria.hotel.builders.GuestBuilder;
import com.company.hotelaria.hotel.controller.GuestController;
import com.company.hotelaria.hotel.core.dto.guest.GuestFullResponse;
import com.company.hotelaria.hotel.core.dto.guest.GuestRequest;
import com.company.hotelaria.hotel.core.dto.guest.GuestResponse;
import com.company.hotelaria.hotel.service.GuestService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class GuestControllerTests {

    GuestBuilder guestBuilder = new GuestBuilder();

    @Test
    public void testeDeveRetornarOClienteBuscadoPorCpf() {

        GuestService guestService = mock(GuestService.class);
        GuestController guestController = new GuestController(guestService);

        when(guestService.findBySocialSecurityNumber(anyString())).thenReturn(guestBuilder.novoGuestFullResponse());
        ResponseEntity<GuestFullResponse> guestResponse = guestController.findBySocialSecurityNumber(String.valueOf(1));

        Assertions.assertEquals(guestResponse.getBody().getId(), guestBuilder.novoGuestResponse().getId());
        Assertions.assertEquals(guestResponse.getBody().getName(), guestBuilder.novoGuestResponse().getName());
        Assertions.assertEquals(guestResponse.getBody().getSocialSecurityNumber(),guestBuilder.novoGuestResponse().getSocialSecurityNumber());
        Assertions.assertEquals(guestResponse.getBody().getDateOfBirth(),guestBuilder.novoGuestResponse().getDateOfBirth());
        Assertions.assertEquals(guestResponse.getBody().getEmail(), guestBuilder.novoGuestResponse().getEmail());
        Assertions.assertEquals(guestResponse.getBody().getPhone(), guestBuilder.novoGuestResponse().getPhone());
        Assertions.assertEquals(guestResponse.getBody().getGuestAddress().get(0).getStreetName(), guestBuilder.novoGuestFullResponse().getGuestAddress().get(0).getId());
        Assertions.assertEquals(guestResponse.getBody().getGuestAddress().get(0).getStreetName(), guestBuilder.novoGuestFullResponse().getGuestAddress().get(0).getStreetName());
    }

    @Test
    public void testeDeveDeletarGuestCadastradoComSucesso() {

        GuestService guestService = mock(GuestService.class);
        GuestController guestController = new GuestController(guestService);

        doNothing().when(guestService).delete(anyLong());
        guestController.delete(1L);

        verify(guestService, times(1)).delete(1L);
    }

    @Test
    public void testeDeveCriarUsuarioVinculadoAoEnderecoComSucesso() {

        GuestService guestService = mock(GuestService.class);
        GuestController guestController = new GuestController(guestService);

        when(guestService.save(any(GuestRequest.class))).thenReturn(guestBuilder.novoGuestResponse());
        ResponseEntity<GuestResponse> guestResponse = guestController.save(guestBuilder.novoGuestRequest());


        Assertions.assertEquals(guestResponse.getBody().getId(), guestBuilder.novoGuestResponse().getId());
        Assertions.assertEquals(guestResponse.getBody().getName(), guestBuilder.novoGuestResponse().getName());
        Assertions.assertEquals(guestResponse.getBody().getSocialSecurityNumber(),guestBuilder.novoGuestResponse().getSocialSecurityNumber());
        Assertions.assertEquals(guestResponse.getBody().getDateOfBirth(),guestBuilder.novoGuestResponse().getDateOfBirth());
        Assertions.assertEquals(guestResponse.getBody().getEmail(), guestBuilder.novoGuestResponse().getEmail());
        Assertions.assertEquals(guestResponse.getBody().getPhone(), guestBuilder.novoGuestResponse().getPhone());
    }

    @Test
    public void testeDeveAtualizarClienteEEnderecoVinculadoComSucesso() {

        GuestService guestService = mock(GuestService.class);
        GuestController guestController = new GuestController(guestService);

        when(guestService.update(any(GuestRequest.class), any())).thenReturn(guestBuilder.novoGuestResponse());
        ResponseEntity<GuestResponse> guestResponse = guestController.update(guestBuilder.novoGuestRequest(), 1L);

        Assertions.assertEquals(guestResponse.getBody().getId(), guestBuilder.novoGuestResponse().getId());
        Assertions.assertEquals(guestResponse.getBody().getName(), guestBuilder.novoGuestResponse().getName());
        Assertions.assertEquals(guestResponse.getBody().getSocialSecurityNumber(),guestBuilder.novoGuestResponse().getSocialSecurityNumber());
        Assertions.assertEquals(guestResponse.getBody().getDateOfBirth(),guestBuilder.novoGuestResponse().getDateOfBirth());
        Assertions.assertEquals(guestResponse.getBody().getEmail(), guestBuilder.novoGuestResponse().getEmail());
        Assertions.assertEquals(guestResponse.getBody().getPhone(), guestBuilder.novoGuestResponse().getPhone());
    }
}
