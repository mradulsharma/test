package com.madiv.brv;

import static com.madiv.generic.util.StringUtil.isEmpty;

import java.util.ArrayList;
import java.util.List;

public class TypeDefinitionCreator {
    public static void main(String[] args) {
        TypeDefinitionCreator typeDefinitionCreator = new TypeDefinitionCreator();
        typeDefinitionCreator.generateXMLFile();
    }

    private void generateXMLFile() {
        StepManager<Processor> stepMngr = new StepManager();
        
        
        //Add TypeDefinition Starting node
        stepMngr.addStep(new XMLStarter());
        
        
        //Add TypeDefinition Attributes
        stepMngr.addStep(new XMLAttributes());
        
        //Add TypeDefinition Nodes
        stepMngr.addStep(new XMLNode());
        
        //Add TypeDefinition Ending node
        stepMngr.addStep(new XMLEnd());
        
        
        
        //Print
        stepMngr.execute();
        
        
//        for (String line : RawFileContents.LINES){
//            System.out.println(line);
//        }
    }

}


interface Processor{
    public void process();
} 

abstract class LinePrinter{
    private String[] LINES = null;
    public void printLines(){
        for(String line : LINES)System.out.println(line);
    }
    
    public void setLINES(String[] lINES) {
        LINES = lINES;
    }
}


class XMLStarter extends LinePrinter implements Processor{
    private String[] LINES = {
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>",
            "<TypeModel>",
            "   <TypeDefinition>",
            "       <physicalName>ContributionScheduleValidationRule</physicalName>",
            "       <key>CSVR</key>",
            "       <description>Contribution Schedule Validation Rule</description>",
            "       <canAppend>false</canAppend>"
    };

    @Override
    public void process() {
        super.setLINES(LINES);
        super.printLines();
    }
    
}


class XMLAttributes extends LinePrinter implements Processor{
    private String[] LINES = {
            "       <attribute>",
            "           <key>CTCD</key>",
            "           <physicalName>CTCD</physicalName>",
            "           <description>Country code, this validation applicable for</description>",
            "           <canOverride>true</canOverride>",
            "           <dataType>text</dataType>",
            "           <legacyCodeTag/>",
            "       </attribute>",
            "       <attribute>",
            "           <key>RLTY</key>",
            "           <physicalName>RLTY</physicalName>",
            "           <description>Rule type (Warning/Error)</description>",
            "           <canOverride>true</canOverride>",
            "           <dataType>text</dataType>",
            "           <legacyCodeTag/>",
            "       </attribute>"
    };

    @Override
    public void process() {
        super.setLINES(LINES);
        super.printLines();
    }
    
}



class XMLEnd extends LinePrinter implements Processor{
    private String[] LINES = {
            "   </TypeDefinition>",
            "</TypeModel>"
    };

    @Override
    public void process() {
        super.setLINES(LINES);
        super.printLines();
    }
    
}




class XMLNode extends LinePrinter implements Processor{
    private enum AttributeType{CTCD,RLTY};

