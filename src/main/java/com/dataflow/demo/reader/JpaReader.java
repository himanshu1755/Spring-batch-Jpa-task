package com.dataflow.demo.reader;

import com.dataflow.demo.entities.EitDmnStagingShadow;
import com.dataflow.demo.repository.IEitDmnStagingShadowRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class JpaReader implements ItemReader<EitDmnStagingShadow> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JpaReader.class);

    private List<EitDmnStagingShadow> itemList;

    @Autowired
    IEitDmnStagingShadowRepository eitDmnStagingShadowRepository;
    private int nextItemIndex;


    Collection<EitDmnStagingShadow> readFromDb() {
        List<EitDmnStagingShadow> l1 = eitDmnStagingShadowRepository.findAll();
       LOGGER.info("=======================Data from Reader======================");
       LOGGER.info(".");
       LOGGER.info(".");
       LOGGER.info(".");
       LOGGER.info("DB_LIST  {}",l1);
       LOGGER.info("DB_LIST.SIZE  {}",l1.size());
        return l1;

    }

    @Override
    public EitDmnStagingShadow read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        EitDmnStagingShadow item = null;
        if (ObjectUtils.isEmpty(itemList)) {
            itemList = new ArrayList<>(readFromDb());
            nextItemIndex = 0;
        }
        if (nextItemIndex < itemList.size()) {
            item = itemList.get(nextItemIndex);
            nextItemIndex++;
        }
        return item;
    }
}
