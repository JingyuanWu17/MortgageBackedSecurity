package InputData;

import com.opencsv.bean.CsvBindByName;

public class BaseServicingMultsData {
    @CsvBindByName(column = "agency")
    private String agency;

    @CsvBindByName(column = "pool_id")
    private String pool_id;

    @CsvBindByName(column = "issue_date")
    private String issue_date;

    @CsvBindByName(column = "base_mults")
    private String base_mults;

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getPool_id() {
        return pool_id;
    }

    public void setPool_id(String pool_id) {
        this.pool_id = pool_id;
    }

    public String getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(String issue_date) {
        this.issue_date = issue_date;
    }

    public String getBase_mults() {
        return base_mults;
    }

    public void setBase_mults(String base_mults) {
        this.base_mults = base_mults;
    }
}
