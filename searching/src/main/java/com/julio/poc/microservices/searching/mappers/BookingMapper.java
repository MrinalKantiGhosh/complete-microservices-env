package com.julio.poc.microservices.searching.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.julio.poc.microservices.searching.dtos.BookingGetDTO;
import com.julio.poc.microservices.searching.dtos.BookingPostDTO;
import com.julio.poc.microservices.searching.entities.Booking;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    @Mappings({
            @Mapping(target = "bookingIdentity.idRoom", source = "idRoom"),
            @Mapping(target = "bookingIdentity.startDate", source = "startDate"),
            @Mapping(target = "bookingIdentity.endDate", source = "endDate")
    })
    Booking toEntity(BookingPostDTO dto);

    @Mappings({
            @Mapping(target = "idRoom", source = "bookingIdentity.idRoom"),
            @Mapping(target = "startDate", source = "bookingIdentity.startDate"),
            @Mapping(target = "endDate", source = "bookingIdentity.endDate")
    })
    BookingGetDTO toDTO(Booking entity);

    List<BookingGetDTO> toDTO(List<Booking> entities);

    default Page<BookingGetDTO> toDTO(Page<Booking> page) {
        List<BookingGetDTO> responses = toDTO(page.getContent());
        return new PageImpl<>(responses, page.getPageable(), page.getTotalElements());
    }
}