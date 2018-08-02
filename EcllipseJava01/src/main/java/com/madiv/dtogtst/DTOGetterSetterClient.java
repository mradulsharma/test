package com.madiv.dtogtst;

import java.util.List;

import com.madiv.dtogtst.bsns.ColumnHandlerFactory;
import com.madiv.dtogtst.bsns.ColumnHandlerType;
import com.madiv.dtogtst.in.HardCodedInput;
import com.madiv.dtogtst.in.Input;
import com.madiv.dtogtst.out.ConsoleOutput;
import com.madiv.dtogtst.out.Output;
import com.madiv.dtogtst.utl.DTOGetterSetterUtil;

public class DTOGetterSetterClient {

    public static void main(String[] args) {
        DTOGetterSetterClient generator = new DTOGetterSetterClient();
        Output output = new ConsoleOutput();
        generator.gnerateGetterSetter(0, output);
        generator.printOnConsole(output);
        generator.printFieldNames();
        
    }

    private void printFieldNames() {
        Input in = new HardCodedInput();

        
        
        System.out.println();
        System.out.println();
        System.out.println();
        
        for(int i=0; i < in.getTotalRecords(); i++) {
            String columnDeclaration = in.getInputLineAt(i);
            System.out.println(DTOGetterSetterUtil.getField(columnDeclaration));
        }
        
        
        System.out.println();
        System.out.println();
        System.out.println();
        
        
        
        for(int i=0; i < in.getTotalRecords(); i++) {
            String columnDeclaration = in.getInputLineAt(i);
            System.out.println(DTOGetterSetterUtil.getFieldCamelCase(columnDeclaration));
        }
    }

    private void printOnConsole(Output output) {
        output.outputResult();
    }

    private void gnerateGetterSetter(int startSequenceNumber, Output output) {
        Input in = new HardCodedInput();
        
        for(int i=0; i < in.getTotalRecords(); i++) {
            ColumnHandlerType handlerType = ColumnHandlerFactory.getColumnTypeHandler(in.getColumnType(i));
            String columnDeclaration = in.getInputLineAt(i); 
            List<String> colDefinition = handlerType.generateColumnDefinition(columnDeclaration, startSequenceNumber++);
            
            output.addMethodDetail(colDefinition);
        }
        
    }

}





