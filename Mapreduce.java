package com.jlh.lambda;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator
 * On 2016/8/12 0012.
 *
 * @description
 */
public class Mapreduce {
    public static void main (String []args){
        final BigDecimal HUNDRED = new BigDecimal("100");
        System.out.println("Stocks priced over $100 are " +
                Tickers.symbols
                        .stream()
                        .filter(symbol -> symbol.length() > 3)
                        .sorted()
                        .collect(Collectors.joining(", ")));

        int [][]a = {{1,2},{3,4}};
        Arrays.stream(a).map(m->Arrays.stream(m)).forEach(m->m.forEach(System.out::println));//二维数组遍历
    }
    static class Tickers {
        public static final List<String> symbols = Arrays.asList(
                "AMD", "HPQ", "IBM", "TXN", "VMW", "XRX", "AAPL", "ADBE",
                "AMZN", "CRAY", "CSCO", "DELL", "GOOG", "INTC", "INTU",
                "MSFT", "ORCL", "TIBX", "VRSN", "YHOO");
    }
}
