package in.rohanshukla.fruitsapi.config;

import com.razorpay.RazorpayClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class RazorpayConfig {

    @Bean
    public RazorpayClient razorpayClient() throws Exception {
        return new RazorpayClient(
                "rzp_test_RvkRUEHFCxl4Rz",
                "ZMrAoX9v2jIoBqgfefKXEv2K"
        );
    }
}

