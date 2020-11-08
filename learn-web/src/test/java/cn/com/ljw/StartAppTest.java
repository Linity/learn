package cn.com.ljw;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class StartAppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }


    public static void main(String[] args) {
        ReportExporter reportExporter = new ReportExporter();
        reportExporter.exportPdf();
        reportExporter.exportCsv();
    }


}
