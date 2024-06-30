package com.jvdm.campaign.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Service;

import com.jvdm.campaign.dto.CampDto;
import com.jvdm.campaign.entity.Camp;
import com.jvdm.campaign.repository.CampRepository;
import com.jvdm.campaign.service.CampService;

@Service
public class CampServiceImpl implements CampService {

    private final CampRepository campRepository;

    public CampServiceImpl(CampRepository campRepository) {
        this.campRepository = campRepository;
    }

    @Override
    public String saveCamp(CampDto campDto) {

        Camp camp = new Camp();
        camp.setId(UUID.randomUUID().toString());
        camp.setTitle(campDto.getTitle());
        camp.setDescription(campDto.getDescription());
        camp.setStartDate(campDto.getStartDate());
        camp.setEndDate(campDto.getEndDate());
        camp.setPlace(campDto.getPlace());
        camp.setAmount(campDto.getAmount());
        camp.setChildSeatAmount(campDto.getChildSeatAmount());
        camp.setSeats(campDto.getSeats());
        camp.setSeatsBooked(0);
        camp.setStatus(Camp.CampStatus.CREATED);

        campRepository.save(camp);
        return camp.getId();
    }

    @Override
    public CampDto findCampById(String campId) {
        return campRepository.findById(campId).map(this::mapToCampDto).orElse(null);
    }

    @Override
    public List<CampDto> findAllCamps() {
        List<Camp> camps = campRepository.findAll();
        return camps.stream()
                .map((camp) -> mapToCampDto(camp))
                .collect(Collectors.toList());

    }

    @Override
    public CampDto updateCamp(CampDto campDto) {
        try {
            Optional<Camp> optionalCamp = campRepository.findById(campDto.getId());
            if (optionalCamp.isPresent()) {
                Camp camp = optionalCamp.get();
                camp.setTitle(campDto.getTitle());
                camp.setDescription(campDto.getDescription());
                camp.setPlace(campDto.getPlace());
                camp.setAmount(campDto.getAmount());
                camp.setChildSeatAmount(campDto.getChildSeatAmount());
                camp.setSeats(campDto.getSeats());
                camp.setStatus(campDto.getStatus());

                campRepository.save(camp);
                return campDto;
            }
        } catch (Exception e) {
            return null;
        }
        return null;

    }

    @Override
    public CampDto deleteCampById(String campId) {
        Optional<Camp> optionalCamp = campRepository.findById(campId);
        if (optionalCamp.isPresent()) {
            Camp camp = optionalCamp.get();
            campRepository.delete(camp);
            return mapToCampDto(camp);
        } else {
            return null;
        }

    }

    private CampDto mapToCampDto(Camp camp) {
        CampDto campDto = new CampDto();
        campDto.setId(camp.getId());
        campDto.setTitle(camp.getTitle());
        campDto.setDescription(camp.getDescription());
        campDto.setPlace(camp.getPlace());
        campDto.setAmount(camp.getAmount());
        campDto.setStartDate(camp.getStartDate());
        campDto.setEndDate(camp.getEndDate());
        campDto.setSeats(camp.getSeats());
        campDto.setStatus(camp.getStatus());

        return campDto;

    }

    @Override
    public boolean existsByTitle(String title) {
        return campRepository.existsByTitle(title);
    }

    public boolean isValidDate(Date startDate, Date endDate) {
        return startDate != null && endDate != null && startDate.before(endDate) && startDate.after(new Date());
    }
}