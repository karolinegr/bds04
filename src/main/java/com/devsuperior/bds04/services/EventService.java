package com.devsuperior.bds04.services;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    @Autowired
    private EventRepository repository;

    public Page<EventDTO> findAll(Pageable pageable){
        Page<Event> page = repository.findAll(pageable);
        return page.map(EventDTO::new);
    }

    public EventDTO insert(EventDTO newEvent){
        Event entity = new Event();
        entity.setName(newEvent.getName());
        entity.setDate(newEvent.getDate());
        entity.setUrl(newEvent.getUrl());
        entity.setCity(new City(newEvent.getCityId(), null));
        entity = repository.save(entity);
        return new EventDTO(entity);
    }
}
