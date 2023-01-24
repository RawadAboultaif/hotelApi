package com.company.hotelaria.hotel.tests.unitTest;

import com.company.hotelaria.hotel.builders.UnitBuilder;
import com.company.hotelaria.hotel.core.model.unit.UnitResponse;
import com.company.hotelaria.hotel.core.mapper.UnitMapper;
import com.company.hotelaria.hotel.enums.UnitEnum;
import com.company.hotelaria.hotel.exception.BusinessException;
import com.company.hotelaria.hotel.repository.UnitRepository;
import com.company.hotelaria.hotel.service.UnitService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UnitServiceTest {

    @InjectMocks
    private UnitService unitService;
    @Mock
    private UnitMapper unitMapper;
    @Mock
    private UnitRepository unitRepository;

    @Test
    public void testMustFindUnitByName() {

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
    public void testMustSaveUnit() {

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
    public void testMustReturnErrorWhenSavingUnitWithNameAlreadyUsed() {

        when(unitMapper.requestToEntity(any())).thenReturn(UnitBuilder.novoUnit());
        when(unitRepository.findByName(any())).thenReturn(Optional.of(UnitBuilder.novoUnit()));


        assertThrows(BusinessException.class, () -> unitService.save(UnitBuilder.novoUnitRequest()));
    }

    @Test
    public void testMustUpdate() {

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
    public void testMustReturnErrorWhenUpdatingUnitNameToUnitNameAlreadyUsed() {

        when(unitRepository.findById(any())).thenReturn(Optional.of(UnitBuilder.novoUnitComUnitNameDiferente()));
        when(unitRepository.findByName(any())).thenReturn(Optional.of(UnitBuilder.novoUnit()));


        assertThrows(BusinessException.class, () -> unitService.update(UnitBuilder.novoUnitRequest(), UnitBuilder.novoUnitResponse().getId()));
    }

    @Test
    public void testMustDoUnitCheckout() {

        when(unitRepository.findByName(any())).thenReturn(Optional.of(UnitBuilder.novoUnit()));
        when(unitMapper.entityToResponse(any())).thenReturn(UnitBuilder.novoUnitResponse());

        UnitResponse unitResponse = unitService.updateCheckOut(UnitBuilder.novoUnitRequest().getName());

        assertEquals(unitResponse.getStatus(), UnitEnum.EMPTY);
    }

    @Test
    public void testMustDoUnitCheckIn() {

        when(unitRepository.findByName(any())).thenReturn(Optional.of(UnitBuilder.novoUnit()));
        when(unitMapper.entityToResponse(any())).thenReturn(UnitBuilder.novoUnitFullResponse());

        UnitResponse unitResponse = unitService.updateCheckIn(UnitBuilder.novoUnitRequest().getName());

        assertEquals(unitResponse.getStatus(), UnitEnum.FULL);
    }

    @Test
    public void testMustDeleteUnit() {

        when(unitRepository.findById(any())).thenReturn(Optional.of(UnitBuilder.novoUnit()));
        doNothing().when(unitRepository).deleteById(any());

        unitService.delete(1L);

        verify(unitRepository, times(1)).deleteById(any());
    }
}
