package com.backend.springboot.docker.mapper;

import com.backend.springboot.docker.dto.PersonDto;
import com.backend.springboot.docker.dto.ResponseDto;
import com.backend.springboot.docker.response.entity.Response;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DtoMapper {

    public List<PersonDto> mapToPersonDtoList(List<Response> responseList) {

        return responseList.stream().map(r ->
                new PersonDto(r.getPerson().getPersonId(), r.getPerson().getName(), r.getPerson().getEntryDate(), r.getResponse()))
                .collect(Collectors.toList());

    }

    public List<ResponseDto> mapToResponseDtoList(List<Response> responseList) {

        return responseList.stream().map(r ->
                new ResponseDto(r.getId(), r.getQuestion().getQuestion(), r.getPerson().getName(), r.getResponse(), r.getComments()))
                .collect(Collectors.toList());

    }

    public ResponseDto mapToResponseDto(Response response) {

        return new ResponseDto(response.getId(),
                response.getQuestion().getQuestion(), response.getPerson().getName(), response.getResponse(), response.getComments());

    }

}
