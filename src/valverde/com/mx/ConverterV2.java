package valverde.com.mx;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static valverde.com.mx.util.Colors.ANSI_RESET;
import static valverde.com.mx.util.Colors.ANSI_YELLOW;
import static valverde.com.mx.util.Constants.BY_CURRENCY_CODE;
import static valverde.com.mx.util.Constants.BY_DEFAULT;
import static valverde.com.mx.util.Constants.BY_PAIR;
import static valverde.com.mx.util.Constants.EXCHANGE_URL;

public class ConverterV2 {

    public void getConversionRates(String ... args)  {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .header("Accept", "application/json")
                .GET();
        if(args != null && args.length == 0) {
            requestBuilder.uri(URI.create(EXCHANGE_URL.concat(BY_DEFAULT)));
        }
        if(args != null && args.length == 1) {
            requestBuilder.uri(URI.create(EXCHANGE_URL.concat(BY_CURRENCY_CODE)+args[0]));
        }
        if(args != null && args.length == 2) {
            requestBuilder.uri(URI.create(EXCHANGE_URL.concat(BY_PAIR)+args[0]+"/"+args[1]));
        }
        if(args != null && args.length == 3) {
            requestBuilder.uri(URI.create(EXCHANGE_URL.concat(BY_PAIR)+args[0]+"/"+args[1]+"/"+args[2]));
        }

        HttpRequest request = requestBuilder.build();

        HttpResponse<String> response;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("\n"+ANSI_YELLOW+response.body()+ANSI_RESET);

    }

}
