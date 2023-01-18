package com.company.hotelaria.hotel.tests.rentTests;

import com.company.hotelaria.hotel.builders.GuestBuilder;
import com.company.hotelaria.hotel.builders.RentBuilder;
import com.company.hotelaria.hotel.builders.UnitBuilder;
import com.company.hotelaria.hotel.core.dto.rent.RentResponse;
import com.company.hotelaria.hotel.core.mapper.RentMapper;
import com.company.hotelaria.hotel.exception.BusinessException;
import com.company.hotelaria.hotel.repository.GuestRepository;
import com.company.hotelaria.hotel.repository.RentRepository;
import com.company.hotelaria.hotel.repository.UnitRepository;
import com.company.hotelaria.hotel.service.RentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RentServiceTest {

    @InjectMocks
    private RentService rentService;
    @Mock
    private RentMapper rentMapper;
    @Mock
    private RentRepository rentRepository;
    @Mock
    private GuestRepository guestRepository;
    @Mock
    private UnitRepository unitRepository;

    @Test
    public void testDeveRetonarRentBuscadoporIdComSucesso() {

        when(rentMapper.entityToResponse(any())).thenReturn(RentBuilder.novoRentResponse());
        when(rentRepository.findById(any())).thenReturn(Optional.of(RentBuilder.novoRent()));

        RentResponse rentResponse = rentService.findById(RentBuilder.novoRent().getId());

        assertEquals(rentResponse.getId(), RentBuilder.novoRent().getId());
        assertEquals(rentResponse.getCheckOut(), RentBuilder.novoRent().getCheckOut());
        assertEquals(rentResponse.getCheckIn(), RentBuilder.novoRent().getCheckIn());
        assertEquals(rentResponse.getTotalPrice(), RentBuilder.novoRent().getTotalPrice());
        assertEquals(rentResponse.getGuest().getName(), RentBuilder.novoRent().getGuest().getName());
        assertEquals(rentResponse.getUnit().getPrice(), RentBuilder.novoRent().getUnit().getPrice());
    }

    @Test
    public void testDeveCadastrarRentComSucesso() {

        when(guestRepository.findById(any())).thenReturn(Optional.of(GuestBuilder.novoGuest()));
        when(unitRepository.findByName(any())).thenReturn(Optional.of(UnitBuilder.novoUnit()));
        when(rentMapper.requestToEntity(any())).thenReturn(RentBuilder.novoRent());
        when(rentMapper.entityToResponse(any())).thenReturn(RentBuilder.novoRentResponse());

        RentResponse rentResponse = rentService.save(RentBuilder.novoRentRequest(), GuestBuilder.novoGuest().getId(), UnitBuilder.novoUnit().getName());

        assertEquals(rentResponse.getId(), RentBuilder.novoRent().getId());
        assertEquals(rentResponse.getCheckOut(), RentBuilder.novoRent().getCheckOut());
        assertEquals(rentResponse.getCheckIn(), RentBuilder.novoRent().getCheckIn());
        assertEquals(rentResponse.getTotalPrice(), RentBuilder.novoRent().getTotalPrice());
        assertEquals(rentResponse.getGuest().getName(), RentBuilder.novoRent().getGuest().getName());
        assertEquals(rentResponse.getUnit().getPrice(), RentBuilder.novoRent().getUnit().getPrice());
    }

    @Test
    public void testDeveRetornarErroAoCadastrarRentComUnitStatusFull() {

        when(guestRepository.findById(any())).thenReturn(Optional.of(GuestBuilder.novoGuest()));
        when(unitRepository.findByName(any())).thenReturn(Optional.of(UnitBuilder.novoUnitStatusFull()));


        assertThrows(BusinessException.class, () -> rentService.save(RentBuilder.novoRentRequest(), GuestBuilder.novoGuest().getId(), UnitBuilder.novoUnitStatusFull().getName()));
    }

    @Test
    public void testDeveDeletarRentComSucesso() {

        when(rentRepository.findById(any())).thenReturn(Optional.of(RentBuilder.novoRent()));
        doNothing().when(rentRepository).deleteById(any());

        rentService.delete(1L);

        verify(rentRepository, times(1)).deleteById(any());
    }
}
