package org.wiremock.extension.jwt;

import com.github.jknack.handlebars.Helper;
import com.github.tomakehurst.wiremock.extension.TemplateHelperProviderExtension;
import java.util.Map;

public class JwtHelpersExtension implements TemplateHelperProviderExtension {

    private final JwtSigningKeySettings jwtSigningKeySettings;

    public JwtHelpersExtension(JwtSigningKeySettings jwtSigningKeySettings) {
        this.jwtSigningKeySettings = jwtSigningKeySettings;
    }

    @Override
    public Map<String, Helper<?>> provideTemplateHelpers() {
        JwtHandlebarsHelper jwtHandlebarsHelper = new JwtHandlebarsHelper(jwtSigningKeySettings);
        JwksHandlebarsHelper jwksHandlebarsHelper = new JwksHandlebarsHelper(jwtSigningKeySettings);
        JwkHandlebarsHelper jwkHandlebarsHelper = new JwkHandlebarsHelper(jwtSigningKeySettings);
        return Map.of(
                "jwt", jwtHandlebarsHelper,
                "claims", new ClaimListHandlebarsHelper(),
                "claimsObject", new ClaimsObjectHandlebarsHelper(),
                "jwks", jwksHandlebarsHelper,
                "jwk", jwkHandlebarsHelper);
    }

    @Override
    public String getName() {
        return "jwt-template-helpers";
    }
}