    private List<String> LINES = new ArrayList<String>();
    
    
    private String[][] codeTypeDetials = {
            {"H001","Status Applied","Checks if contribution Schedule has already been applied.","","Error"},
            {"H002","Due date","Checks if Payment Due Date is set for selected Scheme Payment Schedule.","","Error"},
            {"H003","Totl Cntrb Amt","Checks if Total Contribution or Total Adjustment amount is provided or not.","","Error"},
            {"H004","Pymt schdl enbl","Checks if Payment Schedule functionality is enabled for Scheme.","","Error"},
            {"H005","Zero Amt","Checks if this is manual schedule and the total of all contributions is zero then raise a work note","","Warning"},
            {"H006","Mbr Mchng Rule","Checks if member matching rulese exits or not.","","Error"},
            {"H007","Vld Acct Typ","Checks if employer account type having valid account type (defined code type WMAT > EMPR)","","Error"},
            {"M001","Vld Slry Typ","Checks if salary type supplied is valid Sonata Salary Type","","Error"},
            {"M002","Slry Effct Dt","Checks if salary does not have an effective date","","Error"},
            {"M003","SlryTyp EffctDt","Checks if supplied salary type maps to matched salary type which has been duplicated for effective start date.","","Error"},
            {"M004","SlryTyp MltEfDt","Checks if supplied salary type maps to matched salary type which has been supplied for multiple effective dates.","","Warning"},
            {"M005","SlryTypAlrdyRcd","Checks if supplied salary type maps to matched salary type which has been recorded already for effective start date.","","Error"},
            {"M006","Vld WrkStEffDt","Checks if Work Status Effective Date is supplied or not. (Employment status effective date is null and service fraction is not null)","","Error"},
            {"M007","CtgryUndrSchm","Checks if supplied category can be matched to a category under Scheme.","","Info"},
            {"M008","DfltPECtgry","If supplied category is not matched, then checks for default employer/PE category.","","Error"},
            {"M009","NoCtgryFound","If not category found for member, raise validation error.","","Error"},
            {"M010","TFN InUse","Checks if supplied TFN is already used or not.","AU","Error"},
            {"M011","CrtUpdtRlSet","Checks if create/update rule specified based on combination of employer id and category id.","","Error"},
            {"M012","CnclMbrReg","Checks if member create rule code is configued to not allowed (SCMC -> DMRC). Updates member registration status to cancelled.","","Error"},
            {"M013","NonPrtcpEmplyr","Validates if employer is non participating, (account can't be created).","","Error"},
            {"M014","EmplymtEndDt","Checks if Supplied employment end date is less than the existing employment start date.","","Error"},
            {"M015","EffctVsActCmnDt","Checks if effective date is after account commencement date.","","Error"},
            {"M016","ChekPnsAcct","Checks if account is member account (not pension), and status is active.","","Error"},
            {"M017","HasSffctFunds","Checks if contribution type has sufficient funds","","Error"},
            {"M018","CntrbTypForMbr","Checks if contribution type given is available for account or not.","","Error"},
            {"M019","NegCntrbForMbr","Checks set of validation, related to account balance is not sufficient for negative contribution.","","Error"},
            {"M020","MbrADA status","Checks if contribution type can be allocated to member's account based of ADA status.","","Error"},
            {"M021","ZroCntrbForMbr","Checks if contribution for account is zero (no transaction will be created)","","Error"},
            {"M022","NonConcsnlCntrb","Checks total contribution amount and non concesional limit for member's account.","","Error"},
            {"M023","BckDtdLmtAuCnt","Checks if contribution is more than specified backdated limit. (Only for specific AU client)","AU","Error"},
            {"M024","ActvEmplymntChk","Updates existing active employment if required, else if does not exists then create one.","","Warning"},
            {"M025","OthrCntrbDtl","Superstream contribution member other details provided in contrib reecord, validates special condition on contribution type.  (Only for specific AU client)","AU","Error"},
            {"M026","GCTAR UnprcTxn","For GCTAR message type, checks if there is any existing un priced transaction available.","AU","Error"},
            {"M027","Vld NINO","Validates if NINO supplied is in format CCNNNNNNC (C=Char, N=Numeric)","UK","Error"},
            {"M028","AE ValidtEfftDt","Checks dependency of fields between Auto-enrolment-data-effective-date and combination of Auto-enrolment-eligibility/Auto-enrolment-status","UK","Error"},
            {"M029","AE RulOrRate","Check if Contribution Amount and Contribution Rule Rate are mutually exclusive or not.","UK","Error"},
            {"C001","Validt Erng Dtl","Checks 4 earning related validations based on combination of gross earning amount, gross pensionable earning amount ....and.... pay period start/end date.","UK","Error"}
    };
    
    private String[] codeLine = {
            "       <Code>",
            "           <key>%1$s</key>",
            "           <physicalName>%1$s</physicalName>",
            "           <shortDescription>%1$s</shortDescription>",
            "           <longDescription>%1$s</longDescription>",
            "           <display>true</display>",
            "           <bitmap/>",
            "       </Code>"
    };
    
    private String[] attributeLines = {
            "           <attributeValue>",
            "               <attributeKey>%1$s</attributeKey>",
            "               <value>",
            "                   <TextValue>%1$s</TextValue>",
            "               </value>",
            "           </attributeValue>",
    };
    

    @Override
    public void process() {
        populateLines();
        
        String[] xmlCodes = new String[LINES.size()];
        xmlCodes = LINES.toArray(xmlCodes);
        
        super.setLINES(xmlCodes);
        super.printLines();
    }


    private void populateLines() {
        for(int i=0; i< codeTypeDetials.length; i++){
            LINES.add(codeLine[0]);
            LINES.add(String.format(codeLine[1], codeTypeDetials[i][0]));
            LINES.add(String.format(codeLine[2], codeTypeDetials[i][0]));
            LINES.add(String.format(codeLine[3], codeTypeDetials[i][1]));
            LINES.add(String.format(codeLine[4], codeTypeDetials[i][2]));
            LINES.add(codeLine[5]);
            LINES.add(codeLine[6]);
         
            if(!isEmpty(codeTypeDetials[i][3])) populateAttributes(AttributeType.CTCD, codeTypeDetials[i][3]);
            if(!isEmpty(codeTypeDetials[i][4])) populateAttributes(AttributeType.RLTY, codeTypeDetials[i][4]);
            
            LINES.add(codeLine[7]);
            
        }
    }


