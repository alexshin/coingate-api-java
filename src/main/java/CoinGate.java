import com.mashape.unirest.http.exceptions.UnirestException;
import net.socium.coingateapi.client.ApiClient;
import net.socium.coingateapi.client.EnvironmentType;
import net.socium.coingateapi.exceptions.ApiException;
import org.json.JSONObject;

import java.math.BigDecimal;

public class CoinGate {
    public static void main(String[] args) {
        ApiClient client = new ApiClient(EnvironmentType.sandbox, "MwFbwyrn4VmHQ45QzxaPco7SQyQk9h7ztbetxgiS");
        // 121418
        JSONObject res;
        try {
            res = client.checkout(121418, "btc");
            System.out.println(res);
        } catch (UnirestException e) {
            e.printStackTrace();
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
}
