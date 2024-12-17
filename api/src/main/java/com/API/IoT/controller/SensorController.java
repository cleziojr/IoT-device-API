package com.API.IoT.controller;

import com.API.IoT.dto.mapper.SensorMapper;
import com.API.IoT.dto.sensor.SensorCreateDTO;
import com.API.IoT.dto.sensor.SensorResponseDTO;
import com.API.IoT.entity.SensorEntity;
import com.API.IoT.infra.RestErrorMessage;
import com.API.IoT.service.SensorService;

import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Sensores", description = "Contém os dados coletados pelos sensores de determinado sistema. Contém operações de escrita e leitura.")
@RestController
@RequestMapping("/api/v1/sensors")
public class SensorController {
    @Autowired
    private SensorService sensorService;

    @Operation(summary = "Cadastrar dados referentes ao sistemas",
        responses = {
                @ApiResponse(responseCode = "201", description = "Dado cadastrado com sucesso.",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = SensorResponseDTO.class))),
                @ApiResponse(responseCode = "422", description = "Campo(s) inválido(s)",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestErrorMessage.class)))
        })
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid SensorCreateDTO sensorCreateDTO) {
        sensorService.save(sensorCreateDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Buscar um dado pelo ID",
    responses = {
            @ApiResponse(responseCode = "200", description = "Dado encontrado com sucesso.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SensorResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestErrorMessage.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<SensorResponseDTO> findById(@PathVariable Long id) {
        SensorEntity sensor = sensorService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(SensorMapper.toResponseDTO(sensor));
    }

    @Operation(summary = "Buscar todos os dados",
        responses = {
                @ApiResponse(responseCode = "200", description = "Dados encontrados com sucesso.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SensorResponseDTO.class)))
        })
    @GetMapping
    public ResponseEntity<List<SensorResponseDTO>> findAll() {
        List<SensorResponseDTO> sensors = SensorMapper.toListResponseDTO(
                sensorService.findAll()
        );

        return ResponseEntity.status(HttpStatus.OK).body(sensors);
    }

    @Operation(summary = "Buscar dados referente a um sistema pelo seu ID",
    responses = {
            @ApiResponse(responseCode = "200", description = "Dados encontrados com sucesso.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = SensorResponseDTO.class)))
    })
    @GetMapping("/system/{systemId}")
    public ResponseEntity<List<SensorResponseDTO>> findBySystemId(@PathVariable Long systemId) {
        List<SensorResponseDTO> sensors = SensorMapper.toListResponseDTO(
                sensorService.findBySystemId(systemId)
        );

        return ResponseEntity.status(HttpStatus.OK).body(sensors);
    }

}
