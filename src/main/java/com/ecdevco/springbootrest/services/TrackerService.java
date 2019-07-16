package com.ecdevco.springbootrest.services;

import com.ecdevco.springbootrest.entities.Tracker;
import com.ecdevco.springbootrest.repositories.TrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TrackerService {

    @Autowired
    private TrackerRepository trackerRepository;

    @Transactional(propagation =  Propagation.REQUIRES_NEW)
    public Boolean createLog(Tracker tracker) {
        return trackerRepository.save(tracker) != null ? true : false;
    }
}
