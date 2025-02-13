package com.biblio.infrastructure.adapter.service;

import lombok.extern.log4j.Log4j2;
import net.sf.jasperreports.engine.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

@Service
@Log4j2
public class ReportService {

    private final DataSource dataSource; // Inyectamos la conexión a la BD

    public ReportService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public byte[] generateReport(String reportPath) throws IOException, JRException {

        InputStream reportStream = new ClassPathResource(reportPath).getInputStream();
        try{
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, null, dataSource.getConnection());
            // Exportar a PDF
            return JasperExportManager.exportReportToPdf(jasperPrint);

        } catch (SQLException e) {
            log.error("error {0}",e);
        }
        return new byte[0];
    }
}
