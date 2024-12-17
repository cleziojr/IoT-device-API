package com.API.IoT.controller;

import com.API.IoT.dto.mapper.UserMapper;
import com.API.IoT.dto.user.UserCreateDTO;
import com.API.IoT.dto.user.UserResponseDTO;
import com.API.IoT.entity.UserEntity;
import com.API.IoT.infra.RestErrorMessage;
import com.API.IoT.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Usuários", description = "Contém todas as operações relativas aos recursos para criação, e leitura de um usuário")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "Cadastrar um usuário",
        description = "Cadastrará um novo usuário no sistema",
        responses = {
                @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso.",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDTO.class))),
                @ApiResponse(responseCode = "422", description = "Campo(s) inválido(s)",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestErrorMessage.class)))
        })
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid UserCreateDTO userCreateDTO) {
        userService.save(userCreateDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Buscar um usuário pelo ID",
        responses = {
                @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso.",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDTO.class))),
                @ApiResponse(responseCode = "404", description = "Usuário não encontrado.",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestErrorMessage.class)))
        })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
        UserEntity user = userService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(UserMapper.toResponseDTO(user));
    }

    @Operation(summary = "Buscar todos os usuários",
        responses = {
                @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDTO.class)))
        })
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        List<UserResponseDTO> user = UserMapper.toListResponseDTO(
                userService.findAll()
        );

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
    
    @Operation(summary = "Buscar usuário pelo email",
        responses = {
                @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDTO.class)))
        })
    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDTO> findByEmail(@PathVariable String email) {
        UserEntity user = userService.findByEmail(email);

        return ResponseEntity.status(HttpStatus.OK).body(UserMapper.toResponseDTO(user));
    }
}
