package InputData;

import com.opencsv.bean.CsvBindByName;

public class Loan {
    @CsvBindByName(column = "LoanId")
    private String LoanId;

    @CsvBindByName(column = "OriginalBalance")
    private String OriginalBalance;

    @CsvBindByName(column = "CurrentBalance")
    private String CurrentBalance;

    @CsvBindByName(column = "OriginalTerm")
    private String OriginalTerm;

    @CsvBindByName(column = "MortgageDate")
    private String MortgageDate;

    @CsvBindByName(column = "State")
    private String State;

    @CsvBindByName(column = "GrossCoupon")
    private String GrossCoupon;

    @CsvBindByName(column = "AdjustedGrossCoupon")
    private String AdjustedGrossCoupon;

    @CsvBindByName(column = "GuaranteeFee")
    private String GuaranteeFee;

    @CsvBindByName(column = "BaseServicingFee")
    private String BaseServicingFee;

    @CsvBindByName(column = "MaturityDate")
    private String MaturityDate;

    @CsvBindByName(column = "RelocationFlag")
    private String RelocationFlag;

    @CsvBindByName(column = "CooperativeFlag")
    private String CooperativeFlag;

    @CsvBindByName(column = "ReuralHousingFlag")
    private String ReuralHousingFlag;

    @CsvBindByName(column = "GovtOrRualHousingFlag")
    private String GovtOrRualHousingFlag;

    @CsvBindByName(column = "MhaFlag")
    private String MhaFlag;

    @CsvBindByName(column = "FICO")
    private String FICO;

    @CsvBindByName(column = "LtvPct")
    private String LtvPct;

    @CsvBindByName(column = "ConformingLoanAmountFlag")
    private String ConformingLoanAmountFlag;

    @CsvBindByName(column = "LoanPurposeType")
    private String LoanPurposeType;

    @CsvBindByName(column = "Insurer")
    private String Insurer;

    @CsvBindByName(column = "AmortizationTerm")
    private String AmortizationTerm;

    @CsvBindByName(column = "ProcessStatus")
    private String ProcessStatus;

    @CsvBindByName(column = "LPMIFlag")
    private String LPMIFlag;

    @CsvBindByName(column = "PaymentType")
    private String PaymentType;

    @CsvBindByName(column = "FirstPaymentDueDate")
    private String FirstPaymentDueDate;

    @CsvBindByName(column = "PackageTypeNumber")
    private String PackageTypeNumber;

    @CsvBindByName(column = "CorpusCoupon")
    private String CorpusCoupon;

    @CsvBindByName(column = "BuyUpRate")
    private String BuyUpRate;

    @CsvBindByName(column = "BuyUpMults")
    private String BuyUpMults;

    @CsvBindByName(column = "BuyDownRate")
    private String BuyDownRate;

    @CsvBindByName(column = "BuyDownMults")
    private String BuyDownMults;

    @CsvBindByName(column = "ConformingLoanFlag")
    private String ConformingLoanFlag;

    @CsvBindByName(column = "SecuritizeHfiFlag")
    private String SecuritizeHfiFlag;

    @CsvBindByName(column = "FinalPackageFlag")
    private String FinalPackageFlag;

    @CsvBindByName(column = "DeficiencyFlag")
    private String DeficiencyFlag;

    @CsvBindByName(column = "ArmInitialResetDate")
    private String ArmInitialResetDate;

    @CsvBindByName(column = "FundingDate")
    private String FundingDate;

    @CsvBindByName(column = "LockExpirationDate")
    private String LockExpirationDate;

    @CsvBindByName(column = "InitialLockDate")
    private String InitialLockDate;

    @CsvBindByName(column = "PoolSettlementDate")
    private String PoolSettlementDate;

    @CsvBindByName(column = "JumboConformingFlag")
    private String JumboConformingFlag;

    @CsvBindByName(column = "FnmaHighLtvMhaFlag")
    private String FnmaHighLtvMhaFlag;

    @CsvBindByName(column = "FhlmcHighLtvMhaFlag")
    private String FhlmcHighLtvMhaFlag;

    @CsvBindByName(column = "GovernmentOrRualHousingFlag")
    private String GovernmentOrRualHousingFlag;

    public String getLoanId() {
        return LoanId;
    }