    private void populateAttributes(AttributeType ctcd, String codeTypeValue) {
        LINES.add(attributeLines[0]);
        LINES.add(String.format(attributeLines[1], ctcd));
        LINES.add(attributeLines[2]);
        LINES.add(String.format(attributeLines[3], codeTypeValue));
        LINES.add(attributeLines[4]);
        LINES.add(attributeLines[5]);
        
    }
    
}



class StepManager<T extends Processor>{
    private List<T> steps = new ArrayList<>();
    public void addStep(T step){
        steps.add(step);
    }
    public void execute(){
        for(T step : steps){
            step.process();
        }
    }
}




class RawFileContents{
    public static String[] LINES = {
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>",
            "<TypeModel>",
            "   <TypeDefinition>",
            "       <physicalName>ContributionScheduleValidationRule</physicalName>",
            "       <key>CSVR</key>",
            "       <description>Contribution Schedule Validation Rule</description>",
            "       <canAppend>false</canAppend>",
            "       <attribute>",
            "           <key>CTCD</key>",
            "           <physicalName>CTCD</physicalName>",
            "           <description>Country code, this validation applicable for</description>",
            "           <canOverride>true</canOverride>",
            "           <dataType>text</dataType>",
            "           <legacyCodeTag/>",
            "       </attribute>",
            "       <attribute>",
            "           <key>RLTY</key>",
            "           <physicalName>RLTY</physicalName>",
            "           <description>Rule type (Warning/Error)</description>",
            "           <canOverride>true</canOverride>",
            "           <dataType>text</dataType>",
            "           <legacyCodeTag/>",
            "       </attribute>",
            "       <Code>",
            "           <key>H001</key>",
            "           <physicalName>H001</physicalName>",
            "           <shortDescription>HDR : National Insurance Number</shortDescription>",
            "           <longDescription>Checks if NINO is valid or not</longDescription>",
            "           <display>true</display>",
            "           <bitmap/>",
            "           <attributeValue>",
            "               <attributeKey>CTCD</attributeKey>",
            "               <value>",
            "                   <TextValue>UK</TextValue>",
            "               </value>",
            "           </attributeValue>",
            "           <attributeValue>",
            "               <attributeKey>RLTY</attributeKey>",
            "               <value>",
            "                   <TextValue>Error</TextValue>",
            "               </value>",
            "           </attributeValue>",
            "       </Code>",
            "       <Code>",
            "           <key>H002</key>",
            "           <physicalName>H002</physicalName>",
            "           <shortDescription>HDR : Employer Number</shortDescription>",
            "           <longDescription>Checks if employer number is in system</longDescription>",
            "           <display>true</display>",
            "           <bitmap/>",
            "           <attributeValue>",
            "               <attributeKey>RLTY</attributeKey>",
            "               <value>",
            "                   <TextValue>Error</TextValue>",
            "               </value>",
            "           </attributeValue>",
            "       </Code>",
            "       <Code>",
            "           <key>M001</key>",
            "           <physicalName>M001</physicalName>",
            "           <shortDescription>MBR : TFN in use</shortDescription>",
            "           <longDescription>Checks if TFN provided is already in use in system</longDescription>",
            "           <display>true</display>",
            "           <bitmap/>",
            "           <attributeValue>",
            "               <attributeKey>CTCD</attributeKey>",
            "               <value>",
            "                   <TextValue>AUS</TextValue>",
            "               </value>",
            "           </attributeValue>",
            "           <attributeValue>",
            "               <attributeKey>RLTY</attributeKey>",
            "               <value>",
            "                   <TextValue>Error</TextValue>",
            "               </value>",
            "           </attributeValue>",
            "       </Code>",
            "       <Code>",
            "           <key>C001</key>",
            "           <physicalName>C001</physicalName>",
            "           <shortDescription>CA : Test</shortDescription>",
            "           <longDescription>Test Checks</longDescription>",
            "           <display>true</display>",
            "           <bitmap/>",
            "           <attributeValue>",
            "               <attributeKey>RLTY</attributeKey>",
            "               <value>",
            "                   <TextValue>Warning</TextValue>",
            "               </value>",
            "           </attributeValue>",
            "       </Code>",
            "   </TypeDefinition>",
            "</TypeModel>"
    };
}