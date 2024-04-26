package com.eos.apps.assistant.services.impl;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Service
public class FirebaseInitializer {
    private final static String creds = "{\n" +
            "  \"type\": \"service_account\",\n" +
            "  \"project_id\": \"shopping-f97ad\",\n" +
            "  \"private_key_id\": \"cf4a4482a85f7d926c247a7002a7428b3554b803\",\n" +
            "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDGqCcCK1gLa0wn\\nYRYSwyYs9cF48JmygXNxvlC28bFarkmaRTnCesR+p0E6TJO/3tMCaEYcshgfqtFG\\nMRmkl1NjtGX2hSSFGzX7v9bHruxQYi1AfVQR/mCoE44Z3zfKd87izapHHrf58sT0\\nSiHk983H4D0PpQkxN9Pa2KFcz6xmXudRaGQM/rK7lvqDEtyJUDasDLCtwjZ1COQC\\nJGiSw7gTeHhf7zOAvkY6J5V6v1iV1jnN18gVqd/o56iKg8pQ2Bz4WfC4W1ECcANE\\nlDWaKhs9zuLipmo3QzgyV2IC2qbc/SzCIcIVsjeiY5YcQ+hVI5hY0unNv+oXv1Os\\nrH7McWKzAgMBAAECggEABsr/MnrK8vEDVKFkQNX9ezIYIIuRpeH5UZ1hYzAWkXQ+\\nPhE57WWHTJFpkJXHw5rZ+k4Yf6izXHnZ2B2bs94c0t77UZex7P9GNEqWDJP1rrX8\\nuCTQ+CCLq5Onm0jfvoOkRmsj9HSfc+OSSowyZoL2vWv0ipFpJA61ptkbg3HygjmT\\nEve2DnkpSO3J8+Y+tblIm9H+K5kAVM71V3bqI/znJ/8HXQwd61wAn9OE5Vx7nWel\\nnYsIh3ktB3kPIcTKpeJ/CTH1wG9Cjgf2hN+X5fakD0YA3OmEIckMUeGos1paE9um\\nGqjXmlZsAvDj/dxe3jwgoDRSRGOQY1r+4H5JA1MnEQKBgQD1buWLFkyaYFpwjbQe\\nERJm33xRBZ/WNs/1x7pLVfyK7PhqWfLvf/WcP72eGCTgbv1udlO06q5yjrhtSbTj\\n2AuCIGYGUxSs/Het9wdSinU2GVCuNntZi2I/y3UcmxavKZ32FkMvOkwZPhgX8WRu\\n9LV2acQ65fQUbQPjtjWg1PivewKBgQDPNbLSxh79C0OFT3qMNcHxfIfr3prJltXf\\nNxKKkjh7Eeq1e5eg74sX5DxrHlyrzHUQqR6gTdt/jkPz/YherwtUKuM+UCQknkzN\\n1tj+TMK3AGM7FE6WSqT8Lf9VoBPEul8aiM41RzvP3v6Bn3cYNT//ZaOXZ/yjabBU\\nfZXhjXFYKQKBgEqTufg7fGdkY9bTmCmSsm7D9fiApwLmgOaEHB2MMULdotZeIq2X\\nykACNlH1sC7qSuPn3KOatAtnRq8euHKLUWPnCFlfoWa3CH66E2WHYPphUty8/VpG\\nOkJ8Rke88HWlRbRjK2MMadc5wQR1ezkj7ofet5vWCAa04XuuADmwvShFAoGBAMt+\\npxIMdF56c2M2vNl56fApJqtkQIt90KiYw2LHWySyYdi9ehoZZnaeNR/V/5jRAJ7O\\nGD/CdrB8nQu/j++85NlSDT4jInu74x27w/tqk0d53/ojZVKAGCt5WOtUfFWV2tSK\\nSklUnc715uiSHTnvAdzmipSlER3DK+KuO0X5zLiRAoGATbtAN0LvfQTxWyRLDnqW\\nMwvOVekou8jb/3lhgNdwurTLov9G8oM+K1ilUuSM+EUIMp2TE8Q7p0P1IOZyKXAH\\nkKA14De/Iv/gBZeEWh4Q+s0oW0tgbnDqnSVHsbDI2RRw1aYzZplpjozq5EY6aWDJ\\n72P/WXYryCkmZ7UnYaH4RMU=\\n-----END PRIVATE KEY-----\\n\",\n" +
            "  \"client_email\": \"firebase-adminsdk-xcej4@shopping-f97ad.iam.gserviceaccount.com\",\n" +
            "  \"client_id\": \"113605129732718818367\",\n" +
            "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
            "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
            "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
            "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-xcej4%40shopping-f97ad.iam.gserviceaccount.com\",\n" +
            "  \"universe_domain\": \"googleapis.com\"\n" +
            "}\n";

    @Autowired
    private Environment env;

    @PostConstruct
    public void initialize() throws IOException {
        FirebaseOptions options = getOptions(env.getActiveProfiles());
        FirebaseApp.initializeApp(options);
    }

    public static FirebaseOptions getOptions(String[] activeProfiles) throws IOException {
        InputStream serviceAccount = new ByteArrayInputStream(creds.getBytes(StandardCharsets.UTF_8));
        return new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://shopping-f97ad.firebaseio.com")
                .build();
    }
}
