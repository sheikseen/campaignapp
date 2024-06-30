package com.jvdm.campaign.service;

import java.util.Date;
import java.util.List;

import com.jvdm.campaign.dto.CampDto;

public interface CampService {

    String saveCamp(CampDto campDto);

    CampDto findCampById(String campId);

    List<CampDto> findAllCamps();

    CampDto updateCamp(CampDto campDto);

    CampDto deleteCampById(String campId);

    public boolean existsByTitle(String title);

    boolean isValidDate(Date startDate, Date endDate);
}
