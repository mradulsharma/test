package com.madiv.dtogtst.in;

import com.madiv.dtogtst.bsns.ColumnType;
import com.madiv.dtogtst.utl.DTOGetterSetterUtil;

public class HardCodedInput implements Input {
    private String[] columnFields= {
//            "new StringColumnMetaData(\"spmd_nino_no\"),",                  //01
//            "new StringColumnMetaData(\"spmd_tax_region\"),",               //02
//            "new DateTimeColumnMetaData(\"spmd_retirement_date\"),",        //03
//            
//            "new StringColumnMetaData(\"spmd_city_town\"),",                //04
//            "new StringColumnMetaData(\"spmd_hm_tel_number\"),",            //05
//            "new StringColumnMetaData(\"spmd_mbl_tel_number\"),",           //06
//            
//            "new StringColumnMetaData(\"spmd_empl_end_reason\"),",          //07
//            
//            "new DateTimeColumnMetaData(\"spmd_auto_enrlmnt_efftv_dt\"),",  //08
//            "new StringColumnMetaData(\"spmd_auto_enrlmnt_elgblty\"),",     //09
//            "new StringColumnMetaData(\"spmd_auto_enrlmnt_status\"),",      //10
//            "new DateTimeColumnMetaData(\"spmd_auto_enrlmnt_info_snt_dt\"),", //11
//            
//            "new StringColumnMetaData(\"spmd_cont_1_type_code\"),",         //12
//            "new NumericColumnMetaData(\"spmd_cont_1_rule_amt\"),",         //13
//            "new NumericColumnMetaData(\"spmd_cont_1_rule_rate\"),",        //14
//            "new StringColumnMetaData(\"spmd_cont_2_type_code\"),",         //15
//            "new NumericColumnMetaData(\"spmd_cont_2_rule_amt\"),",         //16
//            "new NumericColumnMetaData(\"spmd_cont_2_rule_rate\")"          //17
            
            
//            "new StringColumnMetaData(\"sloc_short_name\"),",           //01
//            "new StringColumnMetaData(\"contrib_type\"),",              //02
//            "new LongColumnMetaData(\"spsd_contrib_amount\"),",         //03
//            "new StringColumnMetaData(\"contrib_detail_status\")"       //04
            
            
//            "new StringColumnMetaData(\"earng_type\"),",                //01
//            "new LongColumnMetaData(\"sced_earng_amt\"),",              //02
//            "new StringColumnMetaData(\"earng_freq\"),",                //03
//            "new StringColumnMetaData(\"currency_code\"),",             //04
//            "new DateTimeColumnMetaData(\"sced_pay_prd_start_date\"),", //05
//            "new DateTimeColumnMetaData(\"sced_pay_prd_end_date\")"     //06
            
            "new LongColumnMetaData(\"erng_id\"),",                         //01
            "new LongColumnMetaData(\"erng_employment_id\"),",              //02
            "new LongColumnMetaData(\"erng_aclk_id\"),",                    //03
            "new LongColumnMetaData(\"erng_clnt_id\"),",                    //04
            "new LongColumnMetaData(\"erng_earning_type_id\"),",            //05
            "new StringColumnMetaData(\"erng_currency_code\"),",            //06
            "new NumericColumnMetaData(\"erng_earning_amount\"),",          //07
            "new DateTimeColumnMetaData(\"erng_pay_prd_start_date\"),",     //08
            "new DateTimeColumnMetaData(\"erng_pay_prd_end_date\"),",       //09
            "new LongColumnMetaData(\"erng_freq_type_code_id\"),",          //10
            "new DateTimeColumnMetaData(\"erng_last_updated\"),",           //11
            "new StringColumnMetaData(\"erng_last_updated_by\"),",          //12
            "new LongColumnMetaData(\"erng_timestamp\")"                    //13
            
    } ;
    
    
    
    @Override
    public Integer getTotalRecords() {
        return columnFields.length;
    }

    @Override
    public ColumnType getColumnType(int position) {
        return DTOGetterSetterUtil.getColumnType(columnFields[position]);
    }

    @Override
    public String getInputLineAt(int position) {
        return columnFields[position];
    }
}
