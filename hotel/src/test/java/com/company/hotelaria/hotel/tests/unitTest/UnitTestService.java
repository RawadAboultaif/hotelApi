package com.company.hotelaria.hotel.tests.unitTest;

import com.company.hotelaria.hotel.builders.GuestBuilder;
import com.company.hotelaria.hotel.builders.UnitBuilder;
import com.company.hotelaria.hotel.core.dto.unit.UnitRequest;
import com.company.hotelaria.hotel.core.dto.unit.UnitResponse;
import com.company.hotelaria.hotel.core.entities.Unit;
import com.company.hotelaria.hotel.core.mapper.UnitMapper;
import com.company.hotelaria.hotel.exception.BusinessException;
import com.company.hotelaria.hotel.repository.UnitRepository;
import com.company.hotelaria.hotel.service.UnitService;
import net.bytebuddy.pool.TypePool;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UnitTestService {

    @InjectMocks
    private UnitService unitService;
    @Mock
    private UnitMapper unitMapper;
    @Mock
    private UnitRepository unitRepository;

    @Test
    public void testDeveBuscarUnitPorNome() {

        when(unitMapper.entityToResponse(any())).thenReturn(UnitBuilder.novoUnitResponse());
        when(unitRepository.findByName(any())).thenReturn(Optional.of(UnitBuilder.novoUnit()));

        UnitResponse unitResponse = unitService.findByName(UnitBuilder.novoUnit().getName());

        assertEquals(unitResponse.getId(), UnitBuilder.novoUnitResponse().getId());
        assertEquals(unitResponse.getName(), UnitBuilder.novoUnitResponse().getName());
        assertEquals(unitResponse.getPrice(), UnitBuilder.novoUnitResponse().getPrice());
        assertEquals(unitResponse.getLimitGuest(), UnitBuilder.novoUnitResponse().getLimitGuest());
        assertEquals(unitResponse.getStatus(), UnitBuilder.novoUnitResponse().getStatus());
    }

    @Test
    public void testDeveCadastrarUnitComSucesso() {

        when(unitMapper.requestToEntity(any())).thenReturn(UnitBuilder.novoUnit());
        when(unitRepository.findByName(any())).thenReturn(Optional.empty());
        when(unitRepository.save(any())).thenReturn(UnitBuilder.novoUnit());
        when(unitMapper.entityToResponse(any())).thenReturn(UnitBuilder.novoUnitResponse());

        UnitResponse unitResponse = unitService.save(UnitBuilder.novoUnitRequest());

        assertEquals(unitResponse.getId(), UnitBuilder.novoUnitResponse().getId());
        assertEquals(unitResponse.getName(), UnitBuilder.novoUnitResponse().getName());
        assertEquals(unitResponse.getPrice(), UnitBuilder.novoUnitResponse().getPrice());
        assertEquals(unitResponse.getLimitGuest(), UnitBuilder.novoUnitResponse().getLimitGuest());
        assertEquals(unitResponse.getStatus(), UnitBuilder.novoUnitResponse().getStatus());
    }

    @Test
    public void testDeveAtualizarUnitComSucesso() {

        when(unitRepository.findById(any())).thenReturn(Optional.of(UnitBuilder.novoUnit()));
        when(unitMapper.entityToResponse(any())).thenReturn(UnitBuilder.novoUnitResponse());

        UnitResponse unitResponse = unitService.update(UnitBuilder.novoUnitRequest(), UnitBuilder.novoUnitResponse().getId());

        assertEquals(unitResponse.getId(), UnitBuilder.novoUnitResponse().getId());
        assertEquals(unitResponse.getName(), UnitBuilder.novoUnitResponse().getName());
        assertEquals(unitResponse.getPrice(), UnitBuilder.novoUnitResponse().getPrice());
        assertEquals(unitResponse.getLimitGuest(), UnitBuilder.novoUnitResponse().getLimitGuest());
        assertEquals(unitResponse.getStatus(), UnitBuilder.novoUnitResponse().getStatus());
    }

    @Test
    public void testDeveRetornarErroAoAtualizarUnitNameParaUmNomeJaExistente() {

        when(unitRepository.findById(any())).thenReturn(Optional.of(UnitBuilder.novoUnitComUnitNameDiferente()));
        when(unitRepository.findByName(any())).thenReturn(Optional.of(UnitBuilder.novoUnit()));


        assertThrows(BusinessException.class, () -> unitService.update(UnitBuilder.novoUnitRequest(), UnitBuilder.novoUnitResponse().getId()));
    }

    @Test
    public void testDeveRealizarUnitCheckOut() {

    }
}
