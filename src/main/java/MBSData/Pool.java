package MBSData;

import com.opencsv.bean.CsvBindByName;

public class Pool {
    @CsvBindByName(column = "PackageTypeNumber")
    private String PackageTypeNumber;

    @CsvBindByName(column = "LoanPricerId")
    private String LoanPricerId;

    @CsvBindByName(column = "XcbFlag")
    private String XcbFlag;

    @CsvBindByName(column = "OriginalTerm")
    private String OriginalTerm;

    @CsvBindByName(column = "AmortizationTermRange")
    private String AmortizationTermRange;

    @CsvBindByName(column = "PaymentType")
    private String PaymentType;

    @CsvBindByName(column = "InsurerList")
    private String InsurerList;

    @CsvBindByName(column = "ConformingLoanFlag")
    private String ConformingLoanFlag;

    @CsvBindByName(column = "GovernmentOrRualHousingFlag")
    private String GovernmentOrRualHousingFlag;

    @CsvBindByName(column = "OriginalLtvRange")
    private String OriginalLtvRange;

    @CsvBindByName(column = "OriginalBalanceRange")
    private String OriginalBalanceRange;

    @CsvBindByName(column = "StreamlineVALowFicoFlag")
    private String StreamlineVALowFicoFlag;

    @CsvBindByName(column = "RelocationFlag")
    private String RelocationFlag;

    @CsvBindByName(column = "PropertyTypeList")
    private String PropertyTypeList;

    @CsvBindByName(column = "OriginalFICORange")
    private String OriginalFICORange;

    @CsvBindByName(column = "LoanPurposeTypeList")
    private String LoanPurposeTypeList;

    @CsvBindByName(column = "VerifiedFicoScoreFlag")
    private String VerifiedFicoScoreFlag;

    @CsvBindByName(column = "InitialPeriodCap")
    private String InitialPeriodCap;

    @CsvBindByName(column = "SubsequentPeriodCap")
    private String SubsequentPeriodCap;

    @CsvBindByName(column = "LifeCap")
    private String LifeCap;

    @CsvBindByName(column = "MonthsToInitialResetRange")
    private String MonthsToInitialResetRange;

    public String getPackageTypeNumber() {
        return PackageTypeNumber;
    }

    public void setPackageTypeNumber(String packageTypeNumber) {
        PackageTypeNumber = packageTypeNumber;
    }

    public String getLoanPricerId() {
        return LoanPricerId;
    }

    public void setLoanPricerId(String loanPricerId) {
        LoanPricerId = loanPricerId;
    }

    public String getXcbFlag() {
        return XcbFlag;
    }

    public void setXcbFlag(String xcbFlag) {
        XcbFlag = xcbFlag;
    }

    public String getOriginalTerm() {
        return OriginalTerm;
    }

    public void setOriginalTerm(String originalTerm) {
        OriginalTerm = originalTerm;
    }

    public String getAmortizationTermRange() {
        return AmortizationTermRange;
    }

    public void setAmortizationTermRange(String amortizationTermRange) {
        AmortizationTermRange = amortizationTermRange;
    }

    public String getPaymentType() {
        return PaymentType;
    }

    public void setPaymentType(String paymentType) {
        PaymentType = paymentType;
    }

    public String getInsurerList() {
        return InsurerList;
    }

    public void setInsurerList(String insurerList) {
        InsurerList = insurerList;
    }

    public String getConformingLoanFlag() {
        return ConformingLoanFlag;
    }

    public void setConformingLoanFlag(String conformingLoanFlag) {
        ConformingLoanFlag = conformingLoanFlag;
    }

    public String getGovernmentOrRualHousingFlag() {
        return GovernmentOrRualHousingFlag;
    }

    public void setGovernmentOrRualHousingFlag(String governmentOrRualHousingFlag) {
        GovernmentOrRualHousingFlag = governmentOrRualHousingFlag;
    }

    public String getOriginalLtvRange() {
        return OriginalLtvRange;
    }

    public void setOriginalLtvRange(String originalLtvRange) {
        OriginalLtvRange = originalLtvRange;
    }

    public String getOriginalBalanceRange() {
        return OriginalBalanceRange;
    }

    public void setOriginalBalanceRange(String originalBalanceRange) {
        OriginalBalanceRange = originalBalanceRange;
    }

    public String getStreamlineVALowFicoFlag() {
        return StreamlineVALowFicoFlag;
    }

    public void setStreamlineVALowFicoFlag(String streamlineVALowFicoFlag) {
        StreamlineVALowFicoFlag = streamlineVALowFicoFlag;
    }

    public String getRelocationFlag() {
        return RelocationFlag;
    }

    public void setRelocationFlag(String relocationFlag) {
        RelocationFlag = relocationFlag;
    }

    public String getPropertyTypeList() {
        return PropertyTypeList;
    }

    public void setPropertyTypeList(String propertyTypeList) {
        PropertyTypeList = propertyTypeList;
    }

    public String getOriginalFICORange() {
        return OriginalFICORange;
    }

    public void setOriginalFICORange(String originalFICORange) {
        OriginalFICORange = originalFICORange;
    }

    public String getLoanPurposeTypeList() {
        return LoanPurposeTypeList;
    }

    public void setLoanPurposeTypeList(String loanPurposeTypeList) {
        LoanPurposeTypeList = loanPurposeTypeList;
    }

    public String getVerifiedFicoScoreFlag() {
        return VerifiedFicoScoreFlag;
    }

    public void setVerifiedFicoScoreFlag(String verifiedFicoScoreFlag) {
        VerifiedFicoScoreFlag = verifiedFicoScoreFlag;
    }

    public String getInitialPeriodCap() {
        return InitialPeriodCap;
    }

    public void setInitialPeriodCap(String initialPeriodCap) {
        InitialPeriodCap = initialPeriodCap;
    }

    public String getSubsequentPeriodCap() {
        return SubsequentPeriodCap;
    }

    public void setSubsequentPeriodCap(String subsequentPeriodCap) {
        SubsequentPeriodCap = subsequentPeriodCap;
    }

    public String getLifeCap() {
        return LifeCap;
    }

    public void setLifeCap(String lifeCap) {
        LifeCap = lifeCap;
    }

    public String getMonthsToInitialResetRange() {
        return MonthsToInitialResetRange;
    }

    public void setMonthsToInitialResetRange(String monthsToInitialResetRange) {
        MonthsToInitialResetRange = monthsToInitialResetRange;
    }
}