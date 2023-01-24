package com.company.hotelaria.hotel.tests.paymentTest;

import com.company.hotelaria.hotel.builders.GuestBuilder;
import com.company.hotelaria.hotel.builders.PaymentBuilder;
import com.company.hotelaria.hotel.core.mapper.PaymentMapper;
import com.company.hotelaria.hotel.core.model.payment.PaymentResponse;
import com.company.hotelaria.hotel.repository.GuestRepository;
import com.company.hotelaria.hotel.repository.PaymentsRepository;
import com.company.hotelaria.hotel.service.PaymentService;
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
public class PaymentServiceTest {

    @InjectMocks
    private PaymentService paymentService;
    @Mock
    private PaymentsRepository paymentRepository;
    @Mock
    private PaymentMapper paymentMapper;
    @Mock
    private GuestRepository guestRepository;

    @Test
    public void testMustFindPaymentById() {

        when(paymentMapper.entityToResponse(any())).thenReturn(PaymentBuilder.novoPaymentResponse());
        when(paymentRepository.findById(any())).thenReturn(Optional.of(PaymentBuilder.novoPayment()));

        PaymentResponse paymentResponse = paymentService.findById(PaymentBuilder.novoPaymentResponse().getId());

        assertEquals(paymentResponse.getId(), PaymentBuilder.novoPaymentResponse().getId());
        assertEquals(paymentResponse.getCard(), PaymentBuilder.novoPaymentResponse().getCard());
        assertEquals(paymentResponse.getMethod(), PaymentBuilder.novoPaymentResponse().getMethod());
    }

    @Test
    public void testMustSavePayment() {

        when(guestRepository.findById(any())).thenReturn(Optional.of(GuestBuilder.novoGuest()));
        when(paymentMapper.requestToEntity(any())).thenReturn(PaymentBuilder.novoPayment());
        when(paymentRepository.save(any())).thenReturn(PaymentBuilder.novoPayment());
        when(paymentMapper.entityToResponse(any())).thenReturn(PaymentBuilder.novoPaymentResponse());

        PaymentResponse paymentResponse = paymentService.save(PaymentBuilder.novoPaymentRequest(), GuestBuilder.novoGuestResponse().getId());

        assertEquals(paymentResponse.getId(), PaymentBuilder.novoPaymentResponse().getId());
        assertEquals(paymentResponse.getCard(), PaymentBuilder.novoPaymentResponse().getCard());
        assertEquals(paymentResponse.getMethod(), PaymentBuilder.novoPaymentResponse().getMethod());
    }

    @Test
    public void testMustUpdatePayment() {

        when(paymentRepository.findById(any())).thenReturn(Optional.of(PaymentBuilder.novoPayment()));
        when(paymentMapper.entityToResponse(any())).thenReturn(PaymentBuilder.novoPaymentResponse());

        PaymentResponse paymentResponse = paymentService.update(PaymentBuilder.novoPaymentRequest(), PaymentBuilder.novoPaymentResponse().getId());

        assertEquals(paymentResponse.getId(), PaymentBuilder.novoPaymentResponse().getId());
        assertEquals(paymentResponse.getCard(), PaymentBuilder.novoPaymentResponse().getCard());
        assertEquals(paymentResponse.getMethod(), PaymentBuilder.novoPaymentResponse().getMethod());
    }

    @Test
    public void testMustDeletePayment() {

        when(paymentRepository.findById(any())).thenReturn(Optional.of(PaymentBuilder.novoPayment()));
        doNothing().when(paymentRepository).deleteById(any());

        paymentService.delete(1L);

        verify(paymentRepository, times(1)).deleteById(any());
    }
}
