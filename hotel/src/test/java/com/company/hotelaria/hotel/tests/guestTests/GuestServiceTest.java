package com.company.hotelaria.hotel.tests.guestTests;

import com.company.hotelaria.hotel.builders.AddressBuilder;
import com.company.hotelaria.hotel.builders.EmployeeBuilder;
import com.company.hotelaria.hotel.builders.GuestBuilder;
import com.company.hotelaria.hotel.builders.PaymentBuilder;
import com.company.hotelaria.hotel.core.dto.guest.GuestFullResponse;
import com.company.hotelaria.hotel.core.dto.guest.GuestRequest;
import com.company.hotelaria.hotel.core.dto.guest.GuestResponse;
import com.company.hotelaria.hotel.core.entities.Guest;
import com.company.hotelaria.hotel.core.mapper.GuestMapper;
import com.company.hotelaria.hotel.enums.Message;
import com.company.hotelaria.hotel.exception.BusinessException;
import com.company.hotelaria.hotel.repository.AddressRepository;
import com.company.hotelaria.hotel.repository.EmployeeRepository;
import com.company.hotelaria.hotel.repository.GuestRepository;
import com.company.hotelaria.hotel.repository.PaymentsRepository;
import com.company.hotelaria.hotel.service.GuestService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GuestServiceTest {

    @InjectMocks
    private GuestService guestService;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private PaymentsRepository paymentRepository;

    @Mock
    private GuestRepository guestRepository;
    @Mock
    private GuestMapper guestMapper;

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    public void testDeveListarTodosOsGuest() {
        when(guestRepository.findAll()).thenReturn(GuestBuilder.novaListaGuest());
        when(guestMapper.listEntityToListResponse(any())).thenReturn(GuestBuilder.novaListaDeGuestResponse());

        List<GuestResponse> listGuest = guestService.findAll();

        assertTrue(listGuest.toString().equals(GuestBuilder.novaListaDeGuestResponse().toString()));
    }

    @Test
    public void testDeveBuscarGuestPorCpfComSucesso() {

        when(guestRepository.findBySocialSecurityNumber(any())).thenReturn(Optional.of(GuestBuilder.novoGuest()));
        when(guestMapper.entityToResponseFull(any())).thenReturn(GuestBuilder.novoGuestFullResponse());
        when(addressRepository.findAllByGuestId(any())).thenReturn(AddressBuilder.novaListaAddressResponse());
        when(paymentRepository.findAllByGuestId(any())).thenReturn(PaymentBuilder.novaListaPaymentResponse());

        GuestFullResponse guestFullResponse = guestService.findBySocialSecurityNumber("1");

        assertNotNull(guestFullResponse);
        assertEquals(guestFullResponse.getGuestAddress().toString(), AddressBuilder.novaListaAddressResponse().toString());
        assertEquals(guestFullResponse.getGuestPayment().toString(), PaymentBuilder.novaListaPaymentResponse().toString());
    }

    @Test
    public void testeDeveCadastrarGuestComSucesso() {

        when(guestMapper.requestToEntity(any())).thenReturn(GuestBuilder.novoGuest());
        when(guestRepository.save(any())).thenReturn(GuestBuilder.novoGuest());
        when(guestMapper.entityToResponse(any())).thenReturn(GuestBuilder.novoGuestResponse());

        GuestResponse guestResponse = guestService.save(GuestBuilder.novoGuestRequest());
        GuestResponse testGuestResponse = GuestBuilder.novoGuestResponse();

        assertNotNull(guestResponse);
        assertEquals(guestResponse.getId(), testGuestResponse.getId());
        assertEquals(guestResponse.getName(), testGuestResponse.getName());
        assertEquals(guestResponse.getEmail(), testGuestResponse.getEmail());
        assertEquals(guestResponse.getSocialSecurityNumber(), testGuestResponse.getSocialSecurityNumber());
        assertEquals(guestResponse.getDateOfBirth(), testGuestResponse.getDateOfBirth());
        assertEquals(guestResponse.getPhone(), testGuestResponse.getPhone());
    }

    @Test
    public void testDeveRetornarErroAoCadastrarGuestComCpfJaCadastradoExecption1() {

        when(guestMapper.requestToEntity(any())).thenReturn(GuestBuilder.novoGuest());
        when(guestRepository.findBySocialSecurityNumber(any())).thenReturn(Optional.of(GuestBuilder.novoGuest()));


        assertThrows(BusinessException.class, () -> guestService.save(GuestBuilder.novoGuestRequest()));
    }

    @Test
    public void testDeveRetornarErroAoCadastrarGuestComCpfJaCadastradoExecption2() {

        when(guestMapper.requestToEntity(any())).thenReturn(GuestBuilder.novoGuest());
        when(employeeRepository.findBySocialSecurityNumber(any())).thenReturn(Optional.of(EmployeeBuilder.novoEmployee()));


        assertThrows(BusinessException.class, () -> guestService.save(GuestBuilder.novoGuestRequest()));
    }


    @Test
    public void testDeveAtualizarGuestComSucesso() {

        when(guestRepository.findById(any())).thenReturn(Optional.of(GuestBuilder.novoGuest()));
        when(guestMapper.entityToResponse(any())).thenReturn(GuestBuilder.novoGuestResponse());

        GuestResponse guestResponse = guestService.update(GuestBuilder.novoGuestRequest(), GuestBuilder.novoGuestResponse().getId());

        assertNotNull(guestResponse);
        assertEquals(guestResponse.toString(), GuestBuilder.novoGuestResponse().toString());
    }

    @Test
    public void testeDeveRetornarErroAoAtualizarGuestParaUmCpfJaCadastradoException1() {

        when(guestRepository.findById(any())).thenReturn(Optional.of(GuestBuilder.novoGuestComSocialSecurityNumberDiferente()));

        when(guestRepository.findBySocialSecurityNumber(any())).thenReturn(Optional.of(GuestBuilder.novoGuest()));

        assertThrows(BusinessException.class, () -> guestService.update(GuestBuilder.novoGuestRequest(), GuestBuilder.novoGuestResponse().getId()));

    }

    @Test
    public void testeDeveRetornarErroAoAtualizarGuestParaUmCpfJaCadastradoException2() {

        when(guestRepository.findById(any())).thenReturn(Optional.of(GuestBuilder.novoGuestComSocialSecurityNumberDiferente()));

        when(employeeRepository.findBySocialSecurityNumber(any())).thenReturn(Optional.of(EmployeeBuilder.novoEmployee()));

        assertThrows(BusinessException.class, () -> guestService.update(GuestBuilder.novoGuestRequest(), GuestBuilder.novoGuestResponse().getId()));

    }

    @Test
    public void testDeveDeletarGuestComSucesso() {

        when(guestRepository.findById(any())).thenReturn(Optional.of(GuestBuilder.novoGuest()));
        doNothing().when(guestRepository).deleteById(any());

        guestService.delete(1L);

        verify(guestRepository, times(1)).deleteById(any());
    }

    @Test
    public void testDeveRetornarErroAoDeletarGuestComIdInexistente() {

        when(guestRepository.findById(any())).thenThrow(Message.ID_DO_NOT_EXIST.asBusinessException());

        assertThrows(BusinessException.class, ()-> guestService.delete(1L));
    }
}
