package mariapiabaldoin.Giorno_1.services;

import mariapiabaldoin.Giorno_1.entities.Dipendente;
import mariapiabaldoin.Giorno_1.exceptions.UnauthorizedException;
import mariapiabaldoin.Giorno_1.payloads.DipendenteLoginDTO;
import mariapiabaldoin.Giorno_1.tools.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthService {
    @Autowired
    private DipendentiService dipendentiService;

    @Autowired
    private JWT jwt;

    public String checkCredentialsAndGenerateToken(DipendenteLoginDTO body) {

        Dipendente found = this.dipendentiService.findByEmail(body.email());

        if (found.getPassword().equals(body.password())) {

            String accessToken = jwt.createToken(found);

            return accessToken;
        } else {

            throw new UnauthorizedException("Credenziali errate!");
        }
    }

}
