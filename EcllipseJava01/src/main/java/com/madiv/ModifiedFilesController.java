package com.madiv;

import java.util.ArrayList;
import java.util.List;

public class ModifiedFilesController {
    
    private String soantaGitWorkspacePath = "E:/User/Maddy/P12-Sonata/GitWorkspace/base/sonata"; 
    private String soantaAPIGitWorkspacePath = "E:/User/Maddy/P12-Sonata/GitWorkspace/base/sonata-api"; 
    
    private String dirPath = soantaGitWorkspacePath;
    
    private String backupTarget = "E:/User/Maddy/P12-Sonata/BaseBranch/TaskWorkedUpon/02. Enhancements/36-UKPS-1599-BASE-170008/FilesModified/After/02.BackUp-June27,2018"; 
    
    private String[] gitStatusStrings = {
            "sonata-dao/src/bravura/sonata/dao/contribpayments/FaultCodeDaoContribpayments.java",
            "sonata-dao/src/bravura/sonata/dao/contribpayments/all-messages.properties",
            "sonata-dao/src/bravura/sonata/dao/contribpayments/member/process/apply/tactical/RegistrationValidatorDAO.java",
            "sonata-dao/src/bravura/sonata/dao/contribpayments/member/process/load/PostMemberRegUploadProcess.java",
            "sonata-dao/src/bravura/sonata/dao/contribpayments/member/process/load/PostMemberRegUploadProcessDAO.java",
            "sonata-dao/src/bravura/sonata/dao/contribpayments/member/process/validation/ValidateSuperStreamMessageDAO.java",
            "sonata-dao/src/bravura/sonata/dao/contribpayments/member/process/validation/validator/CategoryValidator.java",
            "sonata-dao/src/bravura/sonata/dao/contribpayments/member/process/validation/validator/EmploymentValidator.java",
            "sonata-dao/src/bravura/sonata/dao/contribpayments/member/process/validation/validator/MemberRuleMappingValidator.java",
            "sonata-dao/src/bravura/sonata/dao/contribpayments/member/process/validation/validator/MemberValidator.java",
            "sonata-dao/src/bravura/sonata/dao/contribpayments/member/process/validation/validator/SalaryAndServiceValidator.java",
            "sonata-dao/src/bravura/sonata/dao/contribpayments/validator/ContribScheduleValidatorRule.java",
            "sonata-dao/src/bravura/sonata/dao/contribpayments/validator/ContribScheduleValidatorStepManager.java",
            "sonata-dao/src/bravura/sonata/dao/contribpayments/validator/ValidateAclkAdaContribStatus.java",
            "sonata-dao/src/bravura/sonata/dao/contribpayments/validator/ValidateAvailableContribution.java",
            "sonata-dao/src/bravura/sonata/dao/contribpayments/validator/ValidateContributionTypeBalance.java",
            "sonata-dao/src/bravura/sonata/dao/contribpayments/validator/ValidateEmployerAcctType.java",
            "sonata-dao/src/bravura/sonata/dao/contribpayments/validator/ValidateHeaderAmounts.java",
            "sonata-dao/src/bravura/sonata/dao/contribpayments/validator/ValidateHeaderApplRule.java",
            "sonata-dao/src/bravura/sonata/dao/contribpayments/validator/ValidateHeaderDueDate.java",
            "sonata-dao/src/bravura/sonata/dao/contribpayments/validator/ValidateHeaderStatus.java",
            "sonata-dao/src/bravura/sonata/dao/contribpayments/validator/ValidateHeaderZeroAmount.java",
            "sonata-dao/src/bravura/sonata/dao/contribpayments/validator/ValidateMatchingRules.java",
            "sonata-dao/src/bravura/sonata/dao/contribpayments/validator/ValidateMemberAcctType.java",
            "sonata-dao/src/bravura/sonata/dao/contribpayments/validator/ValidateMemberCommenceDt.java",
            "sonata-dao/src/bravura/sonata/dao/contribpayments/validator/ValidateMemberZeroAmount.java",
            "sonata-dao/src/bravura/sonata/dao/contribpayments/validator/ValidatePriorUnpricedTrans.java",
            "sonata-dao/src/bravura/sonata/dao/product/behaviour/CMStatDataScheme.java",
            "sonata-dao/src/bravura/sonata/dao/product/behaviour/CMStatDataSchemeDAO.java",
            "sonata-dao/src/bravura/sonata/dao/scheme/FaultCodeDaoScheme.java",
            "sonata-dao/src/bravura/sonata/dao/scheme/all-messages.properties",
            "sonata-dao/src/bravura/sonata/dao/scheme/setup/CMStatDataSchemeWithRulesDAO.java",
            "sonata-dao/src/bravura/sonata/dao/scheme/setup/CMStatDataSchemeWithRulesDTO.java",
            "sonata-dao/src/bravura/sonata/dao/scheme/setup/CMStatDataSchmPaySchRls.java",
            "sonata-dao/src/bravura/sonata/dao/scheme/setup/CMStatDataSchmPaySchRlsDAO.java",
            "sonata-dao/src/bravura/sonata/dao/scheme/setup/contribution/ContributionMemberDetail.java",
            "sonata-dao/src/bravura/sonata/dao/scheme/setup/contribution/ContributionMemberDetailDAO.java",
            "sonata-dao/src/bravura/sonata/dao/scheme/setup/contribution/ContributionMemberDetailDTO.java",
            "sonata-dao/src/bravura/sonata/dao/scheme/setup/member/MemberRegistrationMemberDetail.java",
            "sonata-dao/src/bravura/sonata/dao/scheme/setup/member/MemberRegistrationMemberDetailDAO.java",
            "sonata-dao/src/bravura/sonata/dao/scheme/setup/member/MemberRegistrationMemberDetailDTO.java",
            "sonata-datamodel/datamodel/tables/common/tscm_schedule_member.xml",
            "sonata-datamodel/datamodel/tables/common/tscm_scheme.xml",
            "sonata-datamodel/datamodel/tables/common/tscm_scheme_pymt_schd_hdr.xml",
            "sonata-pbx/src/bravura/sonata/contribpayments/setup/contribution/ContributionMemberDetailsDO.java",
            "sonata-pbx/src/bravura/sonata/contribpayments/setup/contribution/ContributionV.java",
            "sonata-pbx/src/bravura/sonata/contribpayments/setup/contribution/contributionmemberdetailsdo.properties",
            "sonata-pbx/src/bravura/sonata/contribpayments/setup/member/RegistrationMemberDetailsDO.java",
            "sonata-pbx/src/bravura/sonata/contribpayments/setup/member/RegistrationV.java",
            "sonata-pbx/src/bravura/sonata/contribpayments/setup/member/registrationmemberdetailsdo.properties",
            "sonata-pbx/src/bravura/sonata/pbvm/authentication/SonataLoginModuleWeb.java",
            "sonata-pbx/src/bravura/sonata/product/behaviour/SchemelevelDO.java",
            "sonata-pbx/src/bravura/sonata/product/behaviour/schemeleveldo.properties",
            "sonata-rda/src/rda/bravura/sonata/contribpayments/setup/contribution/ContributionmemberdetailsDF.java",
            "sonata-rda/src/rda/bravura/sonata/contribpayments/setup/member/RegistrationmemberdetailsDF.java",
            "sonata-rda/src/rda/bravura/sonata/product/behaviour/SchemelevelDF.java",
            "sonata-dao/src/bravura/sonata/dao/contribpayments/ScheduleValidationRuleSet.java",
            "sonata-dao/src/bravura/sonata/dao/contribpayments/validator/ValidateAutoEnrolmentDetails.java",
            "sonata-dao/src/bravura/sonata/dao/contribpayments/validator/ValidateContributionAmountOrRate.java",
            "sonata-dao/src/bravura/sonata/dao/contribpayments/validator/ValidateEarningDetails.java",
            "sonata-dao/src/bravura/sonata/dao/contribpayments/validator/ValidateNationalInsuranceNumber.java",
            "sonata-dao/src/bravura/sonata/dao/scheme/setup/CMMaintLoadContribFileUK.java",
            "sonata-dao/src/bravura/sonata/dao/scheme/setup/CMMaintLoadContribFileUKDAO.java",
            "sonata-dao/src/bravura/sonata/dao/scheme/setup/CMMaintPremitiveContribLoadValidatorUK.java",
            "sonata-dao/src/bravura/sonata/dao/scheme/setup/ContributionRecordDefinition.java",
            "sonata-dao/src/bravura/sonata/dao/scheme/setup/UKLoadContribErrorLogs.java",
            "sonata-dao/src/bravura/sonata/dao/scheme/setup/UKLoadContributionVO.java",
            "sonata-datamodel/datamodel/tables/common/tscm_schedule_mbr_earng_dtl.xml",
            "sonata-datamodel/datamodel/tables/common/work/wkcm_contrib_data_uk.xml",
            "sonata-datamodel/datamodel/tables/common/work/wkcm_contrib_header_uk.xml",
            "sonata-datamodel/datamodel/tables/common/work/wkcm_contrib_member_data_uk.xml"
    };
    
    
    private List<String> finalFilesToBackup = null;
    
    
    
    
    public static void main(String[] args) {
        ModifiedFilesController controller = new ModifiedFilesController();
//        controller.backup();
        controller.generateDirCommand();
    }

    private void generateDirCommand() {
        
    }

    private void backup() {
        finalFilesToBackup = cleanInputList();
        
        
    }

    private List<String> cleanInputList() {
        finalFilesToBackup = new ArrayList<String>();
        
        
        
        
        
        
        
        return finalFilesToBackup;
    }
}
