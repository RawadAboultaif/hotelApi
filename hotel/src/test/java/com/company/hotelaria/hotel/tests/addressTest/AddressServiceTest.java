package com.company.hotelaria.hotel.tests.addressTest;

import com.company.hotelaria.hotel.builders.AddressBuilder;
import com.company.hotelaria.hotel.builders.EmployeeBuilder;
import com.company.hotelaria.hotel.builders.GuestBuilder;
import com.company.hotelaria.hotel.core.mapper.AddressMapper;
import com.company.hotelaria.hotel.core.model.address.AddressResponse;
import com.company.hotelaria.hotel.repository.AddressRepository;
import com.company.hotelaria.hotel.repository.EmployeeRepository;
import com.company.hotelaria.hotel.repository.GuestRepository;
import com.company.hotelaria.hotel.service.AddressService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    @InjectMocks
    private AddressService addressService;
    @Mock
    private AddressMapper addressMapper;
    @Mock
    private AddressRepository addressRepository;
    @Mock
    private GuestRepository guestRepository;
    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    public void testMustFindAddressById() {

        when(addressMapper.entityToResponse(any())).thenReturn(AddressBuilder.novoAddressResponse());
        when(addressRepository.findById(any())).thenReturn(Optional.of(AddressBuilder.novoAddress()));

        AddressResponse addressResponse = addressService.findById(1L);

        assertEquals(addressResponse.getId(), AddressBuilder.novoAddressResponse().getId());
        assertEquals(addressResponse.getStreetName(), AddressBuilder.novoAddressResponse().getStreetName());
        assertEquals(addressResponse.getNumber(), AddressBuilder.novoAddressResponse().getNumber());
        assertEquals(addressResponse.getZipcode(), AddressBuilder.novoAddressResponse().getZipcode());
    }

    @Test
    public void testMustSaveNewAddressToGuest() {

        when(guestRepository.findById(any())).thenReturn(Optional.of(GuestBuilder.novoGuest()));
        when(addressMapper.requestToEntity(any())).thenReturn(AddressBuilder.novoAddress());
        when(addressRepository.save(any())).thenReturn(AddressBuilder.novoAddress());
        when(addressMapper.entityToResponse(any())).thenReturn(AddressBuilder.novoAddressResponse());

        AddressResponse addressResponse = addressService.saveAddressGuest(AddressBuilder.novoAddressRequest(), GuestBuilder.novoGuest().getId());

        assertEquals(addressResponse.getId(), AddressBuilder.novoAddressResponse().getId());
        assertEquals(addressResponse.getStreetName(), AddressBuilder.novoAddressResponse().getStreetName());
        assertEquals(addressResponse.getNumber(), AddressBuilder.novoAddressResponse().getNumber());
        assertEquals(addressResponse.getZipcode(), AddressBuilder.novoAddressResponse().getZipcode());
    }

    @Test
    public void testMustSabeNewAddressToEmployee() {

        when(employeeRepository.findById(any())).thenReturn(Optional.of(EmployeeBuilder.novoEmployee()));
        when(addressMapper.requestToEntity(any())).thenReturn(AddressBuilder.novoAddress());
        when(addressRepository.save(any())).thenReturn(AddressBuilder.novoAddress());
        when(addressMapper.entityToResponse(any())).thenReturn(AddressBuilder.novoAddressResponse());

        AddressResponse addressResponse = addressService.saveAddressEmployee(AddressBuilder.novoAddressRequest(), EmployeeBuilder.novoEmployee().getId());

        assertEquals(addressResponse.getId(), AddressBuilder.novoAddressResponse().getId());
        assertEquals(addressResponse.getStreetName(), AddressBuilder.novoAddressResponse().getStreetName());
        assertEquals(addressResponse.getNumber(), AddressBuilder.novoAddressResponse().getNumber());
        assertEquals(addressResponse.getZipcode(), AddressBuilder.novoAddressResponse().getZipcode());
    }

    @Test
    public void testMustUpdateAddress() {

        when(addressRepository.findById(any())).thenReturn(Optional.of(AddressBuilder.novoAddress()));
        when(addressMapper.entityToResponse(any())).thenReturn(AddressBuilder.novoAddressResponse());

        AddressResponse addressResponse = addressService.update(AddressBuilder.novoAddressRequest(), AddressBuilder.novoAddress().getId());

        assertEquals(addressResponse.getId(), AddressBuilder.novoAddressResponse().getId());
        assertEquals(addressResponse.getStreetName(), AddressBuilder.novoAddressResponse().getStreetName());
        assertEquals(addressResponse.getNumber(), AddressBuilder.novoAddressResponse().getNumber());
        assertEquals(addressResponse.getComplement(), AddressBuilder.novoAddressResponse().getComplement());
        assertEquals(addressResponse.getCity(), AddressBuilder.novoAddressResponse().getCity());
        assertEquals(addressResponse.getZipcode(), AddressBuilder.novoAddressResponse().getZipcode());
    }

    @Test
    public void testMustDeleteAddress() {
        when(addressRepository.findById(any())).thenReturn(Optional.of(AddressBuilder.novoAddress()));
        doNothing().when(addressRepository).deleteById(any());

        addressService.delete(1L);

        verify(addressRepository, times(1)).deleteById(any());
    }

}
