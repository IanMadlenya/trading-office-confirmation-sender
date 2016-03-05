package com.trading;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import org.junit.Test;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class PdfConfirmationGenerationIntegrationTest {

    @Test
    public void pdf_confirmation_should_be_easily_generated_from_jrxml_template() throws Exception {
        InputStream resourceAsStream = PdfConfirmationGenerationIntegrationTest.class.getClassLoader().getResourceAsStream("Confirmation.jrxml");

        assert resourceAsStream != null;
        JasperReport jasperReport = JasperCompileManager.compileReport(resourceAsStream);

        byte[] data = JasperRunManager.runReportToPdf(jasperReport, parameters(), new JREmptyDataSource());

        assertThat(data.length).isGreaterThan(0);
        Files.write(Paths.get("Test-Confirmation.pdf"), data);
    }

    private Map<String, Object> parameters() {
        Map<String, Object> map = new HashMap<>();
        map.put("ALLOC_RPT_ID", "1234");
        map.put("TRANS_TYPE", "BUY");
        map.put("INST_ID_TYPE", "SEDOL");
        map.put("INST_ID", "55234");

        map.put("ALLOC_INSTR_NAME", "AMAZON STOCKS");
        map.put("CURRENCY", "USD");
        map.put("EXCHANGE", "NASDAQ");

        map.put("QUANTITY", "10");
        map.put("PRICE", "1234.56");
        map.put("SIDE", "SELL");

        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy", Locale.US);
        ZonedDateTime tradeDate = ZonedDateTime.of(2016, 6, 3, 0, 0, 0, 0, ZoneId.of("GMT"));

        map.put("TRADE_DATE", tradeDate.format(format));

        return map;
    }
}
