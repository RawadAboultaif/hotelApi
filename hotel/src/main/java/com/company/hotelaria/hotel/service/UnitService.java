package com.company.hotelaria.hotel.service;

import com.company.hotelaria.hotel.core.model.unit.UnitRequest;
import com.company.hotelaria.hotel.core.model.unit.UnitResponse;
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
        if(request.getPrice() <= 0 ||  request.getLimitGuest() <= 0) {
            throw Message.UNIT_PRICE_OR_LIMITGUEST_UNDER_ZERO.asBusinessException();
        }
        Unit unit = this.unitMapper.requestToEntity(request);
        unit.setStatus(UnitEnum.EMPTY);
        this.unitRepository.findByName(unit.getName()).ifPresent(p -> {
            throw Message.UNIT_NAME_EXIST.asBusinessException();
        });
        Unit unitResult = this.unitRepository.save(unit);
        UnitResponse unitResponse = this.unitMapper.entityToResponse(unitResult);
        return unitResponse;
    }

    @Transactional
    public UnitResponse update(@Valid UnitRequest request, Long id){
        log.info(" update request = {}", request);
        if(request.getPrice() < 0 ||  request.getLimitGuest() < 0) {
            throw Message.UNIT_PRICE_OR_LIMITGUEST_UNDER_ZERO.asBusinessException();
        }
        Unit unit = this.unitRepository.findById(id).orElseThrow(Message.ID_DO_NOT_EXIST::asBusinessException);
        if(!request.getName().equalsIgnoreCase(unit.getName())) {
            this.unitRepository.findByName(request.getName()).ifPresent(p -> {
                throw Message.UNIT_NAME_EXIST.asBusinessException();
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
    public UnitResponse updateCheckOut(String name) {
        log.info(" checkout request = {}", name);
        Unit unit = this.unitRepository.findByName(name).orElseThrow(Message.UNIT_NAME_DO_NOT_EXIST::asBusinessException);
        unit.updateStatus(UnitEnum.EMPTY);
        UnitResponse unitResponse = this.unitMapper.entityToResponse(unit);
        return unitResponse;
    }

    @Transactional
    public UnitResponse updateCheckIn(String name) {
        log.info(" checkout request = {}", name);
        Unit unit = this.unitRepository.findByName(name).orElseThrow(Message.UNIT_NAME_DO_NOT_EXIST::asBusinessException);
        unit.updateStatus(UnitEnum.FULL);
        UnitResponse unitResponse = this.unitMapper.entityToResponse(unit);
        return unitResponse;
    }

    public void delete(Long id) {
        Unit unit = this.unitRepository.findById(id).orElseThrow(Message.ID_DO_NOT_EXIST::asBusinessException);
        this.unitRepository.deleteById(unit.getId());
        log.info("method = delete number = {}",id);
    }
}
