package az.chaypay.app.controller;

import com.nimbusds.srp6.SRP6ClientSession;
import com.nimbusds.srp6.SRP6CryptoParams;
import com.nimbusds.srp6.SRP6ServerSession;
import com.nimbusds.srp6.SRP6VerifierGenerator;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class SRP6Helper {

    public static final SRP6CryptoParams CRYPTO_PARAMS =
            new SRP6CryptoParams(SRP6CryptoParams.N_256, SRP6CryptoParams.g_common, "SHA-256");

    public static SRP6VerifierGenerator generateSRP6VerifierGenerator() {
        SRP6VerifierGenerator srp6VerifierGenerator = new SRP6VerifierGenerator(CRYPTO_PARAMS);
        srp6VerifierGenerator.setXRoutine(SRP6ThinbusRoutines.getXRoutine());
        return srp6VerifierGenerator;
    }

    public SRP6ClientSession generateSRP6ClientSession() {
        SRP6ClientSession srp6ClientSession = new SRP6ClientSession(1000);
        srp6ClientSession.setXRoutine(SRP6ThinbusRoutines.getXRoutine());
        srp6ClientSession.setHashedKeysRoutine(SRP6ThinbusRoutines.getURoutine());
        srp6ClientSession.setClientEvidenceRoutine(SRP6ThinbusRoutines.getClientEvidenceRoutine());
        srp6ClientSession.setServerEvidenceRoutine(SRP6ThinbusRoutines.getServerEvidenceRoutine());
        return srp6ClientSession;
    }

    public SRP6ServerSession generateSRP6ServerSession() {
        SRP6ServerSession srp6ServerSession = new SRP6ServerSession(CRYPTO_PARAMS);
        srp6ServerSession.setClientEvidenceRoutine(SRP6ThinbusRoutines.getClientEvidenceRoutine());
        srp6ServerSession.setHashedKeysRoutine(SRP6ThinbusRoutines.getURoutine());
        srp6ServerSession.setServerEvidenceRoutine(SRP6ThinbusRoutines.getServerEvidenceRoutine());
        return srp6ServerSession;
    }

    public BigInteger generateRandomSalt() {
        SRP6VerifierGenerator gen = generateSRP6VerifierGenerator();
        return new BigInteger(1, gen.generateRandomSalt());
    }



}