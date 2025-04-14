import com.hotsauce.payments.creditcard.utils.CreditCardRegistry;
import com.hotsauce.payments.creditcard.core.CreditCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCreditCard {
    @BeforeEach
    public void setUp(){

    }

    @Test
    public void testCreditCard() {
        CreditCard creditCard = CreditCardRegistry.load();
        assertNotNull(creditCard);
    }

    @Test
    public void testCreditCardSale() {

    }

    @Test
    public void testCreditCardVoid() {

    }

}
