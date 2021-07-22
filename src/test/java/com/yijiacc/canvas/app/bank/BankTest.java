package com.yijiacc.canvas.app.bank;

import com.yijiacc.canvas.app.bank.account.Account;
import com.yijiacc.canvas.app.bank.card.CardNetwork;
import com.yijiacc.canvas.app.bank.card.CreditCard;
import com.yijiacc.canvas.app.bank.user.Name;
import com.yijiacc.canvas.app.util.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigInteger;
import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static com.yijiacc.canvas.app.util.Util.lengthMax;
import static com.yijiacc.canvas.app.util.Util.lengthMin;


public class BankTest {
    private final static LocalDate LOCAL_DATE = LocalDate.of(2021, 7, 20);

    @Mock
    Random random;

    @Mock
    Clock clock;

    private Clock fixedClock;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.openMocks(this);

        fixedClock = Clock.fixed(LOCAL_DATE.atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
        doReturn(fixedClock.instant()).when(clock).instant();
        doReturn(fixedClock.getZone()).when(clock).getZone();
    }

    @ParameterizedTest(name = "{displayName} : {0}")
    @MethodSource("requestCreditCardProvider")
    public void requestCreditCardTest(
            String testCaseName,
            List<Integer> randomNums,
            List<Integer> lengths,
            List<CreditCardRequest> creditCardRequests,
            List<Tuple<CreditCard, Class<? extends Exception>>> expectedCreditCards) {
        Bank bank = new Bank(random, fixedClock);
        for (int i = 0; i < creditCardRequests.size(); i++) {
            setUpRandom(random, randomNums.get(i), lengths.get(i));
            assertCreditCard(bank, creditCardRequests.get(i), expectedCreditCards.get(i));
        }
    }

    private static void setUpRandom(Random random, int randomNum, int length) {
        int min = lengthMin(10, length);
        int max = lengthMax(10, length);
        when(random.nextInt(max - min + 1) + min).thenReturn(randomNum);
    }

    private static void assertCreditCard(
            Bank bank,
            CreditCardRequest request,
            Tuple<CreditCard, Class<? extends Exception>> expectedResult) {
        CreditCard expectedCreditCard = expectedResult.first;
        Class<? extends Exception> exception = expectedResult.second;
        if (exception == null) {
            try {
                CreditCard creditCard = bank.requestCreditCard(request);
                assertEquals(expectedCreditCard, creditCard);
            } catch (Exception e) {
                fail(e);
            }
        } else {
            assertThrows(exception, () -> {
                bank.requestCreditCard(request);
            });
        }
    }

    private static Stream<Arguments> requestCreditCardProvider() {
        BigInteger accountNumber1 = new BigInteger("1000000000");
        Account account1 = new Account(accountNumber1);

        BigInteger accountNumber2 = new BigInteger("2000000000");
        Account account2 = new Account(accountNumber2);
        return Stream.of(
                arguments(
                        "Request is null",
                        Arrays.asList(275),
                        Arrays.asList(3),
                        Arrays.asList((CreditCardRequest) null),
                        Arrays.asList(new Tuple<CreditCard, Class<? extends Exception>>(null, IllegalArgumentException.class))
                ),
                arguments(
                        "Holder name is null",
                        Arrays.asList(275),
                        Arrays.asList(3),
                        Arrays.asList(new CreditCardRequest(account1, null)),
                        Arrays.asList(new Tuple<CreditCard, Class<? extends Exception>>(null, IllegalArgumentException.class))
                ),
                arguments(
                        "Account is null",
                        Arrays.asList(275),
                        Arrays.asList(3),
                        Arrays.asList(new CreditCardRequest(null, new Name("Abc", "Def"))),
                        Arrays.asList(new Tuple<CreditCard, Class<? extends Exception>>(null, IllegalArgumentException.class))
                ),
                arguments(
                        "New credit card",
                        Arrays.asList(275, 788),
                        Arrays.asList(3, 3),
                        Arrays.asList(
                                new CreditCardRequest(account1, new Name("Abc", "Def")),
                                new CreditCardRequest(account2, new Name("Cc", "Smith"))
                        ),
                        Arrays.asList(
                                new Tuple<CreditCard, Class<? extends Exception>>(
                                        new CreditCard(
                                                new Name("Abc", "Def"),
                                                new BigInteger("1000000000000001"),
                                                LocalDate.of(2024, 7, 20),
                                                375,
                                                CardNetwork.VISA,
                                                "Future Digital",
                                                accountNumber1
                                        ),
                                        null),

                                new Tuple<CreditCard, Class<? extends Exception>>(
                                        new CreditCard(
                                                new Name("Cc", "Smith"),
                                                new BigInteger("1000000000000002"),
                                                LocalDate.of(2024, 7, 20),
                                                888,
                                                CardNetwork.VISA,
                                                "Future Digital",
                                                accountNumber2
                                        ),
                                        null)
                        ))
        );
    }
}
