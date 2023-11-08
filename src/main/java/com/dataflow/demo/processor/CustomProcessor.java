package com.dataflow.demo.processor;

import com.dataflow.demo.entities.EitDmnStagingShadow;
import org.springframework.batch.item.ItemProcessor;


public class CustomProcessor implements ItemProcessor<EitDmnStagingShadow,EitDmnStagingShadow> {

    public EitDmnStagingShadow process(EitDmnStagingShadow eit) throws Exception
    {

        if(eit.getUserdef2().equalsIgnoreCase("Value2")){
            eit.setUserdef2("newValue2");
        }
        else {
            eit.setUserdef2("Value2");
        }
        return eit;
    }
}
