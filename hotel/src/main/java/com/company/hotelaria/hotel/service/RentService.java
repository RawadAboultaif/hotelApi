package com.company.hotelaria.hotel.service;

import com.company.hotelaria.hotel.core.entities.Guest;
import com.company.hotelaria.hotel.core.entities.Rent;
import com.company.hotelaria.hotel.core.entities.Unit;
import com.company.hotelaria.hotel.core.mapper.GuestMapper;
import com.company.hotelaria.hotel.core.mapper.RentMapper;
import com.company.hotelaria.hotel.core.mapper.UnitMapper;
import com.company.hotelaria.hotel.core.model.rent.RentRequest;
import com.company.hotelaria.hotel.core.model.rent.RentResponse;
import com.company.hotelaria.hotel.enums.Message;
import com.company.hotelaria.hotel.enums.UnitEnum;
import com.company.hotelaria.hotel.repository.GuestRepository;
import com.company.hotelaria.hotel.repository.RentRepository;
import com.company.hotelaria.hotel.repository.UnitRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

import static java.time.temporal.ChronoUnit.DAYS;


@AllArgsConstructor
@Service
@Validated
@Slf4j
public class RentService {

    private RentMapper rentMapper;
    private RentRepository rentRepository;
    private GuestMapper guestMapper;
    private GuestRepository guestRepository;
    private UnitMapper unitMapper;
    private UnitRepository unitRepository;

    public RentResponse findById(Long id){
        log.info("findById");
        return this.rentMapper.entityToResponse(this.rentRepository.findById(id)
                .orElseThrow(Message.ID_DO_NOT_EXIST::asBusinessException
                ));
    }

    @Transactional
    public RentResponse save(@Valid RentRequest request, Long id, String name){

        log.info("save request = {}", request);
        if(request.getCheckIn().compareTo(request.getCheckOut()) > 0) {
            throw Message.RENT_CHECKIN_GREATER_THAN_CHECKOUT.asBusinessException();
        }
        Guest guestResult = this.guestRepository.findById(id)
                .orElseThrow(Message.ID_DO_NOT_EXIST::asBusinessException);
        Unit unitResult = this.unitRepository.findByName(name)
                .orElseThrow(Message.UNIT_NAME_DO_NOT_EXIST::asBusinessException);
        if(unitResult.getStatus() == UnitEnum.F) {
            throw Message.UNIT_ALREADY_OCCUPIED.asBusinessException();
        } else {
            unitResult.updateStatus(UnitEnum.FULL);
            Rent rent = this.rentMapper.requestToEntity(request);
            long daysBetween = DAYS.between(request.getCheckIn(), request.getCheckOut());
            rent.setTotalPrice(daysBetween*unitResult.getPrice());
            rent.setGuest(guestResult);
            rent.setUnit(unitResult);
            Rent rentResult = this.rentRepository.save(rent);
            RentResponse rentResponse = this.rentMapper.entityToResponse(rentResult);
            return rentResponse;
        }
    }

    public void delete(Long id) {

        Rent rent = this.rentRepository.findById(id).orElseThrow(Message.ID_DO_NOT_EXIST::asBusinessException);
        this.rentRepository.deleteById(rent.getId());
        log.info("method = delete number = {}",id);
    }
}
