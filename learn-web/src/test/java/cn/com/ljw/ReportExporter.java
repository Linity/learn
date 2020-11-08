package cn.com.ljw;

/**
 * @author Steph_Lin
 * @date 2020/9/16
 */
public class ReportExporter extends AlarmExporter implements IExporter {

    @Override
    public void exportPdf() {
        System.out.println("pdf");
    }

}
