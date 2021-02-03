package MBSData;

import com.opencsv.bean.CsvBindByName;

public class GuaranteeFeeData {
    @CsvBindByName(column = "agency")
    private String agency;

    @CsvBindByName(column = "guarantee_fee")
    private String guarantee_fee;

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getGurantee_fee() {
        return guarantee_fee;
    }

    public void setGuarantee_fee(String gurantee_fee) {
        this.guarantee_fee = gurantee_fee;
    }
}
