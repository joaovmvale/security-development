package com.securityDevelopment.equipment.controller;

import com.securityDevelopment.equipment.model.EquipmentModel;
import com.securityDevelopment.equipment.model.dto.EquipmentDTO;
import com.securityDevelopment.equipment.model.dto.EquipmentResponseDTO;
import com.securityDevelopment.equipment.service.EquipmentService;
import com.securityDevelopment.utils.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {
    EquipmentService equipmentService;

    @Autowired
    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @PostMapping
    public ResponseEntity<EquipmentResponseDTO> create(@RequestBody EquipmentDTO dto) throws CustomException {
        EquipmentModel equipment = equipmentService.create(dto.transformToEquipmentModel());
        EquipmentResponseDTO response = EquipmentResponseDTO.transformToEquipmentResponseDTO(equipment);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<EquipmentResponseDTO>> listAll() {
        List<EquipmentModel> equipment = equipmentService.listAll();
        List<EquipmentResponseDTO> response = equipment.stream().map(EquipmentResponseDTO::transformToEquipmentResponseDTO).collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentResponseDTO> findById(@PathVariable UUID id) {
        EquipmentModel equipment = equipmentService.getById(id);
        EquipmentResponseDTO response = EquipmentResponseDTO.transformToEquipmentResponseDTO(equipment);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipmentResponseDTO> update(@PathVariable UUID id, @RequestBody EquipmentDTO dto) throws CustomException {
        EquipmentModel equipment = equipmentService.getById(id);
        equipment.setSerial(dto.getSerial());
        equipment.setModel(dto.getModel());
        equipment.setMemory(dto.getMemory());
        equipment.setProcessor(dto.getProcessor());
        equipment.setStorage(dto.getStorage());

        equipmentService.create(equipment);
        EquipmentResponseDTO response = EquipmentResponseDTO.transformToEquipmentResponseDTO(equipment);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        equipmentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
