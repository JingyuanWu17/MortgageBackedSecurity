package MBSData;

import com.opencsv.bean.CsvBindByName;

public class BuyUpDownData {
    @CsvBindByName(column = "agency")
    private String agency;

    @CsvBindByName(column = "issue_month")
    private String issue_month;

    @CsvBindByName(column = "pool_id")
    private String pool_id;

    @CsvBindByName(column = "lpmi_flag")
    private String lpmi_flag;

    @CsvBindByName(column = "note_rate_min")
    private String note_rate_min;

    @CsvBindByName(column = "note_rate_max")
    private String note_rate_max;

    @CsvBindByName(column = "remaining_term_min")
    private String remaining_term_min;

    @CsvBindByName(column = "remaining_term_max")
    private String remaining_term_max;

    @CsvBindByName(column = "buy_up_mults")
    private String buy_up_mults;

    @CsvBindByName(column = "buy_down_mults")
    private String buy_down_mults;

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getIssue_month() {
        return issue_month;
    }

    public void setIssue_month(String issue_month) {
        this.issue_month = issue_month;
    }

    public String getPool_id() {
        return pool_id;
    }

    public void setPool_id(String pool_id) {
        this.pool_id = pool_id;
    }

    public String getLpmi_flag() {
        return lpmi_flag;
    }

    public void setLpmi_flag(String lpmi_flag) {
        this.lpmi_flag = lpmi_flag;
    }

    public String getNote_rate_min() {
        return note_rate_min;
    }

    public void setNote_rate_min(String note_rate_min) {
        this.note_rate_min = note_rate_min;
    }

    public String getNote_rate_max() {
        return note_rate_max;
    }

    public void setNote_rate_max(String note_rate_max) {
        this.note_rate_max = note_rate_max;
    }

    public String getRemaining_term_min() {
        return remaining_term_min;
    }

    public void setRemaining_term_min(String remaining_term_min) {
        this.remaining_term_min = remaining_term_min;
    }

    public String getRemaining_term_max() {
        return remaining_term_max;
    }

    public void setRemaining_term_max(String remaining_term_max) {
        this.remaining_term_max = remaining_term_max;
    }

    public String getBuy_up_mults() {
        return buy_up_mults;
    }

    public void setBuy_up_mults(String buy_up_mults) {
        this.buy_up_mults = buy_up_mults;
    }

    public String getBuy_down_mults() {
        return buy_down_mults;
    }

    public void setBuy_down_mults(String buy_down_mults) {
        this.buy_down_mults = buy_down_mults;
    }
}
