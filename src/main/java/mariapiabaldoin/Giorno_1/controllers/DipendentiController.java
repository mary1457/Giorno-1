package mariapiabaldoin.Giorno_1.controllers;


import mariapiabaldoin.Giorno_1.entities.Dipendente;
import mariapiabaldoin.Giorno_1.exceptions.BadRequestException;
import mariapiabaldoin.Giorno_1.payloads.NewDipendenteDTO;
import mariapiabaldoin.Giorno_1.services.DipendentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;


@RestController
@RequestMapping("/dipendenti")
public class DipendentiController {
    @Autowired
    private DipendentiService dipendentiService;


    @GetMapping
    public Page<Dipendente> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "id") String sortBy) {

        return this.dipendentiService.findAll(page, size, sortBy);
    }


    @GetMapping("/{dipendenteId}")
    public Dipendente findById(@PathVariable UUID dipendenteId) {
        return this.dipendentiService.findById(dipendenteId);
    }


    @PutMapping("/{dipendenteId}")
    public Dipendente findByIdAndUpdate(@PathVariable UUID dipendenteId, @RequestBody @Validated NewDipendenteDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            validationResult.getAllErrors().forEach(System.out::println);
            throw new BadRequestException("Ci sono stati errori nel payload!");
        }

        return this.dipendentiService.findByIdAndUpdate(dipendenteId, body);
    }


    @DeleteMapping("/{dipendenteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID dipendenteId) {
        this.dipendentiService.findByIdAndDelete(dipendenteId);
    }

    @PatchMapping("/{dipendenteId}/avatar")
    public Dipendente uploadAvatar(@RequestParam("avatar") MultipartFile file, @PathVariable UUID dipendenteId) {

        return this.dipendentiService.uploadAvatar(file, dipendenteId);
    }
}
