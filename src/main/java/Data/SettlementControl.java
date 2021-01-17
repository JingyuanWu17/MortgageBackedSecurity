package Data;

import com.opencsv.bean.CsvBindByName;

public class SettlementControl {
    @CsvBindByName(column = "pool_family_id")
    private String pool_family_id;

    @CsvBindByName(column = "loan_status")
    private String loan_status;

    @CsvBindByName(column = "first_issue_pre_bma")
    private String first_issue_pre_bma;

    @CsvBindByName(column = "first_issue_post_bma")
    private String first_issue_post_bma;

    @CsvBindByName(column = "second_issue")
    private String second_issue;

    @CsvBindByName(column = "third_issue")
    private String third_issue;

    public String getPool_family_id() {
        return pool_family_id;
    }

    public void setPool_family_id(String pool_family_id) {
        this.pool_family_id = pool_family_id;
    }

    public String getLoan_status() {
        return loan_status;
    }

    public void setLoan_status(String loan_status) {
        this.loan_status = loan_status;
    }

    public String getFirst_issue_pre_bma() {
        return first_issue_pre_bma;
    }

    public void setFirst_issue_pre_bma(String first_issue_pre_bma) {
        this.first_issue_pre_bma = first_issue_pre_bma;
    }

    public String getFirst_issue_post_bma() {
        return first_issue_post_bma;
    }

    public void setFirst_issue_post_bma(String first_issue_post_bma) {
        this.first_issue_post_bma = first_issue_post_bma;
    }

    public String getSecond_issue() {
        return second_issue;
    }

    public void setSecond_issue(String second_issue) {
        this.second_issue = second_issue;
    }

    public String getThird_issue() {
        return third_issue;
    }

    public void setThird_issue(String third_issue) {
        this.third_issue = third_issue;
    }
}
