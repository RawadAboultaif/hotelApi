package com.company.hotelaria.hotel.tests.employeeTest;

import com.company.hotelaria.hotel.builders.AddressBuilder;
import com.company.hotelaria.hotel.builders.EmployeeBuilder;
import com.company.hotelaria.hotel.builders.GuestBuilder;
import com.company.hotelaria.hotel.core.model.employee.EmployeeFullResponse;
import com.company.hotelaria.hotel.core.model.employee.EmployeeResponse;
import com.company.hotelaria.hotel.core.mapper.EmployeeMapper;
import com.company.hotelaria.hotel.enums.Message;
import com.company.hotelaria.hotel.exception.BusinessException;
import com.company.hotelaria.hotel.repository.AddressRepository;
import com.company.hotelaria.hotel.repository.EmployeeRepository;
import com.company.hotelaria.hotel.repository.GuestRepository;
import com.company.hotelaria.hotel.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private EmployeeMapper employeeMapper;
    @Mock
    private AddressRepository addressRepository;

    @Mock
    private GuestRepository guestRepository;

    @Test
    public void testMustListAllClients() {
        when(employeeRepository.findAll()).thenReturn(EmployeeBuilder.novaListaEmployee());
        when(employeeMapper.listEntityToListResponse(any())).thenReturn(EmployeeBuilder.novaListaEmployeeResponse());

        List<EmployeeResponse> listEmployee = employeeService.findAll();

        assertTrue(listEmployee.toString().equals(EmployeeBuilder.novaListaEmployeeResponse().toString()));
    }

    @Test
    public void testMustFindEmployeeByCpf() {

        when(employeeRepository.findBySocialSecurityNumber(any())).thenReturn(Optional.of(EmployeeBuilder.novoEmployee()));
        when(employeeMapper.entityToResponseFull(any())).thenReturn(EmployeeBuilder.novoEmployeeFullResponse());
        when(addressRepository.findAllByEmployeeId(any())).thenReturn(AddressBuilder.novaListaAddressResponse());


        EmployeeFullResponse employeeFullResponse = employeeService.findBySocialSecurityNumber("1");

        assertNotNull(employeeFullResponse);
        assertEquals(employeeFullResponse.getEmployeeAddress().toString(), AddressBuilder.novaListaAddressResponse().toString());
    }

    @Test
    public void testMustSaveEmployee() {

        when(employeeMapper.requestToEntity(any())).thenReturn(EmployeeBuilder.novoEmployee());
        when(employeeRepository.save(any())).thenReturn(EmployeeBuilder.novoEmployee());
        when(employeeMapper.entityToResponse(any())).thenReturn(EmployeeBuilder.novoEmployeeResponse());

        EmployeeResponse employeeResponse = employeeService.save(EmployeeBuilder.novoEmployeeRequest());
        EmployeeResponse testEmployeeResponse = EmployeeBuilder.novoEmployeeResponse();

        assertNotNull(employeeResponse);
        assertEquals(employeeResponse.getId(), testEmployeeResponse.getId());
        assertEquals(employeeResponse.getName(), testEmployeeResponse.getName());
        assertEquals(employeeResponse.getEmail(), testEmployeeResponse.getEmail());
        assertEquals(employeeResponse.getSocialSecurityNumber(), testEmployeeResponse.getSocialSecurityNumber());
        assertEquals(employeeResponse.getPhone(), testEmployeeResponse.getPhone());
    }

    @Test
    public void testMustReturnErrorSavingEmployeeWithCpfAlreadyUsedExecption1() {

        when(employeeMapper.requestToEntity(any())).thenReturn(EmployeeBuilder.novoEmployee());
        when(employeeRepository.findBySocialSecurityNumber(any())).thenReturn(Optional.of(EmployeeBuilder.novoEmployee()));


        assertThrows(BusinessException.class, () -> employeeService.save(EmployeeBuilder.novoEmployeeRequest()));
    }

    @Test
    public void testMustReturnErrorSavingEmployeeWithCpfAlreadyUsedExecption2() {

        when(employeeMapper.requestToEntity(any())).thenReturn(EmployeeBuilder.novoEmployee());
        when(guestRepository.findBySocialSecurityNumber(any())).thenReturn(Optional.of(GuestBuilder.novoGuest()));


        assertThrows(BusinessException.class, () -> employeeService.save(EmployeeBuilder.novoEmployeeRequest()));
    }

    @Test
    public void testMustUpdateEmployee() {

        when(employeeRepository.findById(any())).thenReturn(Optional.of(EmployeeBuilder.novoEmployee()));
        when(employeeMapper.entityToResponse(any())).thenReturn(EmployeeBuilder.novoEmployeeResponse());

        EmployeeResponse employeeResponse = employeeService.update(EmployeeBuilder.novoEmployeeRequest(), EmployeeBuilder.novoEmployeeResponse().getId());

        assertNotNull(employeeResponse);
        assertEquals(employeeResponse.toString(), EmployeeBuilder.novoEmployeeResponse().toString());
    }

    @Test
    public void testMustReturnErroUpdatingEmployeeToCpfAlreadyUsedException1() {

        when(employeeRepository.findById(any())).thenReturn(Optional.of(EmployeeBuilder.novoEmployeeComSocialSecurityNumberDiferente()));

        when(employeeRepository.findBySocialSecurityNumber(any())).thenReturn(Optional.of(EmployeeBuilder.novoEmployee()));

        assertThrows(BusinessException.class, () -> employeeService.update(EmployeeBuilder.novoEmployeeRequest(), EmployeeBuilder.novoEmployeeResponse().getId()));

    }

    @Test
    public void testMustReturnErroUpdatingEmployeeToCpfAlreadyUsedException2() {

        when(employeeRepository.findById(any())).thenReturn(Optional.of(EmployeeBuilder.novoEmployeeComSocialSecurityNumberDiferente()));

        when(guestRepository.findBySocialSecurityNumber(any())).thenReturn(Optional.of(GuestBuilder.novoGuest()));

        assertThrows(BusinessException.class, () -> employeeService.update(EmployeeBuilder.novoEmployeeRequest(), EmployeeBuilder.novoEmployeeResponse().getId()));

    }

    @Test
    public void testMustDeleteEmloyee() {

        when(employeeRepository.findById(any())).thenReturn(Optional.of(EmployeeBuilder.novoEmployee()));
        doNothing().when(employeeRepository).deleteById(any());

        employeeService.delete(1L);

        verify(employeeRepository, times(1)).deleteById(any());
    }

    @Test
    public void testMustReturnErrorDeleteEmployeeWithIdNonexistent() {

        when(employeeRepository.findById(any())).thenThrow(Message.ID_DO_NOT_EXIST.asBusinessException());

        assertThrows(BusinessException.class, ()-> employeeService.delete(1L));
    }
}
