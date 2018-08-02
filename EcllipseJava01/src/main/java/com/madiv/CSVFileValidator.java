package com.madiv;


public class CSVFileValidator {
    private String[] fileContents = {
//            "HDR,AA35,US10401,BPAY Employer,20180509,4,4,1000,34000908716090AA06,20180509,UKLD,EMPL,,,,,,,,,,S104,,,,,,,,,,,,,,,,,,,,,,",
//            "MDA,2,QQ123456C,GBNI,100000234,8.5978E+13,Wickham,Alyssa,,19541212,20181212,MRS,FEM,19940207,37 St John's Wood Rd,,,,,London,NW8 8RA,,UK,farrah@somedomain.eml,4.42073E+11,4.42073E+11,19840801,,,,50000,,50000,Default,,,,,ERRG,200,89.16,,,",
//            "MDA,3,QQ123456D,GBRU,100000234,8.5978E+13,Connor,John,,19741012,20251212,MR,MALE,19940209,27 St John's Wood Rd,,,,,London,NW8 8RA,,UK,jconnor@somedomain.eml,4.42073E+11,4.42073E+11,20130801,20171231,RETD,,55000,,,Default,20130801,CENR,AUEN,20130801,MPOS,350,,,,",
//            "MDA,4,QQ123456E,GBSC,100000234,8.5978E+13,Bond,James,,19641012,20201212,MR,MALE,19940204,47 St John's Wood Rd,,,,,London,NW8 8RA,,UK,jbond@somedomain.eml,4.42072E+11,4.42043E+11,20140801,20171231,RESN,,30000,,,Default,20130801,ELWK,OPTI,20120801,EMPL,213,,,,",
//            "MDA,4,QQ123456F,GBSC,100000234,8.5978E+13,Bond,Jane,,19841012,20201212,MRS,FEM,19940201,47 St John's Wood Rd,,,,,London,NW8 8RA,,UK,jabond@somedomain.eml,4.42072E+11,4.42043E+11,20140801,20171231,OTHR,,100000,,,Default,20130801,NEJH,OPTO,20120801,MPOS,,12,EMPL,,12",
//            "CA,,1,NEW,,QQ123456C,8.5978E+13,Wickham,Alyssa,19541212,FEM,19940207,20160617,EOMT,1500,,200,,,,,,,,,,,,,,,,,,,,,,,,,,,",
//            "CA,,1,NEW,,QQ123123C,8.5978E+13,Doe,John,19781212,MALE,19940207,20160617,EOQT,,2100,300,,,,,,,,,,,,,,,,,,,,,,,,,,,",
//            "CA,,1,NEW,,QQ123123D,8.5978E+13,Connor,John,19741012,MALE,19941007,20140617,EOMT,800,,500,,,,,,,,,,,,,,,,,,,,,,,,,,,",
//            "CA,,1,NEW,,QQ123123F,8.5978E+13,Bond,Jane,19841012,FEM,20001007,20140417,EOQT,1200,,500,,,,,,,,,,,,,,,,,,,,,,,,,,,",
            "HDR,AA35,US10401,BPAY Employer,20180509,1,2,500.00,34000908716090AA06,20180509,UKLD,EMPL,,,,,,,,,,S104",
            "MDA,002,QQ123456C,GBNI,100000234,85977964496001,Wickham,Alyssa,,19541212,20181212,MRS,FEM,19940207,37 St John's Wood Rd,,,,,London,NW8 8RA,,UK,farrah@somedomain.eml,+442072899898,+442072899898,19840801,,,,50000.00,,50000.00,Default,,,,,ERRG,200.00,89.16,,,",
            "CA,,001,NEW,,QQ123456C,85977964496001,Wickham,Alyssa,19541212,FEM,19940207,20160617,EOMT,1500.00,1100.00,200.00,,,,,,,,,",
            "CA,,001,NEW,,QQ123123C,85977964496001,Doe,John,19781212,MALE,19940207,20160617,EOQT,2500.00,2100.00,300.00,,,,,,,,,"            
    };
    
    
    
    
    public static void main(String[] args) {
        CSVFileValidator validator = new CSVFileValidator();
        validator.doIt();
    }




    private void doIt() {
        int counter = 0;
        for(String line : fileContents){
            counter++;
            System.out.println("Line no ["+counter+"], total fields ["+getFields(line)+"]");
        }        
    }




    private int getFields(String line) {
        return line.length() - line.replace(",", "").length() + 1;
    }
}
