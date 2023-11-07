package com.madiv.brv;

public class DataModelCreator {

    public static void main(String[] args) {
        
        
        String[][] abc =
            {
                    {"Payroll Number","25","epcd_payroll_number"},
                    {"Account Number","17","epcd_account_number"},
                    {"Member Client ID","10","epcd_member_client_id"},
                    {"NINO","9","epcd_nino_number"},
                    {"Product ID","20","epcd_product_id"},
                    {"Surname","50","epcd_surname"},
                    {"Given Name","50","epcd_given_names"},
                    {"Date of Birth","10","epcd_date_of_birth"},
                    {"Gender","4","epcd_gender_code"},
                    {"Pay Period Start Date","10","epcd_pay_period_start_date"},
                    {"Pay Period End Date","10","epcd_pay_period_end_date"},
                    {"Earnings frequency","4","epcd_earning_freq"},
                    {"Gross earnings amount","15","epcd_gross_earning_amt"},
                    {"Pensionable earnings amount","15","epcd_pensionable_earning_amt"},
                    {"Contribution 1","15","epcd_cont_1_amount"},
                    {"Contribution 2","15","epcd_cont_2_amount"},
                    {"Contribution 3","15","epcd_cont_3_amount"},
                    {"Contribution 4","15","epcd_cont_4_amount"},
                    {"Contribution 5","15","epcd_cont_5_amount"},
                    {"Contribution 6","15","epcd_cont_6_amount"},
                    {"Contribution 7","15","epcd_cont_7_amount"},
                    {"Contribution 8","15","epcd_cont_8_amount"},
                    {"Contribution 9","15","epcd_cont_9_amount"},
                    {"Contribution 10","15","epcd_cont_10_amount"}
            };

        
        for(int i=0; i <abc.length; i++){
            System.out.println("        <column>");
            System.out.println("            <logical-name>"+abc[i][0]+"</logical-name>");
            System.out.println("            <physical-name>"+abc[i][2]+"</physical-name>");
            System.out.println("            <description>"+abc[i][0]+"</description>");
            System.out.println("            <varchar-type jdbc-type=\"VARCHAR\" size=\""+abc[i][1]+"\"/>");
            System.out.println("            <is-nullable>Y</is-nullable>");
            System.out.println("            <is-primary-key>N</is-primary-key>");
            System.out.println("            <default-value/>");
            System.out.println("            <is-system-maintained>N</is-system-maintained>");
            System.out.println("            <is-obfuscated>N</is-obfuscated>");
            System.out.println("        </column>");
        }
        
        
        
    }
}
