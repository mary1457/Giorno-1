package mariapiabaldoin.Giorno_1.controllers;

import mariapiabaldoin.Giorno_1.entities.Dipendente;
import mariapiabaldoin.Giorno_1.exceptions.BadRequestException;
import mariapiabaldoin.Giorno_1.payloads.DipendenteLoginDTO;
import mariapiabaldoin.Giorno_1.payloads.DipendenteLoginResponseDTO;
import mariapiabaldoin.Giorno_1.payloads.NewDipendenteDTO;
import mariapiabaldoin.Giorno_1.services.AuthService;
import mariapiabaldoin.Giorno_1.services.DipendentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private DipendentiService dipendentiService;

    @PostMapping("/login")
    public DipendenteLoginResponseDTO login(@RequestBody DipendenteLoginDTO body) {
        return new DipendenteLoginResponseDTO(this.authService.checkCredentialsAndGenerateToken(body));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente save(@RequestBody @Validated NewDipendenteDTO body, BindingResult validationResult) {

        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }

        return this.dipendentiService.save(body);
    }
}