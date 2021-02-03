package MBSData;

import com.opencsv.bean.CsvBindByName;

public class BaseServicingFeeData {
    @CsvBindByName(column = "agency")
    private String agency;

    @CsvBindByName(column = "base_servicing_fee")
    private String base_servicing_fee;

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getBase_servicing_fee() {
        return base_servicing_fee;
    }

    public void setBase_servicing_fee(String base_servicing_fee) {
        this.base_servicing_fee = base_servicing_fee;
    }
}
