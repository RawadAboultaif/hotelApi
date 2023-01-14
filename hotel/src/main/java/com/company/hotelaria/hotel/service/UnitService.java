package com.company.hotelaria.hotel.service;

import com.company.hotelaria.hotel.core.dto.address.AddressResponse;
import com.company.hotelaria.hotel.core.dto.guest.GuestRequest;
import com.company.hotelaria.hotel.core.dto.guest.GuestResponse;
import com.company.hotelaria.hotel.core.dto.unit.UnitRequest;
import com.company.hotelaria.hotel.core.dto.unit.UnitResponse;
import com.company.hotelaria.hotel.core.entities.Guest;
import com.company.hotelaria.hotel.core.entities.Unit;
import com.company.hotelaria.hotel.core.mapper.UnitMapper;
import com.company.hotelaria.hotel.enums.Message;
import com.company.hotelaria.hotel.enums.UnitEnum;
import com.company.hotelaria.hotel.repository.UnitRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@AllArgsConstructor
@Service
@Validated
@Slf4j
public class UnitService {

    private UnitMapper unitMapper;
    private UnitRepository unitRepository;

    public UnitResponse findByName(String name){
        log.info("findByName");
        return this.unitMapper.entityToResponse(this.unitRepository.findByName(name)
                .orElseThrow(Message.UNIT_NAME_DO_NOT_EXIST::asBusinessException
                ));
    }

    public UnitResponse save(@Valid UnitRequest request){

        log.info("save request = {}", request);
        Unit unit = this.unitMapper.requestToEntity(request);
        this.unitRepository.findByName(request.getName()).ifPresent(p -> {
            throw Message.UNIT_NAME_DO_NOT_EXIST.asBusinessException();
        });
        unit.setStatus(UnitEnum.EMPTY);
        Unit unitResult = this.unitRepository.save(unit);
        UnitResponse unitResponse = this.unitMapper.entityToResponse(unitResult);
        return unitResponse;
    }

    @Transactional
    public UnitResponse update(@Valid UnitRequest request, Long id){
        log.info(" update request = {}", request);
        Unit unit = this.unitRepository.findById(id).orElseThrow(Message.ID_DO_NOT_EXIST::asBusinessException);
        if(!request.getName().equalsIgnoreCase(unit.getName())) {
            this.unitRepository.findByName(request.getName()).ifPresent(p -> {
                throw Message.UNIT_NAME_DO_NOT_EXIST.asBusinessException();
            });
        }
        unit.updateUnit(
                request.getName(),
                request.getPrice(),
                request.getLimitGuest());
        UnitResponse unitResponse = this.unitMapper.entityToResponse(unit);
        return unitResponse;
    }

    @Transactional
    public UnitResponse updateCheckOut(Long id) {
        log.info(" checkout request = {}", id);
        Unit unit = this.unitRepository.findById(id).orElseThrow(Message.ID_DO_NOT_EXIST::asBusinessException);
        unit.updateStatus(UnitEnum.FULL);
        UnitResponse unitResponse = this.unitMapper.entityToResponse(unit);
        return unitResponse;
    }

    @Transactional
    public UnitResponse updateCheckIn(Long id) {
        log.info(" checkout request = {}", id);
        Unit unit = this.unitRepository.findById(id).orElseThrow(Message.ID_DO_NOT_EXIST::asBusinessException);
        unit.updateStatus(UnitEnum.EMPTY);
        UnitResponse unitResponse = this.unitMapper.entityToResponse(unit);
        return unitResponse;
    }

    public void delete(Long id) {
        Unit unit = this.unitRepository.findById(id).orElseThrow(Message.ID_DO_NOT_EXIST::asBusinessException);
        this.unitRepository.deleteById(unit.getId());
        log.info("method = delete number = {}",id);
    }
}
