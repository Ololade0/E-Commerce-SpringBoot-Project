package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.models.VerificationToken;

public interface VerificationTokenService {
    VerificationToken createToken(String email);
}