    public void setLoanId(String loanId) {
        LoanId = loanId;
    }

    public String getOriginalBalance() {
        return OriginalBalance;
    }

    public void setOriginalBalance(String originalBalance) {
        OriginalBalance = originalBalance;
    }

    public String getCurrentBalance() {
        return CurrentBalance;
    }

    public void setCurrentBalance(String currentBalance) {
        CurrentBalance = currentBalance;
    }

    public String getOriginalTerm() {
        return OriginalTerm;
    }

    public void setOriginalTerm(String originalTerm) {
        OriginalTerm = originalTerm;
    }

    public String getMortgageDate() {
        return MortgageDate;
    }

    public void setMortgageDate(String mortgageDate) {
        MortgageDate = mortgageDate;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getGrossCoupon() {
        return GrossCoupon;
    }

    public void setGrossCoupon(String grossCoupon) {
        GrossCoupon = grossCoupon;
    }

    public String getAdjustedGrossCoupon() {
        return AdjustedGrossCoupon;
    }

    public void setAdjustedGrossCoupon(String adjustedGrossCoupon) {
        AdjustedGrossCoupon = adjustedGrossCoupon;
    }

    public String getGuaranteeFee() {
        return GuaranteeFee;
    }

    public void setGuaranteeFee(String guaranteeFee) {
        GuaranteeFee = guaranteeFee;
    }

    public String getBaseServicingFee() {
        return BaseServicingFee;
    }

    public void setBaseServicingFee(String baseServicingFee) {
        BaseServicingFee = baseServicingFee;
    }

    public String getMaturityDate() {
        return MaturityDate;
    }

    public void setMaturityDate(String maturityDate) {
        MaturityDate = maturityDate;
    }

    public String getRelocationFlag() {
        return RelocationFlag;
    }

    public void setRelocationFlag(String relocationFlag) {
        RelocationFlag = relocationFlag;
    }

    public String getCooperativeFlag() {
        return CooperativeFlag;
    }

    public void setCooperativeFlag(String cooperativeFlag) {
        CooperativeFlag = cooperativeFlag;
    }

    public String getReuralHousingFlag() {
        return ReuralHousingFlag;
    }

    public void setReuralHousingFlag(String reuralHousingFlag) {
        ReuralHousingFlag = reuralHousingFlag;
    }

    public String getGovtOrRualHousingFlag() {
        return GovtOrRualHousingFlag;
    }

    public void setGovtOrRualHousingFlag(String govtOrRualHousingFlag) {
        GovtOrRualHousingFlag = govtOrRualHousingFlag;
    }

    public String getMhaFlag() {
        return MhaFlag;
    }

    public void setMhaFlag(String mhaFlag) {
        MhaFlag = mhaFlag;
    }

    public String getFICO() {
        return FICO;
    }

    public void setFICO(String FICO) {
        this.FICO = FICO;
    }

    public String getLtvPct() {
        return LtvPct;
    }

    public void setLtvPct(String ltvPct) {
        LtvPct = ltvPct;
    }

    public String getConformingLoanAmountFlag() {
        return ConformingLoanAmountFlag;
    }

    public void setConformingLoanAmountFlag(String conformingLoanAmountFlag) {
        ConformingLoanAmountFlag = conformingLoanAmountFlag;
    }

    public String getLoanPurposeType() {
        return LoanPurposeType;
    }

    public void setLoanPurposeType(String loanPurposeType) {
        LoanPurposeType = loanPurposeType;
    }

    public String getInsurer() {
        return Insurer;
    }

    public void setInsurer(String insurer) {
        Insurer = insurer;
    }

    public String getAmortizationTerm() {
        return AmortizationTerm;
    }

    public void setAmortizationTerm(String amortizationTerm) {
        AmortizationTerm = amortizationTerm;
    }

    public String getProcessStatus() {
        return ProcessStatus;
    }

    public void setProcessStatus(String processStatus) {
        ProcessStatus = processStatus;
    }

    public String getLPMIFlag() {
        return LPMIFlag;
    }

    public void setLPMIFlag(String LPMIFlag) {
        this.LPMIFlag = LPMIFlag;
    }

    public String getPaymentType() {
        return PaymentType;
    }

    public void setPaymentType(String paymentType) {
        PaymentType = paymentType;
    }

    public String getFirstPaymentDueDate() {
        return FirstPaymentDueDate;
    }

    public void setFirstPaymentDueDate(String firstPaymentDueDate) {
        FirstPaymentDueDate = firstPaymentDueDate;
    }

    public String getPackageTypeNumber() {
        return PackageTypeNumber;
    }

    public void setPackageTypeNumber(String packageTypeNumber) {
        PackageTypeNumber = packageTypeNumber;
    }

    public String getCorpusCoupon() {
        return CorpusCoupon;
    }

    public void setCorpusCoupon(String corpusCoupon) {
        CorpusCoupon = corpusCoupon;
    }

    public String getBuyUpRate() {
        return BuyUpRate;
    }

    public void setBuyUpRate(String buyUpRate) {
        BuyUpRate = buyUpRate;
    }

    public String getBuyUpMults() {
        return BuyUpMults;
    }

    public void setBuyUpMults(String buyUpMults) {
        BuyUpMults = buyUpMults;
    }

    public String getBuyDownRate() {
        return BuyDownRate;
    }

    public void setBuyDownRate(String buyDownRate) {
        BuyDownRate = buyDownRate;
    }

    public String getBuyDownMults() {
        return BuyDownMults;
    }

    public void setBuyDownMults(String buyDownMults) {
        BuyDownMults = buyDownMults;
    }

    public String getConformingLoanFlag() {
        return ConformingLoanFlag;
    }

    public void setConformingLoanFlag(String conformingLoanFlag) {
        ConformingLoanFlag = conformingLoanFlag;
    }

    public String getSecuritizeHfiFlag() {
        return SecuritizeHfiFlag;
    }

    public void setSecuritizeHfiFlag(String securitizeHfiFlag) {
        SecuritizeHfiFlag = securitizeHfiFlag;
    }

    public String getFinalPackageFlag() {
        return FinalPackageFlag;
    }

    public void setFinalPackageFlag(String finalPackageFlag) {
        FinalPackageFlag = finalPackageFlag;
    }

    public String getDeficiencyFlag() {
        return DeficiencyFlag;
    }

    public void setDeficiencyFlag(String deficiencyFlag) {
        DeficiencyFlag = deficiencyFlag;
    }

    public String getArmInitialResetDate() {
        return ArmInitialResetDate;
    }

    public void setArmInitialResetDate(String armInitialResetDate) {
        ArmInitialResetDate = armInitialResetDate;
    }

    public String getFundingDate() {
        return FundingDate;
    }

    public void setFundingDate(String fundingDate) {
        FundingDate = fundingDate;
    }

    public String getLockExpirationDate() {
        return LockExpirationDate;
    }

    public void setLockExpirationDate(String lockExpirationDate) {
        LockExpirationDate = lockExpirationDate;
    }

    public String getInitialLockDate() {
        return InitialLockDate;
    }

    public void setInitialLockDate(String initialLockDate) {
        InitialLockDate = initialLockDate;
    }

    public String getPoolSettlementDate() {
        return PoolSettlementDate;
    }

    public void setPoolSettlementDate(String poolSettlementDate) {
        PoolSettlementDate = poolSettlementDate;
    }

    public String getJumboConformingFlag() {
        return JumboConformingFlag;
    }

    public void setJumboConformingFlag(String jumboConformingFlag) {
        JumboConformingFlag = jumboConformingFlag;
    }

    public String getFnmaHighLtvMhaFlag() {
        return FnmaHighLtvMhaFlag;
    }

    public void setFnmaHighLtvMhaFlag(String fnmaHighLtvMhaFlag) {
        FnmaHighLtvMhaFlag = fnmaHighLtvMhaFlag;
    }

    public String getFhlmcHighLtvMhaFlag() {
        return FhlmcHighLtvMhaFlag;
    }

    public void setFhlmcHighLtvMhaFlag(String fhlmcHighLtvMhaFlag) {
        FhlmcHighLtvMhaFlag = fhlmcHighLtvMhaFlag;
    }

    public String getGovernmentOrRualHousingFlag() {
        return GovernmentOrRualHousingFlag;
    }

    public void setGovernmentOrRualHousingFlag(String governmentOrRualHousingFlag) {
        GovernmentOrRualHousingFlag = governmentOrRualHousingFlag;
    }
}
