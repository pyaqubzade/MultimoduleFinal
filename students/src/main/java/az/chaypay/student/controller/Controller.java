package az.chaypay.student.controller;

import com.nimbusds.srp6.SRP6CryptoParams;
import com.nimbusds.srp6.SRP6Exception;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.Random;

@Getter
@Setter
@RequiredArgsConstructor
class Response {
    private final String salt;
    private final String verifier;
}

@RestController
@RequiredArgsConstructor
public class Controller {

    private final SRP6Helper srp6Helper;

    @GetMapping("/create")
    ResponseEntity<Response> createUserPassword(
            @RequestParam String username,
            @RequestParam String password
    ) {
        var res = createSaltAndVerifier(username, password);
        return ResponseEntity.ok(new Response(res.getFirst(), res.getSecond()));
    }

    @GetMapping("/getStep2")
    ResponseEntity<SrpStep2Req> createUserPassword(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String salt,
            @RequestParam String b
    ) throws SRP6Exception {
        return ResponseEntity.ok(getAAndM1(username, password, salt, b));
    }

    public Pair<String, String> createSaltAndVerifier(String username, String password) {
        var salt = getRandomHexString(32);
        var N = new BigInteger("115b8b692e0e045692cf280b436735c77a5a9e8a9e7ed56c965f87db5b2a2ece3", 16);
        var g = new BigInteger("2", 10);
        var hash1 = deleteZeros(DigestUtils.sha256Hex(username+":"+password).toLowerCase());
        var hash = deleteZeros(DigestUtils.sha256Hex((salt + hash1).toUpperCase()));
        var X = new BigInteger(hash, 16).mod(N);
        var k = new BigInteger("ea366b4591f92bd1aa926cae731bf510c10639b63bdaa79b3319bff4f43e19e3", 16);
        var V = g.modPow(X, N);
        var verifier = V.toString(16);
        return Pair.of(salt, verifier);
    }

    private String getRandomHexString(int numchars) {
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        while (sb.length() < numchars) {
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0, numchars);
    }

    String deleteZeros(String string) {
        while (string.charAt(0) == '0') {
            string = string.substring(1);
        }
        return string;
    }

    public SrpStep2Req getAAndM1(String username, String password, String salt, String b) throws SRP6Exception {
        var N = new BigInteger("115b8b692e0e045692cf280b436735c77a5a9e8a9e7ed56c965f87db5b2a2ece3", 16);
        var g = new BigInteger("2", 10);
        var H = "SHA-256";
        var client = srp6Helper.generateSRP6ClientSession();
        client.step1(username, password);
        var res = client.step2(new SRP6CryptoParams(N, g, H), new BigInteger(salt, 16),
                new BigInteger(b, 16));
        return SrpStep2Req.builder().srpA(res.A.toString(16)).srpM1(res.M1.toString(16)).build();
    }

}
