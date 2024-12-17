package com.API.IoT.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.API.IoT.dto.mapper.SystemMapper;
import com.API.IoT.dto.system.SystemCreateDTO;
import com.API.IoT.dto.system.SystemResponseDTO;
import com.API.IoT.dto.user.UserResponseDTO;
import com.API.IoT.entity.SystemEntity;
import com.API.IoT.infra.RestErrorMessage;
import com.API.IoT.service.SystemService;

import jakarta.validation.Valid;

@Tag(name = "Sistemas", description = "Contém todas as operações relativas aos recursos para criação, e leitura dos sistemas (dispositivos IoT)")
@RestController
@RequestMapping("/api/v1/systems")
public class SystemController {
    @Autowired
    private SystemService systemService;

    @Operation(summary = "Cadastrar um sistema",
        description = "Cadastrará um novo sistema",
        responses = {
                @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso.",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDTO.class))),
                @ApiResponse(responseCode = "422", description = "Campo(s) inválido(s)",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestErrorMessage.class)))
        })
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid SystemCreateDTO systemCreatedDTO) {
        systemService.save(systemCreatedDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Buscar um sistema pelo ID",
    responses = {
            @ApiResponse(responseCode = "200", description = "Sistema encontrado com sucesso.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SystemResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Sistema não encontrado.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestErrorMessage.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<SystemResponseDTO> findById(@PathVariable Long id) {
        SystemEntity system = systemService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(SystemMapper.toResponseDTO(system));
    }

    @Operation(summary = "Buscar todos os sistemas",
    responses = {
            @ApiResponse(responseCode = "200", description = "Sistemas encontrados com sucesso.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = SystemResponseDTO.class)))
    })
    @GetMapping
    public ResponseEntity<List<SystemResponseDTO>> findAll() {
        List<SystemEntity> systemEntities = systemService.findAll();
        List<SystemResponseDTO> systemResponse = SystemMapper.toListResponseDTO(systemEntities);

        return ResponseEntity.status(HttpStatus.OK).body(systemResponse);
    }  

    @Operation(summary = "Buscar sistemas pelo id de usuário",
        responses = {
            @ApiResponse(responseCode = "200", description = "Sistemas encontrados com sucesso.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = SystemResponseDTO.class)))
        })
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SystemResponseDTO>> findByUserId(@PathVariable Long userId) {
        List<SystemResponseDTO> systems = SystemMapper.toListResponseDTO(
                systemService.findByUserId(userId)
        );

        return ResponseEntity.status(HttpStatus.OK).body(systems);
    }
}
