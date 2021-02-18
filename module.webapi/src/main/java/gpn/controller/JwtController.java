package gpn.controller;


import gpn.contract.SessionUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtController {
    /**
     * Получить фейковые данные по графикам
     *
     * @return фейковые данные по графикам
     */
    @GetMapping("/testjwt")
    public ResponseEntity<?> getFakeResult(@AuthenticationPrincipal SessionUser sUser) {
        try {
            return ResponseEntity.ok("succes");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
