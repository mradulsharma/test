{
	"time_stamp":"${currentTimeStamp}",
	"employee_name":"${candidateName}",
	"employee_salary":"${candidateSalary}",
	"employee_skills":[
<#list candidateSkillset as skillset>
		"${skillset_index + 1}. ${skillset}"<#if !skillset_has_next><#else>,</#if>
</#list>
	],
	"employee_addresses":[
<#list candidateAddresses as address>
		{
			"employee_address_type":"${address.getAddressType()}",
			"employee_address":"${address.getAddress()}"
		}<#if !address_has_next><#else>,</#if>
</#list>
	],
	"employee_keyvalue":[
<#list candidateHashmap?keys as kkey>
		{
			"${kkey}":"${candidateHashmap[kkey]}"
		}<#if !kkey_has_next><#else>,</#if>
</#list>
	]
	
	
}